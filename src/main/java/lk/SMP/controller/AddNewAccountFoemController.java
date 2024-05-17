package lk.SMP.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import lk.SMP.db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



public class AddNewAccountFoemController {

    @FXML
    private Button btnSave;

    @FXML
    private AnchorPane rooter;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtSecondPassword;

    @FXML
    private TextField txtUserName;

    @FXML
    void btnClickOnAction(ActionEvent event) {
        String name = txtUserName.getText();
        String password = txtPassword.getText();
        String secondPassword = txtSecondPassword.getText();

        String id = null;

        if (password.equals(secondPassword)){

            try {
                id = generateNextAssestId();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            String sql = "INSERT INTO user VALUES(?,?,?)";

            try {
                Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(sql);

                pstm.setString(1,id);
                pstm.setString(2,name);
                pstm.setString(3,password);

                boolean isSaved = pstm.executeUpdate() > 0;
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer Saved Successfully").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.INFORMATION, "Password isn't match").show();
        }


    }

    private String generateNextAssestId() throws SQLException {
        Connection con = DbConnection.getInstance().getConnection();

        String sql = "SELECT userId FROM user ORDER BY userId DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);
        if(resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }
    static String splitOrderId(String string) {
        if(string != null) {
            String[] strings = string.split("U0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "U00"+id;
            }else {
                if (length < 3){
                    return "U0"+id;
                }else {
                    return "O"+id;
                }
            }
        }
        return "U001";
     }
    }



