package com.hust.itep.aims.controller.admin;

import com.hust.itep.aims.entity.media.Dvd;
import com.hust.itep.aims.entity.media.Media;
import com.hust.itep.aims.service.admin.DVDService;
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

public class DVDScreen implements MediaScreen {

    private Media media;
    private DataChangedListener dataChangedListener;
    DVDService dvdService;

    public DVDScreen() {
    }

    public DVDScreen(Media media, DataChangedListener dataChangedListener, DVDService dvdService) {
        this.media = media;
        this.dataChangedListener = dataChangedListener;
        this.dvdService = dvdService;
    }

    @FXML
    private TextField dvd_type, dvd_director, dvd_studio, dvd_filmType, dvd_language, dvd_subtitles, dvd_runtime;
    @FXML
    private DatePicker dvd_releasedDate;
    @FXML
    private Button addDVDBtn;
    @FXML
    private Label DVDDetailLabel;

    @Override
    public void showScreen() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/DVD.fxml"));
            loader.setControllerFactory(c -> this); // Use this instance as the controller
            Parent root = loader.load();

            if (media != null && media.getId() != 0) {
                // We are editing an existing book
                addDVDBtn.setText("Update");
                DVDDetailLabel.setText("Edit DVD Detail");
                setDVDFields(); // Set fields only in edit mode
            } else {
                DVDDetailLabel.setText("Add DVD Detail");
            }

            Stage stage = new Stage();
            stage.setTitle(media.getId() == 0 ? "Add DVD Details" : "Update DVD Details");
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDVDFields() {
        try {
            // Assuming media.getId() returns the ID of the DVD you want to fetch
            Dvd dvd = dvdService.fetchDVDFromDatabase(media.getId());

            if (dvd != null) {
                dvd_director.setText(dvd.getDirector());
                dvd_language.setText(dvd.getLanguage());
                dvd_runtime.setText(String.valueOf(dvd.getRuntime()));
                dvd_studio.setText(dvd.getStudio());
                dvd_type.setText(dvd.getDvdType());
                dvd_subtitles.setText(dvd.getSubtitles());
                dvd_filmType.setText(dvd.getFilmType());
                if (dvd.getReleasedDate() != null) {
                    LocalDate localDate = new java.sql.Date(dvd.getReleasedDate().getTime()).toLocalDate();
                    dvd_releasedDate.setValue(localDate);
                }
            } else {
                System.out.println("No DVD found with ID: " + media.getId());
                // Handle case where no DVD is found
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void addDVDBtnAction() {
        String studio = dvd_studio.getText();
        String filmType = dvd_filmType.getText();
        String type = dvd_type.getText();
        String language = dvd_language.getText();
        String director = dvd_director.getText();
        String subtitles = dvd_subtitles.getText();
        int runtime = Integer.parseInt(dvd_runtime.getText());
        LocalDate localDate = dvd_releasedDate.getValue();

        if (studio.isEmpty()
                || filmType.isEmpty()
                || type.isEmpty()
                || language.isEmpty()
                || director.isEmpty()
                || subtitles.isEmpty()
                || dvd_runtime.getText().isEmpty()
                || localDate == null) {

            ErrorAlert errorAlert = new ErrorAlert();
            errorAlert.createAlert("Error Message", null, "Please fill all blank fields");
            errorAlert.show();
        }

        try{
            java.util.Date publicationDate = java.sql.Date.valueOf(localDate);
            Dvd newDvd = new Dvd(
                    media,
                    studio,
                    filmType,
                    type,
                    language,
                    director,
                    subtitles,
                    runtime,
                    publicationDate
            );

            // Check if it's a new dvd or an update
            if (media.getId() == 0) {
                // It's a new dvd
                dvdService.addMedia(newDvd);
            } else {
                // It's an existing dvd
                dvdService.updateMedia(newDvd);
            }

            dataChangedListener.onDataChanged();
            Stage stage = (Stage) dvd_type.getScene().getWindow();
            stage.close();

        } catch (Exception e){
            e.printStackTrace();
        }

    }
}
