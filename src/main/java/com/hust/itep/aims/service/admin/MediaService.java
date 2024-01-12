package com.hust.itep.aims.service.admin;

import com.hust.itep.aims.database.ConnectJDBC;
import com.hust.itep.aims.entity.media.Book;
import com.hust.itep.aims.entity.media.Media;
import com.hust.itep.aims.utils.ConfirmationAlert;
import com.hust.itep.aims.utils.ErrorAlert;
import com.hust.itep.aims.utils.InformationAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.Date;

public class MediaService {

    public Connection getConnection() {
        return connection;
    }

    private Connection connection; // Database connection
    private Alert alert;

    public MediaService() {
        this.connection = ConnectJDBC.getConnection();
    }

    public void deleteMedia(int mediaId) throws SQLException {
        String sql = "DELETE FROM Media WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, mediaId);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting media failed, no rows affected.");
            } else {
                System.out.println("Successfully deleted media with ID: " + mediaId);
                InformationAlert alert = new InformationAlert();
                alert.createAlert("Information Message", null, "Successfully deleted media");
                alert.show();
            }
        }
    }

    public ObservableList<Media> fetchMediaList() {
        ObservableList<Media> mediaList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Media";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Media media = new Media(resultSet.getInt("id"),
                        resultSet.getString("category"),
                        resultSet.getInt("price"),
                        resultSet.getInt("value"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getInt("quantity"),
                        resultSet.getDate("importDate"),
                        resultSet.getBoolean("rushOrderSupported"),
                        resultSet.getString("barcode"),
                        resultSet.getString("productDimension"),
                        resultSet.getString("imageUrl"));
                mediaList.add(media);
            }
        } catch (SQLException e) {
            System.out.println("Error fetching media");
            e.printStackTrace();
        }

        return mediaList;
    }


}
