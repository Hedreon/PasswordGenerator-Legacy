package com.hedreon.passwordgenerator.lib;

public class GeneratorSettings {
    // Whether to include numbers in the generated password
    public static boolean INCLUDE_NUMBERS;

    // Whether to include symbols in the generated password
    public static boolean INCLUDE_SYMBOLS;

    // Whether to include lowercase letters in the generated password
    public static boolean INCLUDE_LOWERCASE_LETTERS;

    // Whether to include uppercase letters in the generated password
    public static boolean INCLUDE_UPPERCASE_LETTERS;

    // The specified length of the generated password
    public static int PASSWORD_LENGTH;

    // The lowercase alphabet
    public static final String LOWER_CASE = "abcdefghijklmnopqrstuvwxyz";

    // The UPPERCASE alphabet
    public static final String UPPER_CASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // All the known symbols
    public static final String SYMBOLS = "!@#$%^&*~:;'?<>";
}