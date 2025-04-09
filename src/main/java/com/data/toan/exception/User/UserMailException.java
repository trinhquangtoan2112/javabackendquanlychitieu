package com.data.toan.exception.User;

public class UserMailException extends RuntimeException {
    public UserMailException(String mess) {
        super(mess);
    }

    public UserMailException(String mess, Throwable cause) {
        super(mess, cause);
    }
}
