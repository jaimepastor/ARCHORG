import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainLogic {
    @FXML
    private Button btnUTFConv, btnBCDConv;

    public void initialize(){
        btnUTFConv.setOnMouseClicked(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("UTFWindow.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage UTFStage = (Stage) btnUTFConv.getScene().getWindow();
            UTFStage.setTitle("BCD Converter");
            UTFStage.setScene(new Scene(root, 1000, 400));
            UTFStage.setResizable(false);
            UTFStage.show();
        });

        btnBCDConv.setOnMouseClicked(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("BCDWindow.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage BCDStage = (Stage) btnBCDConv.getScene().getWindow();
            BCDStage.setTitle("BCD Converter");
            BCDStage.setScene(new Scene(root, 1000, 400));
            BCDStage.setResizable(false);
            BCDStage.show();
        });
    }
}
