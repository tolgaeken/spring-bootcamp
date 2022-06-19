package dev.spring.p10walletapp.exceptions;

public class TransactionDateTimeParseException  extends RuntimeException{
    public TransactionDateTimeParseException(String msg)
    {
        super(msg);
    }
}
