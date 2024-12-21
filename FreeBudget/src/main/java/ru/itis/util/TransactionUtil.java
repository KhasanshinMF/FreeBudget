package ru.itis.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.util.Date;

@UtilityClass
public class TransactionUtil {

    public boolean checkAmount(BigDecimal amount) {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean checkDate(Date date) {
        return date.compareTo(new Date()) <= 0;
    }

}
