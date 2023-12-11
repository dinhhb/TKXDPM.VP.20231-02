package com.hust.itep.aims.controller;

import com.hust.itep.aims.entity.admin.Data;
import com.hust.itep.aims.database.ConnectJDBC;
import com.hust.itep.aims.entity.media.Media;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

public class AdminController implements Initializable {

    @FXML
    private Button dashboard_btn;

    @FXML
    private Button logout_btn;

    @FXML
    private AnchorPane main_form;

    @FXML
    private ComboBox<?> media_category;

    @FXML
    private ComboBox<?> media_rushOrderSupport;

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

    private Connection connect;

    private PreparedStatement prepare;

    private Statement statement;

    private ResultSet result;

    private String[] categoryList = {"Book", "CD", "LP", "DVD"};

    private String[] rushOrderSupportList = {"Yes", "No"};

    private Image image;
    private ObservableList<Media> mediaListData;

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
                || Data.path == null) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {

            // Kiem tra media co title trung lap khong
            String checkMediaTitle = "SELECT title FROM Media WHERE title = '"
                    + media_title.getText() + "'";
            connect = ConnectJDBC.getConnection();

            try{

                statement = connect.createStatement();
                result = statement.executeQuery(checkMediaTitle);

                if (result.next()){
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText(media_title.getText() + " is already taken");
                    alert.showAndWait();
                } else {
                    String insertMedia = "INSERT INTO Media"
                            + "(category, price, value, title, description, quantity, importDate, rushOrderSupported, imageUrl, productDimension, barcode)"
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    prepare = connect.prepareStatement(insertMedia);
                    prepare.setString(1, (String)media_category.getSelectionModel().getSelectedItem());
                    prepare.setString(2, media_price.getText());
                    prepare.setString(3, media_value.getText());
                    prepare.setString(4, media_title.getText());
                    prepare.setString(5, media_description.getText());
                    prepare.setString(6, media_quantity.getText());

                    Date date = new Date();
                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                    prepare.setString(7, String.valueOf(sqlDate));

                    String rushOrderSupportValue = (String) media_rushOrderSupport.getSelectionModel().getSelectedItem();
                    boolean rushOrderSupported = "Yes".equals(rushOrderSupportValue);
                    prepare.setBoolean(8, rushOrderSupported);

                    String path = Data.path;
                    path = path.replace("\\", "\\\\");
                    prepare.setString(9, path);

                    prepare.setString(10, media_productDimension.getText());
                    prepare.setString(11, media_barcode.getText());

                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully added!");
                    alert.showAndWait();

                    mediaShowData();
                    mediasClearBtn();
                }

            } catch (Exception e){
                e.printStackTrace();
            }
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
                || Data.path == null
                || Data.id == 0) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            String path = Data.path;
            path = path.replace("\\", "\\\\");

            String rushOrderSupportValue = (String) media_rushOrderSupport.getSelectionModel().getSelectedItem();
            int rushOrderSupported = "Yes".equals(rushOrderSupportValue) ? 1 : 0;

            String updateMedia = "UPDATE Media SET "
                    + "title = '" + media_title.getText()
                    + "', category = '" + media_category.getSelectionModel().getSelectedItem()
                    + "', rushOrderSupported = '" + rushOrderSupported
                    + "', barcode = '" + media_barcode.getText()
                    + "', quantity = '" + media_quantity.getText()
                    + "', value = '" + media_value.getText()
                    + "', price = '" + media_price.getText()
                    + "', productDimension = '" + media_productDimension.getText()
                    + "', description = '" + media_description.getText()
                    + "', importDate = '" + Data.date
                    + "', imageUrl = '" + Data.path
                    + "' WHERE id = " + Data.id;
            connect = ConnectJDBC.getConnection();

            try{
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure you want to UPDATE Media title: " + media_title.getText() + "?");
                Optional<ButtonType> option = alert.showAndWait();

                if (option.get().equals(ButtonType.OK)){
                    prepare = connect.prepareStatement(updateMedia);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully updated!");
                    alert.showAndWait();

                    // Cap nhat tableview
                    mediaShowData();
                    // Clear cac truong thong tin
                    mediasClearBtn();
                }else{
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Cancelled");
                    alert.showAndWait();
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void mediaDeleteBtn(){
        if (Data.id == 0) {

            alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Message");
            alert.setHeaderText(null);
            alert.setContentText("Please fill all blank fields");
            alert.showAndWait();
        } else {
            alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Message");
            alert.setHeaderText(null);
            alert.setContentText("Are you sure you want to DELETE Media title: " + media_title.getText());
            Optional<ButtonType> option = alert.showAndWait();

            if (option.get().equals(ButtonType.OK)){
                String deleteMedia = "DELETE FROM Media WHERE id = " + Data.id;
                try{
                    prepare = connect.prepareStatement(deleteMedia);
                    prepare.executeUpdate();

                    alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Successfully deleted!");
                    alert.showAndWait();

                    // Cap nhat tableview
                    mediaShowData();
                    // Clear cac truong thong tin
                    mediasClearBtn();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }else{
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Message");
                alert.setHeaderText(null);
                alert.setContentText("Cancelled");
            }

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
        Data.path = "";
        Data.id = 0;
        medias_imageView.setImage(null);
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

        Data.path = media.getImageUrl();

        String path = "File:" + media.getImageUrl();
        Data.date = String.valueOf(media.getImportDate());
        Data.id = media.getId();

        image = new Image(path, 150, 150, false, true);
        medias_imageView.setImage(image);
    }

    public void mediasImportBtn() {
        FileChooser openFile = new FileChooser();
        openFile.getExtensionFilters().add(new FileChooser.ExtensionFilter("Open Image File", "*.png", "*.jpg"));
        File file = openFile.showOpenDialog(main_form.getScene().getWindow());
        if (file != null) {
            Data.path = file.getAbsolutePath();
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

    // merge data
    public ObservableList<Media> mediaList() {
        ObservableList<Media> listMedia = FXCollections.observableArrayList();
        String sql = "SELECT * FROM Media";
        connect = ConnectJDBC.getConnection();

        try {
            prepare = connect.prepareStatement(sql);
            result = prepare.executeQuery();

            Media media;

            while (result.next()) {
                media = new Media(result.getInt("id"),
                        result.getString("category"),
                        result.getInt("price"),
                        result.getInt("value"),
                        result.getString("title"),
                        result.getString("description"),
                        result.getInt("quantity"),
                        result.getDate("importDate"),
                        result.getBoolean("rushOrderSupported"),
                        result.getString("barcode"),
                        result.getString("productDimension"),
                        result.getString("imageUrl")
                );
                listMedia.add(media);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listMedia;
    }

    // hien thi data trong table
    public void mediaShowData() {
        mediaListData = mediaList();

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
            e.printStackTrace();
        }
    }

    public void displayUsername() {
        String user = Data.username;
        user = user.substring(0, 1).toUpperCase() + user.substring(1);
        username.setText(user);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        displayUsername();
        mediasCategoryList();
        mediasRushOrderSupportList();
        mediaShowData();
    }
}
