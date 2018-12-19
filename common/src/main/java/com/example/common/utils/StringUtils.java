package com.example.common.utils;

/**
 * @author gouchao
 * @since 2018.12.7 10:20
 */
public class StringUtils {
    public static boolean isNull(String str) {
        if (str == null || str.length() == 0 || str.trim().length() == 0
                || str.equals("null")) {
            return true;
        }
        return false;
    }

    public static boolean isNotNull(String str) {
        return !isNull(str);
    }
}
