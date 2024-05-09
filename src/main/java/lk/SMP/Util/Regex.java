package lk.SMP.Util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text){
        String filed = "";
        switch (textField){
            case NAME:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case EMAIL:
                filed = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;
            case ADDRESS:
                filed = "^([A-z0-9]|[-\\,.@+]|\\\\s){4,}$";
                break;
            case PRICE:
                filed = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case PASSWORD:
                //filed = "^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[@$!%?&])[A-Za-z\\d@$!%*?&]{8,}$";
                filed = "^\\d{4}$";
                break;
        }
        Pattern pattern = Pattern.compile(filed);
        if (text != null){
            if (text.trim().isEmpty()){
                return false;
            }
        }else {
            return true;
        }
        Matcher matcher  = pattern.matcher(text);
        if (matcher.matches()){
            return true;
        }else {
            return  false;
        }
    }
    public static boolean setTextColor(TextField location, javafx.scene.control.TextField textField){
        if (Regex.isTextFieldValid(location, textField.getText())){
            textField.setStyle("-fx-border-color: #00FF00;-fx-background-color: #e7dbc0;");
            return true;
        }else {
            textField.setStyle("-fx-border-color: red;-fx-border-radius: 5;-fx-border-width: 3;-fx-background-color: #e7dbc0;");
            return false;
        }
    }
}
