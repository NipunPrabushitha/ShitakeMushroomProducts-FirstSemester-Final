package lk.SMP.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.SMP.Util.Regex;
import lk.SMP.model.Employee;
import lk.SMP.model.Harvest;
import lk.SMP.model.Tm.EmployeeTm;
import lk.SMP.model.Tm.HarvestTm;
import lk.SMP.repository.EmployeeRepo;
import lk.SMP.repository.HarvestRepo;
import lk.SMP.repository.UserRepo;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class EmployeeFormController {


    @FXML
    private TableColumn<?, ?>  colUserId;

    @FXML
    private JFXComboBox<String> cmbFieldId;

    @FXML
    private JFXComboBox<String> cmbUserId;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colDepartment;

    @FXML
    private TableColumn<?, ?> colEmployeeId;

    @FXML
    private TableColumn<?, ?> colFieldId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<EmployeeTm> tblEmployee;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtDepartment;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtName;
    public void initialize() {

        setCellValueFactory();
        loadAllCustomer();
        getFieldId();
        getUserId();
    }

    private void loadAllCustomer() {
        ObservableList<EmployeeTm> obList = FXCollections.observableArrayList();

        List<Employee> employeeList = null;
        try {
            employeeList = EmployeeRepo.getAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (Employee employee : employeeList){
            EmployeeTm employeeTm = new EmployeeTm(
                    employee.getEmployeeId(),
                    employee.getName(),
                    employee.getContactNumber(),
                    employee.getFieldId(),
                    employee.getUserId()

            );

            obList.add(employeeTm);
        }
        tblEmployee.setItems(obList);

    }

    private void setCellValueFactory() {
        colEmployeeId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colContactNumber.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        colFieldId.setCellValueFactory(new PropertyValueFactory<>("fieldId"));
        colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtEmployeeId.setText("");
        txtName.setText("");
        txtContactNo.setText("");
        cmbFieldId.setValue("");
        cmbUserId.setValue("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();

        try {
            boolean isDelete = EmployeeRepo.delete(employeeId);

            if (isDelete){
                new Alert(Alert.AlertType.CONFIRMATION, "Employee is Deleted!").show();
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
        if (isValid()) {
            String employeeId = txtEmployeeId.getText();
            String name = txtName.getText();
            String contactNumber = txtContactNo.getText();
            String fieldId = cmbFieldId.getValue();
            String userId = cmbUserId.getValue();
            if (employeeId.isEmpty() || name.isEmpty() || contactNumber.isEmpty() || fieldId.isEmpty() || userId.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "All fields are required").show();
                return;
            }
            try {
                Employee employee = new Employee(employeeId, name, contactNumber, fieldId, userId);

                boolean issaved = EmployeeRepo.save(employee);
                if (issaved) {
                    new Alert(Alert.AlertType.INFORMATION, "New Employee is Saved....!").show();
                }

            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Validation Error");
                alert.setHeaderText("Validation Failed");
                alert.setContentText("Please fill in all fields correctly.");
                alert.showAndWait();
            }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();
        String name = txtName.getText();
        String contactNumber = txtContactNo.getText();
        String fieldId = cmbFieldId.getValue();
        String userId = cmbUserId.getValue();
        if (employeeId.isEmpty() || name.isEmpty() || contactNumber.isEmpty() || fieldId.isEmpty() || userId.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "All fields are required").show();
            return;
        }

        try {
            Employee employee = new Employee(employeeId, name, contactNumber, fieldId, userId);

            boolean isUpdate = EmployeeRepo.update(employee);

            if (isUpdate){
                new Alert(Alert.AlertType.INFORMATION, "Employee is Updated....!").show();
                // clearFields();
            }
        }catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void txtEmployeeOnAction(ActionEvent event) {
        String employeeId = txtEmployeeId.getText();
        Employee employee = null;
        try {
            employee = EmployeeRepo.searchById(employeeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        txtName.setText(employee.getName());
        txtContactNo.setText(employee.getContactNumber());
        cmbFieldId.setValue(employee.getFieldId());
        cmbUserId.setValue(employee.getUserId());
        cmbFieldId.setValue(employee.getFieldId());
    }
    private void getFieldId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = HarvestRepo.getfieldId();

            for (String code : idList) {
                obList.add(code);
            }
            cmbFieldId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private void getUserId(){
        ObservableList<String> obList = FXCollections.observableArrayList();

        try {
            List<String> idList = UserRepo.getUserId();

            for (String code : idList) {
                obList.add(code);
            }
            cmbUserId.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isValid(){
        boolean isIdValid = Regex.setTextColor(lk.SMP.Util.TextField.IDE, txtEmployeeId);
        boolean isNameValid = Regex.setTextColor(lk.SMP.Util.TextField.NAME, txtName);
        boolean isContactValid = Regex.setTextColor(lk.SMP.Util.TextField.CONTACT, txtContactNo);

        return isIdValid && isNameValid && isContactValid;
    }
    public void EmployeeIdOnKeyRelesed(KeyEvent keyEvent) {
        boolean isIdValid = Regex.setTextColor(lk.SMP.Util.TextField.IDE, txtEmployeeId);
    }

    public void ContactOnKeyRelesed(KeyEvent keyEvent) {
        boolean isContactValid = Regex.setTextColor(lk.SMP.Util.TextField.CONTACT, txtContactNo);
    }

    public void NameOnKeyRelesed(KeyEvent keyEvent) {
        boolean isNameValid = Regex.setTextColor(lk.SMP.Util.TextField.NAME, txtName);
    }
}
