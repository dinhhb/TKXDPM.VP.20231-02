package com.hust.itep.aims.service.admin;

import com.hust.itep.aims.entity.media.Book;
import com.hust.itep.aims.entity.media.Media;
import com.hust.itep.aims.utils.ConfirmationAlert;
import com.hust.itep.aims.utils.InformationAlert;

import java.sql.*;
import java.util.Date;

public class BookService implements IMediaService{

    private final Connection connection;

    public BookService() {
        this.connection = MediaService.getInstance().getConnection();
    }

    @Override
    public void addMedia(Media media) throws SQLException {
        // Bắt đầu giao dịch
        connection.setAutoCommit(false);

        try {
            // Xử lý thêm Book, bao gồm thông tin của Media
            Book book = (Book) media;

            ConfirmationAlert confirmationAlert = new ConfirmationAlert();
            confirmationAlert.createAlert("Confirmation", null, "Are you sure you want to add this media?");
            confirmationAlert.show();

            if (!confirmationAlert.isConfirmed()) {
                throw new SQLException("Cancel adding media");
            }

            String mediaSql = "INSERT INTO Media (category, price, value, title, description, quantity, importDate, rushOrderSupported, imageUrl, productDimension, barcode) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement mediaStatement = connection.prepareStatement(mediaSql, Statement.RETURN_GENERATED_KEYS)) {
                // Thiết lập các tham số cho Media
                mediaStatement.setString(1, book.getCategory());
                mediaStatement.setInt(2, book.getPrice());
                mediaStatement.setInt(3, book.getValue());
                mediaStatement.setString(4, book.getTitle());
                mediaStatement.setString(5, book.getDescription());
                mediaStatement.setInt(6, book.getQuantity());

                // Đặt ngày hiện tại cho importDate
                java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());
                mediaStatement.setDate(7, sqlDate);

                boolean rushOrderSupported = (book.getRushOrderSupport() != null) ? book.getRushOrderSupport() : false;
                mediaStatement.setBoolean(8, rushOrderSupported);
                mediaStatement.setString(9, book.getImageUrl());
                mediaStatement.setString(10, book.getProductDimension());
                mediaStatement.setString(11, book.getBarcode());

                int affectedRows = mediaStatement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating media failed, no rows affected.");
                }

                // Lấy ID được tạo tự động
                try (ResultSet generatedKeys = mediaStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        book.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating book failed, no ID obtained.");
                    }
                }

                // Thêm thông tin vào bảng Book
                String bookSql = "INSERT INTO Book (id, authors, hardCover, publisher, publicationDate, pages, language, bookCategory) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement bookStatement = connection.prepareStatement(bookSql)) {
                    bookStatement.setInt(1, book.getId());
                    bookStatement.setString(2, book.getAuthors());
                    bookStatement.setString(3, book.getHardCover());
                    bookStatement.setString(4, book.getPublisher());
                    bookStatement.setDate(5, new java.sql.Date(book.getPublicationDate().getTime()));
                    bookStatement.setInt(6, book.getPages());
                    bookStatement.setString(7, book.getLanguage());
                    bookStatement.setString(8, book.getBookCategory());

                    bookStatement.executeUpdate();
                    System.out.println("Successfully added media: " + media);

                    InformationAlert alert = new InformationAlert();
                    alert.createAlert("Information Message", null, "Successfully added media" );
                    alert.show();
                }
            }

            connection.commit(); // Hoàn thành giao dịch
        } catch (SQLException e) {
            connection.rollback(); // Hủy bỏ giao dịch
            throw e;
        } finally {
            connection.setAutoCommit(true); // Khôi phục auto-commit
        }
    }
}
