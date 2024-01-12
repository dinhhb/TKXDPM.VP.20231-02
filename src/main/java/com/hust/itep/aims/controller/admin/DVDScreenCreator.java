package com.hust.itep.aims.controller.admin;


import com.hust.itep.aims.entity.media.Media;
import com.hust.itep.aims.service.admin.DVDService;

import java.util.Collection;
import java.util.Collections;

public class DVDScreenCreator implements MediaScreenCreator {
    private DVDService dvdService;

    public DVDScreenCreator() {
        this.dvdService = new DVDService();
    }

    @Override
    public MediaScreen getMediaScreen(Media media, DataChangedListener data) {
        return new DVDScreen(media, data, dvdService);
    }

    @Override
    public Collection<String> getSupportedMediaType() {
        return Collections.singletonList("DVD");
    }
}
