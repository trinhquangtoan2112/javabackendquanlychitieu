package com.data.toan.exception.User;

public class UserDontExits extends RuntimeException {
    public UserDontExits(String mess) {
        super(mess);
    }

    public UserDontExits(String mess, Throwable cause) {
        super(mess, cause);
    }
}
