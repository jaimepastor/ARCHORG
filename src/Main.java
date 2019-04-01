import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root1 = FXMLLoader.load(getClass().getResource("BCDWindow.fxml"));
        primaryStage.setTitle("BCD Converter");
        primaryStage.setScene(new Scene(root1, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();

        Parent root2 = FXMLLoader.load(getClass().getResource("UTFWindow.fxml"));
        Stage UTFstage = new Stage();
        UTFstage.setTitle("Unicode Converter");
        UTFstage.setScene(new Scene(root2, 600, 400));
        UTFstage.setResizable(false);
        UTFstage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}