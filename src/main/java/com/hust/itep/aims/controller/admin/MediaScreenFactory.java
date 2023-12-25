package com.hust.itep.aims.controller.admin;

import com.hust.itep.aims.entity.media.Media;

// Su dung Factory pattern
public class MediaScreenFactory {
    public static MediaScreen getMediaScreen(String category, Media media, DataChangedListener data) {

        if (category == null) {
            return null;
        }

        switch (category.toUpperCase()) {
            case "BOOK":
                return new BookScreen(media, data);
            case "CD":
            case "LP":
                return new CDAndLPScreen(media);
            case "DVD":
                return new DVDScreen(media);
            default:
                return null;
        }
    }
}
