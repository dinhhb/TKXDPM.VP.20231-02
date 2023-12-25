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

/**
 * Ứng dụng singleton pattern
 * Mục đích:
 * - đảm bảo chỉ có 1 instance mediaService để tạo và duy trì kết nối tới db của admin
 * - sử dụng chung 1 mediaService trong các controller của admin
 */

public class MediaService {

    public Connection getConnection() {
        return connection;
    }

    private Connection connection; // Database connection
    private static MediaService instance;

    private Alert alert;

//    public MediaService(Connection connection) {
//        this.connection = connection;
//    }

    // Private constructor
    private MediaService() {
        this.connection = ConnectJDBC.getConnection();
    }

    // Static method to get the instance
    public static MediaService getInstance() {
        if (instance == null) {
            instance = new MediaService();
        }
        return instance;
    }

//    public void addMedia(Media media) throws SQLException {
//        // Check if media title already exists
//        if (isTitleTaken(media.getTitle())) {
//            ErrorAlert errorAlert = new ErrorAlert();
//            errorAlert.createAlert("Error Message", null, "Media title is already taken");
//            errorAlert.show();
//            throw new IllegalArgumentException(media.getTitle() + " is already taken");
//        }
//
//        // Bắt đầu giao dịch
//        connection.setAutoCommit(false);
//
//        try {
//            if (media instanceof Book) {
//                // Xử lý thêm Book, bao gồm thông tin của Media
//                Book book = (Book) media;
//
//                ConfirmationAlert confirmationAlert = new ConfirmationAlert();
//                confirmationAlert.createAlert("Confirmation", null, "Are you sure you want to add this media?");
//                confirmationAlert.show();
//
//                if (!confirmationAlert.isConfirmed()) {
//                    throw new SQLException("Cancel adding media");
//                }
//
//                String mediaSql = "INSERT INTO Media (category, price, value, title, description, quantity, importDate, rushOrderSupported, imageUrl, productDimension, barcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
//                try (PreparedStatement mediaStatement = connection.prepareStatement(mediaSql, Statement.RETURN_GENERATED_KEYS)) {
//                    // Thiết lập các tham số cho Media
//                    mediaStatement.setString(1, book.getCategory());
//                    mediaStatement.setInt(2, book.getPrice());
//                    mediaStatement.setInt(3, book.getValue());
//                    mediaStatement.setString(4, book.getTitle());
//                    mediaStatement.setString(5, book.getDescription());
//                    mediaStatement.setInt(6, book.getQuantity());
//
//                    // Đặt ngày hiện tại cho importDate
//                    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
//                    mediaStatement.setDate(7, sqlDate);
//
//                    boolean rushOrderSupported = (book.getRushOrderSupport() != null) ? book.getRushOrderSupport() : false;
//                    mediaStatement.setBoolean(8, rushOrderSupported);
//                    mediaStatement.setString(9, book.getImageUrl());
//                    mediaStatement.setString(10, book.getProductDimension());
//                    mediaStatement.setString(11, book.getBarcode());
//
//                    int affectedRows = mediaStatement.executeUpdate();
//
//                    if (affectedRows == 0) {
//                        throw new SQLException("Creating media failed, no rows affected.");
//                    }
//
//                    // Lấy ID được tạo tự động
//                    try (ResultSet generatedKeys = mediaStatement.getGeneratedKeys()) {
//                        if (generatedKeys.next()) {
//                            book.setId(generatedKeys.getInt(1));
//                        } else {
//                            throw new SQLException("Creating book failed, no ID obtained.");
//                        }
//                    }
//
//                    // Thêm thông tin vào bảng Book
//                    String bookSql = "INSERT INTO Book (id, authors, hardCover, publisher, publicationDate, pages, language, bookCategory) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
//                    try (PreparedStatement bookStatement = connection.prepareStatement(bookSql)) {
//                        bookStatement.setInt(1, book.getId());
//                        bookStatement.setString(2, book.getAuthors());
//                        bookStatement.setString(3, book.getHardCover());
//                        bookStatement.setString(4, book.getPublisher());
//                        bookStatement.setDate(5, new java.sql.Date(book.getPublicationDate().getTime()));
//                        bookStatement.setInt(6, book.getPages());
//                        bookStatement.setString(7, book.getLanguage());
//                        bookStatement.setString(8, book.getBookCategory());
//
//                        bookStatement.executeUpdate();
//                        System.out.println("Successfully added media: " + media);
//
//                        InformationAlert alert = new InformationAlert();
//                        alert.createAlert("Information Message", null, "Successfully added media" );
//                        alert.show();
//                    }
//                }
//            } else {
////                // Xử lý thêm Media thông thường
////                String mediaSql = "INSERT INTO Media (category, price, value, title, description, quantity, importDate, rushOrderSupported, imageUrl, productDimension, barcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
////                try (PreparedStatement mediaStatement = connection.prepareStatement(mediaSql)) {
////                    // Thiết lập các tham số cho Media
////                    // ...
////
////                    mediaStatement.executeUpdate();
////                }
//                System.out.println("Done");
//            }
//
//            connection.commit(); // Hoàn thành giao dịch
//        } catch (SQLException e) {
//            connection.rollback(); // Hủy bỏ giao dịch
//            throw e;
//        } finally {
//            connection.setAutoCommit(true); // Khôi phục auto-commit
//        }
//    }

    public void addMedia(Media media) throws SQLException {
        if (isTitleTaken(media.getTitle())) {
            ErrorAlert errorAlert = new ErrorAlert();
            errorAlert.createAlert("Error Message", null, "Media title is already taken");
            errorAlert.show();
            throw new IllegalArgumentException(media.getTitle() + " is already taken");
        }

        IMediaService handler = MediaServiceFactory.getHandler(media);
        if (handler != null) {
            handler.addMedia(media);
        }
    }

    public void updateMedia(Media media) throws SQLException {
        // Start transaction
        connection.setAutoCommit(false);

        try {
            // Update Media table
            String mediaSql = "UPDATE Media SET category = ?, price = ?, value = ?, title = ?, description = ?, quantity = ?, importDate = ?, rushOrderSupported = ?, imageUrl = ?, productDimension = ?, barcode = ? WHERE id = ?";
            try (PreparedStatement mediaStatement = connection.prepareStatement(mediaSql)) {
                mediaStatement.setString(1, media.getCategory());
                mediaStatement.setInt(2, media.getPrice());
                mediaStatement.setInt(3, media.getValue());
                mediaStatement.setString(4, media.getTitle());
                mediaStatement.setString(5, media.getDescription());
                mediaStatement.setInt(6, media.getQuantity());

                java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
                mediaStatement.setDate(7, sqlDate);

                boolean rushOrderSupported = (media.getRushOrderSupport() != null) ? media.getRushOrderSupport() : false;
                mediaStatement.setBoolean(8, rushOrderSupported);
                mediaStatement.setString(9, media.getImageUrl());
                mediaStatement.setString(10, media.getProductDimension());
                mediaStatement.setString(11, media.getBarcode());
                mediaStatement.setInt(12, media.getId());

                ConfirmationAlert confirmationAlert = new ConfirmationAlert();
                confirmationAlert.createAlert("Confirmation", null, "Are you sure you want to update this media?");
                confirmationAlert.show();

                if (!confirmationAlert.isConfirmed()) {
                    throw new SQLException("Cancel updating media");
                }

                int affectedRows = mediaStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Updating media failed, no rows affected.");
                }

                System.out.println("Successfully updated media: " + media);
                InformationAlert alert = new InformationAlert();
                alert.createAlert("Information Message", null, "Successfully updated media" );
                alert.show();

            }

            // Handle specific media type updates
            if (media instanceof Book) {
                updateBookDetails((Book) media);
//            } else if (media instanceof CD) {
//                updateCDDetails((CD) media);
            }

            connection.commit(); // Commit transaction
        } catch (SQLException e) {
            connection.rollback(); // Roll back transaction
            throw e;
        } finally {
            connection.setAutoCommit(true); // Restore auto-commit
//            System.out.println("Updating media with ID: " + media.getId());
        }
    }

    private void updateBookDetails(Book book) throws SQLException {
        String bookSql = "UPDATE Book SET authors = ?, hardCover = ?, publisher = ?, publicationDate = ?, pages = ?, language = ?, bookCategory = ? WHERE id = ?";
        try (PreparedStatement bookStatement = connection.prepareStatement(bookSql)) {
            bookStatement.setString(1, book.getAuthors());
            bookStatement.setString(2, book.getHardCover());
            bookStatement.setString(3, book.getPublisher());

            if (book.getPublicationDate() != null) {
                bookStatement.setDate(4, new java.sql.Date(book.getPublicationDate().getTime()));
            } else {
                bookStatement.setNull(4, java.sql.Types.DATE);
            }

            bookStatement.setInt(5, book.getPages());
            bookStatement.setString(6, book.getLanguage());
            bookStatement.setString(7, book.getBookCategory());
            bookStatement.setInt(8, book.getId());

            bookStatement.executeUpdate();
        }
    }


    private boolean isTitleTaken(String title) throws SQLException {
        String checkTitleSql = "SELECT title FROM Media WHERE title = ?";
        try (PreparedStatement statement = connection.prepareStatement(checkTitleSql)) {
            statement.setString(1, title);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        }
    }

    public Book fetchBookFromDatabase(int bookId) {
        // Assuming 'connection' is your established JDBC connection
        String sql = "SELECT * FROM Book WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, bookId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Book book = new Book();

                    // Set additional Book fields
                    book.setAuthors(resultSet.getString("authors"));
                    book.setHardCover(resultSet.getString("hardCover"));
                    book.setPublisher(resultSet.getString("publisher"));
                    book.setLanguage(resultSet.getString("language"));
                    book.setBookCategory(resultSet.getString("bookCategory"));
                    book.setPages(resultSet.getInt("pages"));
                    Date publicationDate = resultSet.getDate("publicationDate");
                    if (publicationDate != null) {
                        book.setPublicationDate(publicationDate);
                    }

                    return book;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching book");
            e.printStackTrace();
        }
        return null; // Return null if book not found or if an exception occurs
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
