package dev.spring.p10walletapp.exceptions;

public class WalletAlreadyExistsException extends RuntimeException {
    public WalletAlreadyExistsException(String msg){
        super(msg);
    }

}
