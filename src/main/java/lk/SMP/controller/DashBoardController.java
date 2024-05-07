package lk.SMP.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashBoardController {

    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane rootNode;

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

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/PlaceOrderForm.fxml"));
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
