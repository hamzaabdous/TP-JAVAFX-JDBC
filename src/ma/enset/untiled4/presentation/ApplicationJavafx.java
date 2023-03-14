package ma.enset.untiled4.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ApplicationJavafx extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
           
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("views/testView.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            
            primaryStage.setTitle("Gestion product ");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
