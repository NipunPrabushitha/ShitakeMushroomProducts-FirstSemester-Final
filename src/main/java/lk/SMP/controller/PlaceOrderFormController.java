package lk.SMP.controller;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.SMP.model.Customer;
import lk.SMP.model.Harvest;
import lk.SMP.model.Tm.CartTm;
import lk.SMP.model.Tm.HarvestTm;
import lk.SMP.repository.CustomerRepo;
import lk.SMP.repository.HarvestRepo;
import lk.SMP.repository.OrderRepo;


import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public class PlaceOrderFormController {

    public JFXComboBox cmbCustomerName;
    @FXML
    private JFXButton btnAddToCart;


    @FXML
    private JFXComboBox<String> cmbCustomerId;


    @FXML
    private JFXComboBox<String> cmbItemCode;


    @FXML
    private TableColumn<?, ?> colAction;


    @FXML
    private TableColumn<?, ?> colDescription;


    @FXML
    private TableColumn<?, ?> colItemCode;


    @FXML
    private TableColumn<?, ?> colQty;


    @FXML
    private TableColumn<?, ?> colTotal;


    @FXML
    private TableColumn<?, ?> colUnitPrice;


    @FXML
    private Label lblCustomerName;


    @FXML
    private Label lblDescription;


    @FXML
    private Label lblNetTotal;


    @FXML
    private Label lblOrderDate;


    @FXML
    private Label lblOrderId;


    @FXML
    private Label lblQtyOnHand;


    @FXML
    private Label lblUnitPrice;


    @FXML
    private AnchorPane pane;


    @FXML
    private TableView<CartTm> tblOrderCart;


    @FXML
    private TextField txtQty;


    private ObservableList<CartTm> obList = FXCollections.observableArrayList();


    public void initialize() {
        setDate();
        getCurrentOrderId();
        getBuyerIds();
        getItemCodes();
        setCellValueFactory();
    }


    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }


    private void getItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        List<String> codeList = HarvestRepo.getIds();


        for (String code : codeList) {
            obList.add(code);
        }
        cmbItemCode.setItems(obList);


    }


    private void getBuyerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();


        try {
            List<String> idList = CustomerRepo.getIds();


            for(String id : idList) {
                obList.add(id);
            }


            cmbCustomerId.setItems(obList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private void getCurrentOrderId() {
        String currentId = OrderRepo.getId();


        String nextOrderId = generateNextOrderId(currentId);
        lblOrderId.setText(nextOrderId);


    }


    private String generateNextOrderId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("O");  //" ", "2"
            int idNum = Integer.parseInt(split[1]);
            return "O" + ++idNum;
        }
        return "O1";
    }


    private void setDate() {
        LocalDate now = LocalDate.now();
        lblOrderDate.setText(String.valueOf(now));
    }


    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();
        String description = lblDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = qty * unitPrice;
        JFXButton btnRemove = new JFXButton("remove");
        btnRemove.setCursor(Cursor.HAND);


        btnRemove.setOnAction((e) -> {
            ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);


            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();


            if(type.orElse(no) == yes) {
                int selectedIndex = tblOrderCart.getSelectionModel().getSelectedIndex();
                obList.remove(selectedIndex);


                tblOrderCart.refresh();
                calculateNetTotal();
            }
        });


        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            if(code.equals(colItemCode.getCellData(i))) {


                CartTm tm = obList.get(i);
                qty += tm.getQty();
                total = qty * unitPrice;


                tm.setQty(qty);
                tm.setTotal(total);


                tblOrderCart.refresh();


                calculateNetTotal();
                return;
            }
        }


        CartTm tm = new CartTm(code, description, qty, unitPrice, total, btnRemove);
        obList.add(tm);


        tblOrderCart.setItems(obList);
        calculateNetTotal();
        txtQty.setText("");
    }


    private void calculateNetTotal() {
        int netTotal = 0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            netTotal += (double) colTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }


    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {


    }


    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {


    }


    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String id = cmbCustomerId.getValue();
        Customer buyer = CustomerRepo.searchById(id);


        lblCustomerName.setText(buyer.getName());


    }


    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String id = cmbItemCode.getValue();


        try {
            Harvest harvest = HarvestRepo.searchById(id);
            if(harvest != null) {
                lblDescription.setText(harvest.getCropType());
                lblUnitPrice.setText(String.valueOf(harvest.getUnitPrice()));
                lblQtyOnHand.setText(String.valueOf(harvest.getQuantity()));
            }


            txtQty.requestFocus();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }




    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();


        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Item Form");
        stage.centerOnScreen();
    }


    public void cmbCustomerNameOnAction(ActionEvent event) {




    }


    public void btnCutomerNameOnAction(ActionEvent event) {
    }
}
