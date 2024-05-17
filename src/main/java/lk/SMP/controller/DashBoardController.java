package lk.SMP.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DashBoardController {

    public Label lblSetDate;
    public Label lblSetTime;
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

    @FXML
    void initialize() {
        setDate();
        setLocalTime();
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblSetDate.setText(String.valueOf(now));
    }

    private void setLocalTime() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss"); // Define format
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(1), e -> {
                    LocalTime timeInSriLanka = LocalTime.now(); // Get current time
                    String formattedTime = timeInSriLanka.format(formatter); // Format time
                    lblSetTime.setText(formattedTime); // Set formatted time to label
                })
        );
        timeline.setCycleCount(Animation.INDEFINITE); // Repeat indefinitely
        timeline.play(); // Start the timeline


    }

    @FXML
    void btnCustomerManageOnAction(ActionEvent event) {
        /*AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        Stage stage = (Stage) rootNode.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Customer Form");
        stage.centerOnScreen();*/

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/CustomerForm.fxml"));
        Parent rootNode = null;
        try {
            rootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    @FXML
    void btnAddSupplierOnAction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/AddSupplierForm.fxml"));
        Parent rootNode = null;
        try {
            rootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    public void btnStockMangementOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/StockManagementForm.fxml"));
        Parent rootNode = null;
        try {
            rootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    public void btnHarvestManagementOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/HarvestForm.fxml"));
        Parent rootNode = null;
        try {
            rootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    public void btnOrderOnAction(ActionEvent actionEvent) {
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/PlaceOrderForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnAddNewAccountOnAction(ActionEvent actionEvent) {
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/AddNewAccount.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void btnLogOutOnAction(ActionEvent actionEvent) {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
    }

    public void btnSalaryMangementOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/SalaryForm.fxml"));
        Parent rootNode = null;
        try {
            rootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }

    public void btnEmployeeMangeOnAction(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EmployeesForm.fxml"));
        Parent rootNode = null;
        try {
            rootNode = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        root.getChildren().clear();
        root.getChildren().add(rootNode);
    }
}
