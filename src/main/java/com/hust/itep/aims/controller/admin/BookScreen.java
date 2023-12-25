package com.hust.itep.aims.controller.admin;

import com.hust.itep.aims.entity.media.Book;
import com.hust.itep.aims.entity.media.Media;
import com.hust.itep.aims.service.admin.MediaService;
import com.hust.itep.aims.utils.ErrorAlert;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class BookScreen implements MediaScreen {

    private Media media;
    private DataChangedListener dataChangedListener;
    MediaService mediaService = MediaService.getInstance();


    public BookScreen() {
    }

    public BookScreen(Media media, DataChangedListener dataChangedListener) {
        this.media = media;
        this.dataChangedListener = dataChangedListener;
    }

    @FXML
    private TextField book_author, book_hardCover, book_publisher, book_language, book_category, book_pages;
    @FXML
    private DatePicker book_publicationDate;
    @FXML
    private Button addBookBtn;
    @FXML
    private Label bookDetailLabel;

    @Override
    public void showScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/book.fxml"));
            loader.setControllerFactory(c -> this); // Use this instance as the controller
            Parent root = loader.load();

            if (media != null && media.getId() != 0) {
                // We are editing an existing book
                addBookBtn.setText("Update");
                bookDetailLabel.setText("Edit Book Detail");
                setBookFields(); // Set fields only in edit mode
            } else {
                bookDetailLabel.setText("Add Book Detail");
            }

            Stage stage = new Stage();
            stage.setTitle(media.getId() == 0 ? "Add Book Details" : "Update Book Details");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setBookFields() {
        try {
            // Assuming media.getId() returns the ID of the book you want to fetch
            Book book = mediaService.fetchBookFromDatabase(media.getId());

            if (book != null) {
                book_author.setText(book.getAuthors());
                book_hardCover.setText(book.getHardCover());
                book_publisher.setText(book.getPublisher());
                book_language.setText(book.getLanguage());
                book_category.setText(book.getBookCategory());
                book_pages.setText(String.valueOf(book.getPages()));
                if (book.getPublicationDate() != null) {
                    LocalDate localDate = new java.sql.Date(book.getPublicationDate().getTime()).toLocalDate();
                    book_publicationDate.setValue(localDate);
                }
            } else {
                System.out.println("No book found with ID: " + media.getId());
                // Handle case where no book is found
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void addBookBtnAction() {
        String author = book_author.getText();
        String hardCover = book_hardCover.getText();
        String publisher = book_publisher.getText();
        String language = book_language.getText();
        String category = book_category.getText();
        int pages = Integer.parseInt(book_pages.getText());
        LocalDate localDate = book_publicationDate.getValue();

        if (author.isEmpty()
                || hardCover.isEmpty()
                || publisher.isEmpty()
                || language.isEmpty()
                || category.isEmpty()
                || book_pages.getText().isEmpty()
                || localDate == null) {

            ErrorAlert errorAlert = new ErrorAlert();
            errorAlert.createAlert("Error Message", null, "Please fill all blank fields");
            errorAlert.show();
        }

        try{
            Book newBook = new Book();

            // Sao chép các giá trị từ đối tượng media
            newBook.setId(media.getId());
            newBook.setCategory(media.getCategory());
            newBook.setTitle(media.getTitle());
            newBook.setBarcode(media.getBarcode());
            newBook.setQuantity(media.getQuantity());
            newBook.setValue(media.getValue());
            newBook.setPrice(media.getPrice());
            newBook.setProductDimension(media.getProductDimension());
            newBook.setDescription(media.getDescription());
            newBook.setRushOrderSupport(media.getRushOrderSupport());
            newBook.setImageUrl(media.getImageUrl());

            newBook.setAuthors(author);
            newBook.setHardCover(hardCover);
            newBook.setPublisher(publisher);
            newBook.setLanguage(language);
            newBook.setBookCategory(category);
            newBook.setPages(pages);
            java.util.Date publicationDate = java.sql.Date.valueOf(localDate);
            newBook.setPublicationDate(publicationDate);

            // Check if it's a new book or an update
            if (media.getId() == 0) {
                // It's a new book
                mediaService.addMedia(newBook);
            } else {
                // It's an existing book
                mediaService.updateMedia(newBook);
            }

            dataChangedListener.onDataChanged();
            Stage stage = (Stage) book_author.getScene().getWindow();
            stage.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
