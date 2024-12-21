package ru.itis.servlet;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ru.itis.dto.AuthResponse;
import ru.itis.dto.SignInRequest;
import ru.itis.filter.AuthFilter;
import ru.itis.mapper.impl.UserMapperImpl;
import ru.itis.repository.impl.UserRepositoryImpl;
import ru.itis.service.UserService;
import ru.itis.service.impl.UserServiceImpl;
import ru.itis.util.KeyNames;

import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private UserService userService;

    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();
        userService = (UserService) context.getAttribute(KeyNames.USER_SERVICE);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("jsp/signIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SignInRequest signInRequest = SignInRequest.builder()
                .email(req.getParameter("email"))
                .password(req.getParameter("password"))
                .build();

        AuthResponse authResponse = userService.signIn(signInRequest);
        if (authResponse.getStatus() == 0) {
            HttpSession session = req.getSession(true);
            session.setAttribute(AuthFilter.AUTHORIZATION, true);
            session.setAttribute("user", authResponse.getUser());
            resp.sendRedirect("/main");
        } else {
            resp.sendRedirect("/error?err=" + authResponse.getStatusDesc());
        }
    }
}
