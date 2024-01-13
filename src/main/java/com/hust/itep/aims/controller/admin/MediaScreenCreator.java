package com.hust.itep.aims.controller.admin;

import com.hust.itep.aims.entity.media.Media;

import java.util.Collection;

public interface MediaScreenCreator {
    MediaScreen getMediaScreen(Media media, DataChangedListener data);
    Collection<String> getSupportedMediaType();
}
