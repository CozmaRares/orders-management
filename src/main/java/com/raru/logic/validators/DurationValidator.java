package com.raru.logic.validators;

import java.util.regex.Pattern;

public class DurationValidator {
    private static Pattern regex = Pattern.compile("^(\\d+y)?(\\d+w)?(\\d+d)?(\\d+h)?(\\d+m)?(\\d+s)?(\\d+ms)?$");

    private DurationValidator() {
    }

    /**
     * Validates the duration string using a regular expression pattern.
     *
     * @param duration The duration string to validate.
     * @throws InvalidDurationException If the duration string is empty or does not
     *                                  match the expected pattern.
     */
    public static void validate(String duration) throws InvalidDurationException {
        if (duration.length() == 0 || !regex.matcher(duration).find())
            throw new InvalidDurationException();
    }

    public static class InvalidDurationException extends Exception {
        @Override
        public String getMessage() {
            return "invalid duration";
        }
    }
}
