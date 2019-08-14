package com.utils;

public class CodeGeneratorUtil {
    public static String generateCode() {
        return String.valueOf((int) (1000 + Math.random() * 8999));
    }
}
