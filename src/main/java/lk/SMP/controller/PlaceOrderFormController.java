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
import lk.SMP.db.DbConnection;
import lk.SMP.model.*;
import lk.SMP.model.Tm.CartTm;
import lk.SMP.repository.CustomerRepo;
import lk.SMP.repository.HarvestRepo;
import lk.SMP.repository.OrderRepo;
import lk.SMP.repository.PlaceOrderRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlaceOrderFormController {

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



    private static int idCounter = 4;

    private ObservableList<CartTm> obList = FXCollections.observableArrayList();


    public void initialize() {
        setDate();
        getCurrentOrderId();
        getCustomerIds();
        getItemCodes();
        setCellValueFactory();
    }
    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }
    private void getItemCodes() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<String> codeList = HarvestRepo.getCodes();

            for (String code : codeList) {
                obList.add(code);
            }
            cmbItemCode.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getCustomerIds() {
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
   /* private void getCurrentOrderId() {
        int currentId = idCounter;

        String nextOrderId = generateNextOrderId(currentId);
        lblOrderId.setText(nextOrderId);

    }
    private String generateNextOrderId(int currentId) {

        if(currentId != 0 ) {
           String customerId = String.format("O%04d", idCounter);
            return customerId;
        }
        return "O0001";
    }*/

    private void getCurrentOrderId() {
        try {
            String orderId = OrderRepo.getCurrentId();

            String nextOrderId = generateNextAssestId();
            lblOrderId.setText(nextOrderId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static String generateNextAssestId() throws SQLException {
        Connection con = DbConnection.getInstance().getConnection();

        String sql = "SELECT OrderId FROM orders ORDER BY OrderId DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    static String splitOrderId(String string) {
        if(string != null) {
            String[] strings = string.split("O0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "O00"+id;
            }else {
                if (length < 3){
                    return "O0"+id;
                }else {
                    return "O"+id;
                }
            }
        }
        return "O001";
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

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) {
        AnchorPane root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/view/CustomerForm.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String orderId = lblOrderId.getText();
        String cusId = cmbCustomerId.getValue();
        Date date = Date.valueOf(LocalDate.now());
        double subTotal = Double.parseDouble(lblNetTotal.getText());

        var order = new Order(orderId, cusId, date ,subTotal);

        List<OrderDetail> odList = new ArrayList<>();

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            CartTm tm = obList.get(i);

            OrderDetail od = new OrderDetail(
                    orderId,
                    tm.getCode(),
                    tm.getQty(),
                    tm.getUnitPrice(),
                    tm.getTotal()

            );

            odList.add(od);
        }

        PlaceOrder po = new PlaceOrder(order, odList);
        try {
            boolean isPlaced = PlaceOrderRepo.placeOrder(po);
            if(isPlaced) {
                idCounter++;
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed!").show();
            } else {
                new Alert(Alert.AlertType.WARNING, "Order Placed Unsuccessfully!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String id = cmbCustomerId.getValue();
        try {
            Customer customer = CustomerRepo.searchById(id);


            lblCustomerName.setText(customer.getName());


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();

        try {
            Harvest harvest = HarvestRepo.searchById(code);
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
        private void calculateNetTotal() {
            int netTotal = 0;
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                netTotal += (double) colTotal.getCellData(i);
            }
            lblNetTotal.setText(String.valueOf(netTotal));
        }


}
