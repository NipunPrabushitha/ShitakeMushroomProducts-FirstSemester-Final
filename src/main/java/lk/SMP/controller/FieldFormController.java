package lk.SMP.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import lk.SMP.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FieldFormController {

    @FXML
    private ComboBox<?> cmbProductId;

    @FXML
    private TableColumn<?, ?> colCropType;

    @FXML
    private TableColumn<?, ?> colFieldId;

    @FXML
    private TableColumn<?, ?> colProductId;

    @FXML
    private TableColumn<?, ?> colSize;

    @FXML
    private TableColumn<?, ?> colSoilType;

    @FXML
    private Label lblName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblField;

    @FXML
    private TextField txtCropType;

    @FXML
    private TextField txtFieldId;

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtSoilType;

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtFieldManageOnAction(ActionEvent event) {
        String id = txtFieldId.getText();

        String sql = "SELECT * FROM field WHERE fieldId =?";

        try{
            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1,id);

            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                String cropType = resultSet.getString(2);
                String soilType = resultSet.getString(3);
                String size = resultSet.getString(4);
                String productId = resultSet.getString(5);


                txtCropType.setText(cropType);
                txtSoilType.setText(soilType);
                txtSize.setText(size);
                //cmbProductId.setItems(productId);

            } else {
                new Alert(Alert.AlertType.ERROR, "Customer Not Found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,"Customer ID Not Found!");
        }
    }

}
