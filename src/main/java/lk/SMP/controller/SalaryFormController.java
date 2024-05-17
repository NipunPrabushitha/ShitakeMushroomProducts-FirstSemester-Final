package lk.SMP.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.SMP.model.Employee;
import lk.SMP.model.Harvest;
import lk.SMP.model.Salary;
import lk.SMP.model.Tm.SalaryTm;
import lk.SMP.repository.EmployeeRepo;
import lk.SMP.repository.HarvestRepo;
import lk.SMP.repository.SalaryRepo;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class SalaryFormController {

    @FXML
    private TableColumn<?, ?> colAmount;

    @FXML
    private TableColumn<?, ?> colEmployee;

    @FXML
    private TableColumn<?, ?> colPaymentDate;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private Label lblEmployeeId;

    @FXML
    private Label lbtDate;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SalaryTm> tblSalary;

    @FXML
    private TextField txtAmount;

    @FXML
    private TextField txtSalaryId;

    @FXML
    private JFXComboBox<String> vmbEmployeeName;
    public void initialize() {
        setDate();
        setCellValueFactory();
        loadAllCustomer();
        getEmployeeName();
    }

    private void loadAllCustomer() {
        ObservableList<SalaryTm> obList = FXCollections.observableArrayList();

        try {
            List<Salary> salaryList = SalaryRepo.getAll();
            for (Salary salary : salaryList) {
                SalaryTm tm = new SalaryTm(
                        salary.getSalaryId(),
                        salary.getAmount(),
                        salary.getPaymentDate(),
                        salary.getEmployeeId()
                );
                obList.add(tm);
            }
            tblSalary.setItems(obList);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void setCellValueFactory() {
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salaryId"));
        colEmployee.setCellValueFactory(new PropertyValueFactory<>("employee"));
        colPaymentDate.setCellValueFactory(new PropertyValueFactory<>("paymentDate"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtSalaryId.setText("");
        txtAmount.setText("");
        lblEmployeeId.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String salaryId = txtSalaryId.getText();

        try {
            boolean isDelete = SalaryRepo.delete(salaryId);

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Salary is Deleted!").show();
                clearFields();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnLogoutOnAction(ActionEvent event) {
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

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String salaryId = txtSalaryId.getText();
        String amount = txtAmount.getText();
        Date paymentDate = Date.valueOf(lbtDate.getText());
        String employeeId = lblEmployeeId.getText();


        Salary salary = new Salary(salaryId, amount,paymentDate, employeeId);

        try {
           boolean isSaved = SalaryRepo.save(salary);
            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "New Salary is Saved....!").show();
                //clearFields();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String salaryId = txtSalaryId.getText();
        String amount = txtAmount.getText();
        Date paymentDate = Date.valueOf(lbtDate.getText());
        String employeeId = lblEmployeeId.getText();


        try {
            Salary salary = new Salary(salaryId, amount, paymentDate, employeeId);

            boolean isUpdate = SalaryRepo.update(salary);

            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION, "Salary is Updated....!").show();
                // clearFields();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void cmbFEmployeeNameOnAction(ActionEvent event) {
        setEmployeeId();
    }

    @FXML
    void txtHarvestOnAction(ActionEvent event) {

    }
    private void setEmployeeId(){
        String employeeName = vmbEmployeeName.getValue();

        try {
            String fieldId = EmployeeRepo.getId(employeeName);
            lblEmployeeId.setText(fieldId);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getEmployeeName(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = EmployeeRepo.getEmployeedName();

            for (String code : idList) {
                obList.add(code);
            }
            vmbEmployeeName.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void setDate() {
        LocalDate now = LocalDate.now();
        lbtDate.setText(String.valueOf(now));
    }

}
