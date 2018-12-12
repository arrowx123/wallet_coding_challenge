package com.guofan.wallet;

public class AccountNotExistException extends Exception {
    public AccountNotExistException(String message) {
        super(message);
    }
}
