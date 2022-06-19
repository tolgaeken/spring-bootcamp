package dev.spring.p10walletapp.util;

import dev.spring.p10walletapp.exceptions.BadRequestException;
import dev.spring.p10walletapp.exceptions.TransactionDateTimeParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class WalletValidatorUtil {


    public static void validateWalletBalance(double balance) {
        if(balance < 0){
            throw new BadRequestException(ErrorMessageConstants.BALANCE_IS_MINUS);
        }
    }

    public static void validateTransactionDate(String transactionDate, DateTimeFormatter formatter) {
        try {
            LocalDate.parse(transactionDate, formatter);
        } catch (DateTimeParseException e) {
            throw new TransactionDateTimeParseException(ErrorMessageConstants.DATE_FORMAT_WRONG + transactionDate);
        }

    }
}
