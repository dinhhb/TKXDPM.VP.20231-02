package com.hust.itep.aims.service.admin;

import com.hust.itep.aims.entity.media.Media;

import java.sql.SQLException;

public interface IMediaService {
    void addMedia(Media media) throws SQLException;
}
