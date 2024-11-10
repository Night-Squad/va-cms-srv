package com.va.corporate.srv.helper;

public class NamingUtils {
    public static String camelToSnake(String camelCase) {
        // System.out.println("Naming utils : "+camelCase);
        // Handle the case where the field starts with "is" (e.g., isActive -> is_active)
        if (camelCase.startsWith("is") && camelCase.length() > 2 && Character.isUpperCase(camelCase.charAt(2))) {
            return "is_" + camelCase.substring(2).replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
        }
        // Default camelCase to snake_case conversion
        return camelCase.replaceAll("([a-z])([A-Z]+)", "$1_$2").toLowerCase();
    }
}
