package com.hust.itep.aims.service.admin;

import com.hust.itep.aims.entity.media.Book;
import com.hust.itep.aims.entity.media.Media;

public class MediaServiceFactory {
    public static IMediaService getHandler(Media media) {
        if (media instanceof Book) {
            return new BookService();
        } else {
            System.out.println("Media is not instanceof Book");
        }
        return null;
    }
}
