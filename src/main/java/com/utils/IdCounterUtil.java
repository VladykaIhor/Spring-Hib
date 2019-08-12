package com.utils;

public class IdCounterUtil {
    private static Long ProductId = 0L;
    private static Long UserId = 0L;

    public static Long getProductId() {
        return ProductId++;
    }

    public static Long getUserId() {
        return UserId++;
    }
}
