package com.raru.logic.validators;

public class QuantityValidator {
    private QuantityValidator() {
    }

    /**
     * Validates the quantity value.
     *
     * @param quantity The quantity value to validate.
     * @throws InvalidQuantityException If the quantity is less than or equal to 0.
     */
    public static void validate(int quantity) throws InvalidQuantityException {
        if (quantity <= 0)
            throw new InvalidQuantityException();
    }

    public static class InvalidQuantityException extends Exception {
        @Override
        public String getMessage() {
            return "invalid quantity";
        }
    }
}
