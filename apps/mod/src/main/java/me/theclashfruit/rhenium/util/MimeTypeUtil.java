package me.theclashfruit.rhenium.util;

public class MimeTypeUtil {
    public static String getMimeType(String ext) {
        return switch (ext) {
            case "html", "htm" -> "text/html";
            case "css" -> "text/css";
            case "js" -> "text/javascript";
            case "json" -> "application/json";
            case "xml" -> "application/xml";
            case "png" -> "image/png";
            case "jpg", "jpeg" -> "image/jpeg";
            case "gif" -> "image/gif";
            case "bmp" -> "image/bmp";
            case "ico" -> "image/x-icon";

            default -> "application/octet-stream";
        };
    }
}
