package lk.SMP.controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LodingFormController implements Initializable {
    @FXML
    public AnchorPane LodinForm;
    @FXML
    public AnchorPane loding;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Timeline timeline = new Timeline();
        KeyFrame keyFrame1 = new KeyFrame(Duration.millis(4000), actionEvent -> {
        });

        timeline.getKeyFrames().addAll(keyFrame1);
        timeline.playFromStart();

        timeline.setOnFinished(actionEvent -> {
            try {
                LodinForm.getScene().getWindow().hide();
                Parent root = FXMLLoader.load(getClass().getResource("/view/Login.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.centerOnScreen();
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
