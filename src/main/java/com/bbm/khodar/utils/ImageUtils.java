package com.bbm.khodar.utils;

public class ImageUtils {

    public static boolean isValidImageFile(String contentType) {
        if (contentType == null || contentType.isEmpty()) return false;
        return contentType.contains("image") && !contentType.contains("gif");
    }
}
