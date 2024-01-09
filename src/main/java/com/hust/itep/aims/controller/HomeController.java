package com.hust.itep.aims.controller;


import com.hust.itep.aims.dao.media.BookDAO;
import com.hust.itep.aims.dao.media.CDDAO;
import com.hust.itep.aims.dao.media.DVDDAO;
import com.hust.itep.aims.dao.media.MediaDAO;
import com.hust.itep.aims.entity.media.Book;
import com.hust.itep.aims.entity.media.Media;

import java.sql.SQLException;
import java.util.List;

/**
 * This class controls the flow of events in homescreen
 * @author nguyenlm
 */
public class HomeController extends BaseController {


    /**
     * this method gets all Media in DB and return back to home to display
     * @return List[Media]
     * @throws SQLException
     */
    public static List getAllMedia() throws SQLException{
        return new MediaDAO().getAllMedia();
    }

    public static Media getBookById(int id) throws SQLException{
        return new BookDAO().getMediaById(id);
    }

    public static Media getDvdById(int id) throws SQLException{
        return new DVDDAO().getMediaById(id);
    }

    public static Media getCdById(int id) throws SQLException{
        return new CDDAO().getMediaById(id);
    }
}
