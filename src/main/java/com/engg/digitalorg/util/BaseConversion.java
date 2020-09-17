package com.engg.digitalorg.util;

import org.springframework.stereotype.Service;

/**
 * The type Base conversion.
 */
@Service
public class BaseConversion {

    private static final String ALLOWED_STRING = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private char[] allowedCharacters = ALLOWED_STRING.toCharArray();
    private int base = allowedCharacters.length;

    /**
     * Encode string.
     *
     * @param input the input
     * @return the string
     */
    public String encode(long input) {
        StringBuilder encodedString = new StringBuilder();

        if (input == 0) {
            return String.valueOf(allowedCharacters[0]);
        }

        while (input > 0) {
            encodedString.append(allowedCharacters[(int) (input % base)]);
            input = input / base;
        }

        return encodedString.reverse().toString();
    }

    /**
     * Decode long.
     *
     * @param input the input
     * @return the long
     */
    public int decode(String input) {
        char[] characters = input.toCharArray();
        float length = characters.length;

        int decoded = 0;

        //counter is used to avoid reversing input string
        int counter = 1;
        for (int i = 0; i < length; i++) {
            decoded += ALLOWED_STRING.indexOf(characters[i]) * Math.pow(base, length - counter);
            counter++;
        }
        return decoded;
    }
}
