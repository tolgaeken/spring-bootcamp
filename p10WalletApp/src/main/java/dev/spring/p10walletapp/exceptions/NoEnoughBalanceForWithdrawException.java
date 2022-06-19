package dev.spring.p10walletapp.exceptions;

public class NoEnoughBalanceForWithdrawException extends RuntimeException{
    public NoEnoughBalanceForWithdrawException(String message){
        super(message);
    }
}
