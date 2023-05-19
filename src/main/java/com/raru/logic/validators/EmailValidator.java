package com.raru.logic.validators;

import java.util.regex.Pattern;

public class EmailValidator {
    private static final Pattern regex = Pattern.compile("^[\\w\\-\\.]+@([\\w-]+\\.)+[\\w-]{2,}$");

    private EmailValidator() {
    }

    /**
     * Validates the email string using a regular expression pattern.
     *
     * @param email The email string to validate.
     * @throws InvalidEmailException If the email string does not match the expected
     *                               pattern.
     */
    public static void validate(String email) throws InvalidEmailException {
        if (!regex.matcher(email).find())
            throw new InvalidEmailException();
    }

    public static class InvalidEmailException extends Exception {
        @Override
        public String getMessage() {
            return "invalid email";
        }
    }
}
