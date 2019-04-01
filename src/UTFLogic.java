import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class UTFLogic {
    @FXML
    private Button btnOK;

    @FXML
    private TextField txtFieldInput;

    @FXML
    private Label lblUTF8, lblUTF16, lblUTF32;

    public void initialize(){
        btnOK.setOnMouseClicked(event -> {
            System.out.println(txtFieldInput.getText());
            String text = txtFieldInput.getText();

            if(text.isEmpty()){
                System.out.println("Error!");
                lblUTF8.setText("ERROR");
                lblUTF16.setText("ERROR");
                lblUTF32.setText("ERROR");
            } else {
                lblUTF8.setText(convertUTF8(txtFieldInput.getText()));
                lblUTF16.setText(convertUTF16(txtFieldInput.getText()));
                lblUTF32.setText(convertUTF32(txtFieldInput.getText()));
            }
        });

    }

    public String convertUTF8(String toConvert){
        return toConvert;
    }

    public String convertUTF16(String toConvert){
        return toConvert;
    }

    public String convertUTF32(String toConvert){
      
        int len = toConvert.length();
        if(len > 8) {
            return "Overload";
        }

        return "0x" + ("00000000" + toConvert).substring(toConvert.length()).toUpperCase();

    }
}
