package com.example.random;

import java.security.SecureRandom;

public class AccountNumberGenerator {
    private static final String BANK_CODE = "ABC";
    private static final int RANDOM_LENGTH = 9;
    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    public static String generateAccountNumber() {
        String randomPart = generateRandomDigits(RANDOM_LENGTH);
        int sum = 0;
        for (char c : randomPart.toCharArray()) {
            sum += Character.getNumericValue(c);
        }
        int checkDigit = sum % 10;
        return BANK_CODE + randomPart + checkDigit;
    }
    public static String generateRandomDigits(int length) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            stringBuilder.append (SECURE_RANDOM.nextInt(10));
        }
        return stringBuilder.toString();
    }
}
