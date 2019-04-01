import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;


public class UTFLogic {
    @FXML
    private Button btnOK;

    @FXML
    private TextField txtFieldInput;

    @FXML
    private Label lblUTF8, lblUTF16, lblUTF32;
    char[] hexCharArray;

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

    public String convertUTF8(String toConvert) {
        String format, answer;
        char[] dupearray1, dupearray2, dupearray3, dupearray4, answerarrray;
        int i, j, decimal;
        String hexStr;
        int temp = Integer.parseInt(toConvert, 16);
        if(temp > 0x1FFFFF) {
            return "TOO BIG!!";
        }

        hexCharArray = Integer.toBinaryString(temp).toCharArray();

        System.out.println("testing1: " + new String(hexCharArray));
        System.out.println("testing2: " + hexCharArray.length);
        answer = "00000000000000000000000000000000";
        if(hexCharArray.length <= 7){
            answerarrray = new char[8];
            for (i = hexCharArray.length - 1, j = 7; j >= 0;j--){//copy adds leading zeros
                if(i == -1){
                    answerarrray[j] = '0';
                } else {
                    answerarrray[j] = hexCharArray[i];
                    i--;
                }
            }
            answerarrray[0] = '0';
            answer = new String(answerarrray);//answer in string

            decimal = Integer.parseInt(answer, 2);
            answer = Integer.toString(decimal, 16);
        } else if(hexCharArray.length <= 11){
            answerarrray = new char[16];
            for (i = hexCharArray.length - 1, j = 15; j >= 0; j--){
                if (i == -1){
                    answerarrray[j] = '0';
                } else if(j == 9){
                    j--;
                } else {
                    answerarrray[j] = hexCharArray[i];
                    i--;
                }
            }
            answerarrray[0] = '1';
            answerarrray[1] = '1';
            answerarrray[2] = '0';
            answerarrray[8] = '1';
            answerarrray[9] = '0';

            answer = new String(answerarrray);//answer in string
            System.out.println(answer);
            decimal = Integer.parseInt(answer, 2);
            answer = Integer.toString(decimal, 16);
        } else if (hexCharArray.length <= 16){
            answerarrray = new char[24];
            for (i = hexCharArray.length - 1, j = 23; j >= 0; j--){
                if (i == -1){
                    answerarrray[j] = '0';
                } else if(j == 9 || j == 17){
                    j--;
                } else {
                    answerarrray[j] = hexCharArray[i];
                    i--;
                }
            }
            answerarrray[0] = '1';
            answerarrray[1] = '1';
            answerarrray[2] = '1';
            answerarrray[3] = '0';
            answerarrray[8] = '1';
            answerarrray[9] = '0';
            answerarrray[16] = '1';
            answerarrray[17] = '0';

            answer = new String(answerarrray);//answer in string
            System.out.println(answer);
            decimal = Integer.parseInt(answer, 2);
            answer = Integer.toString(decimal, 16);
        } else if (hexCharArray.length <= 21){
            answerarrray = new char[32];
            for (i = hexCharArray.length - 1, j = 31; j >= 0; j--){
                if (i == -1){
                    answerarrray[j] = '0';
                } else if(j == 9 || j == 17 || j == 25){
                    j--;
                } else {
                    answerarrray[j] = hexCharArray[i];
                    i--;
                }
            }
            answerarrray[0] = '1';
            answerarrray[1] = '1';
            answerarrray[2] = '1';
            answerarrray[3] = '1';
            answerarrray[4] = '0';
            answerarrray[8] = '1';
            answerarrray[9] = '0';
            answerarrray[16] = '1';
            answerarrray[17] = '0';
            answerarrray[24] = '1';
            answerarrray[25] = '0';
            answer = new String(answerarrray);//answer in string

            System.out.println(answer);
            Long other;
            other = Long.parseLong(answer, 2);
            answer = Long.toString(other, 16);
        }
        return "0x" + answer.toUpperCase();
    }

    public String convertUTF16(String toConvert){
        return toConvert;
    }

    public String convertUTF32(String toConvert){
        return toConvert;
    }
}