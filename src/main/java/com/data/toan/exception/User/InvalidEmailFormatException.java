package com.data.toan.exception.User;

public class InvalidEmailFormatException extends RuntimeException {
    public InvalidEmailFormatException(String mess) {
        super(mess);
    }

    public InvalidEmailFormatException(String mess, Throwable cause) {
        super(mess, cause);
    }
}
