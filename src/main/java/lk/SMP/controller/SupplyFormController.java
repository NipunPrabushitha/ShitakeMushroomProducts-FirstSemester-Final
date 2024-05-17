package lk.SMP.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class SupplyFormController {

    @FXML
    private JFXButton btnAddToCart;

    @FXML
    private JFXComboBox<?> cmbStockCode;

    @FXML
    private JFXComboBox<?> cmbSupplierId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colStockCode;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblExpireDate;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<?> tblSupplyForm;

    @FXML
    private TextField txtExpireDate;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {

    }

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void btnPlaceSupplyOnAction(ActionEvent event) {

    }

    @FXML
    void cmbStockCodeOnAction(ActionEvent event) {

    }

    @FXML
    void cmbSupplierOnAction(ActionEvent event) {

    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {

    }

}
