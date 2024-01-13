package com.hust.itep.aims.controller.admin;

import com.hust.itep.aims.entity.admin.AdminSession;
import com.hust.itep.aims.entity.media.Media;
import com.hust.itep.aims.service.admin.MediaService;
import com.hust.itep.aims.utils.ConfirmationAlert;
import com.hust.itep.aims.utils.ErrorAlert;
import com.hust.itep.aims.utils.InformationAlert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.*;
import java.util.*;
import java.util.Date;

public class AdminController implements Initializable, DataChangedListener {

    @Override
    public void onDataChanged() {
        mediaShowData();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.mediaService = new MediaService();
        this.mediaScreenCreator = loadMediaScreenCreator();

        displayUsername();
        mediasCategoryList();
        mediasRushOrderSupportList();
        mediaShowData();

    }

    private MediaService mediaService;

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private ComboBox<String> media_category;

    @FXML
    private ComboBox<String> media_rushOrderSupport;

    @FXML
    private TextArea media_description;

    @FXML
    private Button media_importBtn;

    @FXML
    private TextField media_price;

    @FXML
    private TextField media_quantity;

    @FXML
    private TextField media_value;

    @FXML
    private Button medias_addBtn;

    @FXML
    private Button medias_btn;

    @FXML
    private Button medias_clearBtn;

    @FXML
    private TableColumn<Media, String> medias_col_category;

    @FXML
    private TableColumn<Media, String> medias_col_desciption;

    @FXML
    private TableColumn<Media, Integer> medias_col_id;

    @FXML
    private TableColumn<Media, Date> medias_col_importDate;

    @FXML
    private TableColumn<Media, Integer> medias_col_price;

    @FXML
    private TableColumn<Media, Integer> medias_col_quantity;

    @FXML
    private TableColumn<Media, String> medias_col_title;

    @FXML
    private TableColumn<Media, Integer> medias_col_value;

    @FXML
    private TableColumn<Media, Boolean> medias_col_rushOrderSupport;

    @FXML
    private TableColumn<Media, String> medias_col_barcode;

    @FXML
    private TableColumn<Media, String> medias_col_productDimension;

    @FXML
    private Button medias_deleteBtn;

    @FXML
    private AnchorPane medias_form;

    @FXML
    private TextField media_title;

    @FXML
    private TextField media_barcode;

    @FXML
    private TextField media_productDimension;


    @FXML
    private ImageView medias_imageView;

    @FXML
    private TableView<Media> medias_tableView;

    @FXML
    private Button medias_updateBtn;

    @FXML
    private Label username;

    @FXML
    private Button users_btn;

    private Alert alert;

    private String[] categoryList = {"Book", "CD", "LP", "DVD"};

    private String[] rushOrderSupportList = {"Yes", "No"};

    private Image image;
    private ObservableList<Media> mediaListData;

    private Map<String, MediaScreenCreator> mediaScreenCreator;

    private Map<String, MediaScreenCreator> loadMediaScreenCreator() {
        Map<String, MediaScreenCreator> creators = new HashMap<>();
        ServiceLoader<MediaScreenCreator> loader = ServiceLoader.load(MediaScreenCreator.class);
        for (MediaScreenCreator creator : loader) {
            for (String mediaType : creator.getSupportedMediaType()) {
                creators.put(mediaType.toUpperCase(), creator);
            }
        }
        return creators;
    }

    public void mediasAddBtn() {
        if (media_title.getText().isEmpty()
                || media_category.getSelectionModel().getSelectedItem() == null
                || media_rushOrderSupport.getSelectionModel().getSelectedItem() == null
                || media_barcode.getText().isEmpty()
                || media_quantity.getText().isEmpty()
                || media_value.getText().isEmpty()
                || media_price.getText().isEmpty()
                || media_productDimension.getText().isEmpty()
                || media_description.getText().isEmpty()
                || AdminSession.path == null) {

            ErrorAlert errorAlert = new ErrorAlert();
            errorAlert.createAlert("Error Message", null, "Please fill all blank fields");
            errorAlert.show();
        }
        try{
            Media newMedia = new Media();
            newMedia.setTitle(media_title.getText());
            newMedia.setCategory(media_category.getValue().toString());

            String rushOrderSupportValue = (String) media_rushOrderSupport.getSelectionModel().getSelectedItem();
            boolean rushOrderSupported = "Yes".equals(rushOrderSupportValue);
            newMedia.setRushOrderSupport(rushOrderSupported);

            newMedia.setBarcode(media_barcode.getText());
            newMedia.setQuantity(Integer.parseInt(media_quantity.getText()));
            newMedia.setValue(Integer.parseInt(media_value.getText()));
            newMedia.setPrice(Integer.parseInt(media_price.getText()));
            newMedia.setProductDimension(media_productDimension.getText());
            newMedia.setDescription(media_description.getText());

            String path = AdminSession.path;
            path = path.replace("\\", "\\\\");
            newMedia.setImageUrl(path);

            // Sử dụng Factory Pattern để hiển thị màn hình phù hợp
            String category = media_category.getValue().toString();
//            System.out.println(category);
//            MediaScreen screen = MediaScreenFactory.getMediaScreen(category.toUpperCase(), newMedia, this);
//            if (screen != null) {
//                screen.showScreen();
//            }

            MediaScreenCreator creator = this.mediaScreenCreator.get(category.toUpperCase());
            if (creator != null) {
                MediaScreen screen = creator.getMediaScreen(newMedia, this);
                screen.showScreen();
            }

//            mediaService.addMedia(newMedia);
//            mediaShowData();
            mediasClearBtn();

        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void mediaUpdateBtn(){
        if (media_title.getText().isEmpty()
                || media_category.getSelectionModel().getSelectedItem() == null
                || media_rushOrderSupport.getSelectionModel().getSelectedItem() == null
                || media_barcode.getText().isEmpty()
                || media_quantity.getText().isEmpty()
                || media_value.getText().isEmpty()
                || media_price.getText().isEmpty()
                || media_productDimension.getText().isEmpty()
                || media_description.getText().isEmpty()
                || AdminSession.path == null
                || AdminSession.id == 0) {

            ErrorAlert errorAlert = new ErrorAlert();
            errorAlert.createAlert("Error Message", null, "Please fill all blank fields");
            errorAlert.show();
        }
        try{
            Media updatedMedia = new Media();
            updatedMedia.setId(AdminSession.id);
            updatedMedia.setTitle(media_title.getText());
            updatedMedia.setCategory(media_category.getValue().toString());

            String rushOrderSupportValue = (String) media_rushOrderSupport.getSelectionModel().getSelectedItem();
            boolean rushOrderSupported = "Yes".equals(rushOrderSupportValue);
            updatedMedia.setRushOrderSupport(rushOrderSupported);

            updatedMedia.setBarcode(media_barcode.getText());
            updatedMedia.setQuantity(Integer.parseInt(media_quantity.getText()));
            updatedMedia.setValue(Integer.parseInt(media_value.getText()));
            updatedMedia.setPrice(Integer.parseInt(media_price.getText()));
            updatedMedia.setProductDimension(media_productDimension.getText());
            updatedMedia.setDescription(media_description.getText());

            String path = AdminSession.path;
            path = path.replace("\\", "\\\\");
            updatedMedia.setImageUrl(path);

            String category = media_category.getValue().toString();

//            MediaScreen screen = MediaScreenFactory.getMediaScreen(category.toUpperCase(), updatedMedia, this);
//            if (screen != null) {
//                screen.showScreen();
//            }

            MediaScreenCreator creator = this.mediaScreenCreator.get(category.toUpperCase());
            if (creator != null) {
                MediaScreen screen = creator.getMediaScreen(updatedMedia, this);
                screen.showScreen();
            }

//            mediaService.updateMedia(updatedMedia);
//            mediaShowData();
            mediasClearBtn();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void mediaDeleteBtn() {
        if (AdminSession.id == 0) {
            ErrorAlert errorAlert = new ErrorAlert();
            errorAlert.createAlert("Error Message", null, "Please select a media to delete");
            errorAlert.show();
            return;
        }

        ConfirmationAlert confirmationAlert = new ConfirmationAlert();
        confirmationAlert.createAlert("Confirmation Message", null, "Are you sure you want to DELETE Media title: " + media_title.getText());
        confirmationAlert.show();

        if (!confirmationAlert.isConfirmed()) {
            InformationAlert cancelledAlert = new InformationAlert();
            cancelledAlert.createAlert("Cancelled", null, "Media deletion cancelled");
            cancelledAlert.show();
            return;
        }

        try {
            mediaService.deleteMedia(AdminSession.id);
            mediaShowData();
            mediasClearBtn();
        } catch (SQLException e) {
            ErrorAlert sqlErrorAlert = new ErrorAlert();
            sqlErrorAlert.createAlert("SQL Error", null, "Error occurred while deleting media: " + e.getMessage());
            sqlErrorAlert.show();
            e.printStackTrace();
        }
    }

    public void mediasClearBtn(){
        media_title.setText("");
        media_category.getSelectionModel().clearSelection();
        media_rushOrderSupport.getSelectionModel().clearSelection();
        media_barcode.setText("");
        media_quantity.setText("");
        media_value.setText("");
        media_price.setText("");
        media_productDimension.setText("");
        media_description.setText("");
        AdminSession.path = "";
        AdminSession.id = 0;
        medias_imageView.setImage(null);
        media_category.setDisable(false);
    }

    public void selectMedia(){
        Media media = medias_tableView.getSelectionModel().getSelectedItem();
        int num = medias_tableView.getSelectionModel().getSelectedIndex();

        if ((num-1) < -1) return;

        media_title.setText(media.getTitle());
        media_barcode.setText(media.getBarcode());
        media_quantity.setText(String.valueOf(media.getQuantity()));
        media_value.setText(String.valueOf(media.getValue()));
        media_price.setText(String.valueOf(media.getPrice()));
        media_productDimension.setText(media.getProductDimension());
        media_description.setText(media.getDescription());

        // Set the ComboBox values and disable them
        media_category.setValue(media.getCategory());
        media_category.setDisable(true);

//        String rushOrderSupportValue = media.getRushOrderSupport() ? "Yes" : "No";
//        media_rushOrderSupport.setValue(rushOrderSupportValue);
//        media_rushOrderSupport.setDisable(true);

        AdminSession.path = media.getImageUrl();

        String path = "File:" + media.getImageUrl();
        AdminSession.date = String.valueOf(media.getImportDate());
        AdminSession.id = media.getId();

        image = new Image(path, 150, 150, false, true);
        medias_imageView.setImage(image);
    }

    public void mediasImportBtn() {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*.png", "*.jpg"));
        File file = openFile.showOpenDialog(main_form.getScene().getWindow());
        if (file != null) {
            AdminSession.path = file.getAbsolutePath();
            image = new Image(file.toURI().toString(), 150, 150, false, true);

            medias_imageView.setImage(image);

        }
    }

    public void mediasCategoryList() {
        List<String> categoryL = new ArrayList<>();
        for (String data : categoryList) {
            categoryL.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(categoryL);
        media_category.setItems(listData);
    }

    public void mediasRushOrderSupportList() {
        List<String> rushOrderSupportL = new ArrayList<>();
        for (String data : rushOrderSupportList) {
            rushOrderSupportL.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(rushOrderSupportL);
        media_rushOrderSupport.setItems(listData);
    }

    public void mediaShowData() {
        mediaListData = mediaService.fetchMediaList();

        medias_col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        medias_col_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        medias_col_category.setCellValueFactory(new PropertyValueFactory<>("category"));
        medias_col_value.setCellValueFactory(new PropertyValueFactory<>("value"));
        medias_col_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        medias_col_quantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        medias_col_importDate.setCellValueFactory(new PropertyValueFactory<>("importDate"));
        medias_col_rushOrderSupport.setCellValueFactory(new PropertyValueFactory<>("rushOrderSupport"));
        medias_col_desciption.setCellValueFactory(new PropertyValueFactory<>("description"));
        medias_col_productDimension.setCellValueFactory(new PropertyValueFactory<>("productDimension"));
        medias_col_barcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));

        medias_tableView.setItems(mediaListData);
    }


    public void logout() {
        try {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to logout?");
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)) {

                // hide trang admin
                logout_btn.getScene().getWindow().hide();

                // Quay ve login form
                Parent root = FXMLLoader.load(getClass().getResource("/fxml/login.fxml"));
                Stage stage = new Stage();
                Scene sence = new Scene(root);
                stage.setTitle("AIMS");
                stage.setScene(sence);
                stage.show();
            }

        } catch (Exception e) {
            System.out.println("Uncessfully logout");
            e.printStackTrace();
        }
    }

    public void displayUsername() {
        String user = AdminSession.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);
    }

}
