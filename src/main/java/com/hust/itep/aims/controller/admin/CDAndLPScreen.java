package com.hust.itep.aims.controller.admin;

import com.hust.itep.aims.entity.media.Media;

public class CDAndLPScreen implements MediaScreen{

    private Media media;

    public CDAndLPScreen(Media media) {
        this.media = media;
    }
    @Override
    public void showScreen() {
        // Logic để hiển thị màn hình CD
    }
}
