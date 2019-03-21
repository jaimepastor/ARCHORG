import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class BCDLogic {
    @FXML
    private Button btnOK;

    @FXML
    private TextField txtFieldInput;

    @FXML
    private Label lblUBCD, lblPBCD, lblDPBCD;

    public void initialize(){
        btnOK.setOnMouseClicked(event -> {
            System.out.println(txtFieldInput.getText());
            String text = txtFieldInput.getText();

            if(text.isEmpty()){
                System.out.println("Error!");
                lblUBCD.setText("ERROR");
                lblPBCD.setText("ERROR");
                lblDPBCD.setText("ERROR");
            } else {
                lblUBCD.setText(convertUBCD(txtFieldInput.getText()));
                lblPBCD.setText(convertPBCD(txtFieldInput.getText()));
                lblDPBCD.setText(convertDPBCD(txtFieldInput.getText()));
            }
        });

    }

    public String convertUBCD(String toConvert){
        return toConvert;
    }

    public String convertPBCD(String toConvert){
        return toConvert;
    }

    public String convertDPBCD(String toConvert){
        return toConvert;
    }
}
