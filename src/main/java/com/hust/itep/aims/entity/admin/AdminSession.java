package com.hust.itep.aims.entity.admin;

public class AdminSession {
    public static String username;
//    public static String imageUrl;
    public static String date;
    public static String path;

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        AdminSession.username = username;
    }

//    public static String getImageUrl() {
//        return imageUrl;
//    }

//    public static void setImageUrl(String imageUrl) {
//        AdminSession.imageUrl = imageUrl;
//    }

    public static String getDate() {
        return date;
    }

    public static void setDate(String date) {
        AdminSession.date = date;
    }

    public static Integer getId() {
        return id;
    }

    public static void setId(Integer id) {
        AdminSession.id = id;
    }

    public static Integer id;
}
