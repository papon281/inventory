package com.inventory.constant;

import java.util.regex.Pattern;

public class Regex {
    public static final Pattern EMAIL_REGEX = Pattern.compile(
            "(^[a-zA-Z0-9.\\-_]+@[a-zA-Z0-9]+\\.[a-zA-Z0-9]+)",
            Pattern.CASE_INSENSITIVE);
    public static final Pattern DATE_REGEX = Pattern.compile("(0[1-9]|[12][0-9]|3[01])-(0[1-9]|1[0-2])-(\\d{4})",
            Pattern.CASE_INSENSITIVE);
    public static final Pattern MOBILE_NUMBER_REGEX = Pattern.compile("^01[3-9]\\d{8}$");
    public static final Pattern PASSWORD_PATTERN = Pattern.compile(
            "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%&!])(?=\\S+$).{8,}$");
}