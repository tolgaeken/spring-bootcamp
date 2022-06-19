package dev.spring.p10walletapp.exceptions;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String msg){
        super(msg);
    }
}
