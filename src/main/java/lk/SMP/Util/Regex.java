package lk.SMP.Util;

import com.jfoenix.controls.JFXTextField;
import javafx.scene.paint.Paint;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Regex {
    public static boolean isTextFieldValid(TextField textField, String text){
        String filed = "";
        switch (textField){
            case IDA:
                filed = "^F\\d+$";
                break;
            case NON:
                filed = ".*";
                break;
            case NAME:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case EMAIL:
            case IDC:
                filed = "^C\\d+$";
                break;
            case CONTACT:
                filed="^([+]94{1,3}|[0])([1-9]{2})([0-9]){7}$";
                break;
            case IDCS:
                filed = "^CS\\d+$";
                break;
            case DESCRIPTION:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case DATE:
                filed = "^\\d{4}-\\d{2}-\\d{2}$";
                break;
            case STATUS:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case IDD:
                filed = "^D\\d+$";
                break;
            case METHOD:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case  AMOUNT:
                filed="^\\d+$";
                break;
            case IDP:
                filed = "^P\\d+$";
                break;
            case PRODUCT:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case IDS:
                filed = "^S\\d+$";
                break;
            case IDE:
                filed = "^E\\d+$";
                break;
            case POSITION:
                filed = "^[A-Za-z]+(?: [A-Za-z]+)*$";
                break;
            case DOUBLE:
                filed = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;
            case QTY:
                filed = "^([0-9]){1,}$";
                break;
            case IDI:
                filed = "^I\\d+$";
                break;
           /* case EMAIL:
                filed = "^([A-z])([A-z0-9.]){1,}[@]([A-z0-9]){1,10}[.]([A-z]){2,5}$";
                break;*/
            /*case ADDRESS:
                filed = "^([A-z0-9]|[-\\,.@+]|\\\\s){4,}$";
                break;*/
            /*case PRICE:
                filed = "^([0-9]){1,}[.]([0-9]){1,}$";
                break;*/
            case PASSWORD:
                //filed = "^(?=.[a-zA-Z])(?=.\\d)(?=.[@$!%?&])[A-Za-z\\d@$!%?&]{8,}$";
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
