package com.data.toan.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserHelper {
    // private static final String gmail_regex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";

    private UserHelper() {

    }

    public static boolean isEmail(String email) {
        Pattern pattern = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
