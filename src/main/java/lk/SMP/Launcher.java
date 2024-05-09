package lk.SMP;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage stage) throws Exception {
       /* stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/Login.fxml"))));
        stage.setTitle("Login Form");
        stage.centerOnScreen();
        stage.show();*/
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/LodingPage.fxml"))));
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.centerOnScreen();
        stage.show();
    }
}
