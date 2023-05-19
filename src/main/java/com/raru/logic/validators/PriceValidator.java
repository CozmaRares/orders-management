package com.raru.logic.validators;

public class PriceValidator {
    private PriceValidator() {
    }

    /**
     * Validates the price value.
     *
     * @param price The price value to validate.
     * @throws InvalidPriceException If the price is less than or equal to 0.
     */
    public static void validate(double price) throws InvalidPriceException {
        if (price <= 0)
            throw new InvalidPriceException();
    }

    public static class InvalidPriceException extends Exception {
        @Override
        public String getMessage() {
            return "invalid price";
        }
    }
}
