//package com.hust.itep.aims.controller.admin;
//
//import com.hust.itep.aims.entity.media.Media;
//import com.hust.itep.aims.service.admin.BookService;
//import com.hust.itep.aims.service.admin.CDAndLPService;
//import com.hust.itep.aims.service.admin.DVDService;
//
//// Su dung Factory pattern
//public class MediaScreenFactory {
//    public static MediaScreen getMediaScreen(String category, Media media, DataChangedListener data) {
//
//        if (category == null) {
//            return null;
//        }
//
//        switch (category.toUpperCase()) {
//            case "BOOK":
//                BookService bookService = new BookService();
//                return new BookScreen(media, data, bookService);
//            case "CD":
//            case "LP":
//                CDAndLPService cdAndLpService = new CDAndLPService();
//                return new CDAndLPScreen(media, data, cdAndLpService);
//            case "DVD":
//                DVDService dvdService = new DVDService();
//                return new DVDScreen(media, data, dvdService);
//            default:
//                return null;
//        }
//    }
//}
