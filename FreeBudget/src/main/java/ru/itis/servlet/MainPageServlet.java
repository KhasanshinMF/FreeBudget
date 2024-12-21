package ru.itis.servlet;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.itis.dto.*;
import ru.itis.mapper.CategoryMapper;
import ru.itis.mapper.TransactionCategoryMapper;
import ru.itis.mapper.TransactionMapper;
import ru.itis.mapper.impl.CategoryMapperImpl;
import ru.itis.mapper.impl.TransactionCategoryMapperImpl;
import ru.itis.mapper.impl.TransactionMapperImpl;
import ru.itis.repository.CategoryRepository;
import ru.itis.repository.TransactionCategoryRepository;
import ru.itis.repository.TransactionRepository;
import ru.itis.repository.impl.CategoryRepositoryImpl;
import ru.itis.repository.impl.TransactionCategoryRepositoryImpl;
import ru.itis.repository.impl.TransactionRepositoryImpl;
import ru.itis.service.CategoryService;
import ru.itis.service.TransactionCategoryService;
import ru.itis.service.TransactionService;
import ru.itis.service.impl.CategoryServiceImpl;
import ru.itis.service.impl.TransactionCategoryServiceImpl;
import ru.itis.service.impl.TransactionServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@WebServlet("/main")
public class MainPageServlet extends HttpServlet {

    private CategoryService categoryService;

    private TransactionService transactionService;

    private TransactionCategoryService transactionCategoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        CategoryRepository categoryRepository = new CategoryRepositoryImpl();
        CategoryMapper categoryMapper = new CategoryMapperImpl();
        categoryService = new CategoryServiceImpl(categoryRepository, categoryMapper);

        TransactionRepository transactionRepository = new TransactionRepositoryImpl();
        TransactionMapper transactionMapper = new TransactionMapperImpl();
        transactionService = new TransactionServiceImpl(transactionMapper, transactionRepository);

        TransactionCategoryRepository transactionCategoryRepository = new TransactionCategoryRepositoryImpl();
        TransactionCategoryMapper transactionCategoryMapper = new TransactionCategoryMapperImpl();
        transactionCategoryService = new TransactionCategoryServiceImpl(transactionCategoryRepository, transactionCategoryMapper);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDataResponse user = (UserDataResponse) req.getSession().getAttribute("user");

        req.setAttribute("userName", user.getNickname());
        req.setAttribute("categories", categoryService.findAllCategoryByUserId(user.getId()));
        req.setAttribute("transactions", getFilteredTransactions(req, user.getId()));

        req.getRequestDispatcher("jsp/main.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDataResponse user = (UserDataResponse) req.getSession().getAttribute("user");

        String action = req.getParameter("action");


        if ("addTransaction".equals(action)) {
            handleTransactionCreation(req, resp, user);
        } else if ("addCategory".equals(action)) {
            handleCategoryCreation(req, resp, user);
        }
    }

    private void handleCategoryCreation(HttpServletRequest req, HttpServletResponse resp, UserDataResponse user) throws IOException, ServletException {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setUserId(user.getId());
        categoryDto.setName(req.getParameter("categoryName"));

        CategoryResponse categoryResponse = categoryService.saveNewCategory(categoryDto);
        if (categoryResponse.getStatus() == 0) {
            resp.sendRedirect("/main");
        } else {
            req.setAttribute("errorMessage", categoryResponse.getStatusDesc());
            req.getRequestDispatcher("jsp/main.jsp").forward(req, resp);
        }
    }

    private void handleTransactionCreation(HttpServletRequest req, HttpServletResponse resp, UserDataResponse user) throws IOException, ServletException {
        TransactionDto transactionDto = createTransactionDtoFromRequest(req, user.getId());

        TransactionResponse transactionResponse = transactionService.saveNewTransaction(transactionDto);

        if (transactionResponse.getStatus() == 0) {

            long transactionId = transactionResponse.getTransactionDto().getId();

            String[] selectedCategoryIds = req.getParameterValues("categoryIds");
            if (selectedCategoryIds != null) {
                for (String categoryId : selectedCategoryIds) {
                    TransactionCategoryDto dto = new TransactionCategoryDto(transactionId, Long.parseLong(categoryId));
                    transactionCategoryService.save(dto);
                }
            }
            resp.sendRedirect("/main");
        } else {
            req.setAttribute("errorMessage", transactionResponse.getStatusDesc());
            req.getRequestDispatcher("jsp/main.jsp").forward(req, resp);
        }
    }

    private List<TransactionDtoWithCategories> getFilteredTransactions(HttpServletRequest req, Long userId) {
        List<TransactionDtoWithCategories> allTransactions = transactionService.findAllTransactionsByUserId(userId);
        Long filterCategoryId = getFilterCategoryId(req);

        if (filterCategoryId == null) {
            return allTransactions;
        }

        List<TransactionDtoWithCategories> filteredTransactions = transactionService.findTransactionsByCategoryId(filterCategoryId);

        if (filteredTransactions.isEmpty()) {
            return Collections.emptyList();
        }

        return filteredTransactions;
    }

    private Long getFilterCategoryId(HttpServletRequest req) {
        try {
            return Long.parseLong(req.getParameter("filterCategoryId"));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private TransactionDto createTransactionDtoFromRequest(HttpServletRequest req, Long userId) {
        return TransactionDto.builder()
                .userId(userId)
                .amount(new BigDecimal(req.getParameter("amount")))
                .date(parseStringToDate(req.getParameter("date")))
                .isIncome(Boolean.parseBoolean(req.getParameter("isIncome")))
                .description(req.getParameter("description"))
                .build();
    }

    private Date parseStringToDate(String date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date parsedDate;
        while (true) {
            try {
                parsedDate = formatter.parse(date);
                break;
            } catch (Exception e) {
                return null;
            }
        }
        return parsedDate;
    }
}
