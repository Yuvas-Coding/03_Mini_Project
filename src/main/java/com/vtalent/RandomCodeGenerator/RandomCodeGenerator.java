package com.vtalent.RandomCodeGenerator;

import java.security.SecureRandom;
import java.util.Random;

public class RandomCodeGenerator {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int CODE_LENGTH = 6; // You can adjust the length as needed
    
    public static void main(String[] args) {
        String randomCode = generateRandomCode(CODE_LENGTH);
        System.out.println("Random Code: " + randomCode);
    }
    
    public static String generateRandomCode(int length) {
        StringBuilder code = new StringBuilder(length);
        Random random = new SecureRandom();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALPHABET.length());
            char randomChar = ALPHABET.charAt(randomIndex);
            code.append(randomChar);
        }

        return code.toString();
    }
}
