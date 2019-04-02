import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.regex.Pattern;


public class UTFLogic {
    @FXML
    private Button btnOK, btnBack;

    @FXML
    private TextField txtFieldInput;

    @FXML
    private Label lblUTF8, lblUTF16, lblUTF32;
    char[] hexCharArray;

    public void initialize(){
        btnBack.setOnMouseClicked(event -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("MainWindow.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage MainStage = (Stage) btnBack.getScene().getWindow();
            MainStage.setTitle("Main");
            MainStage.setScene(new Scene(root, 600, 400));
            MainStage.setResizable(false);
            MainStage.show();
        });

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
        if (!Pattern.matches("^[a-fA-F0-9]+$", toConvert)){
            return "invalid!";
        } else if (toConvert.length() > 8){
            return "Number is too big!";
        }
        String answer;
        char[] answerarrray;
        int i, j, decimal;
        Long tempX = Long.parseLong(toConvert, 16);

        if(tempX > 0x1FFFFF) {//UTF-8 is only capable of UNICODE input from 0 to 0x1FFFFF
            return "TOO BIG!!";
        }

        int temp = Integer.parseInt(toConvert, 16);
        hexCharArray = Integer.toBinaryString(temp).toCharArray();

//        System.out.println("testing1: " + new String(hexCharArray));
//        System.out.println("testing2: " + hexCharArray.length);
        answer = "00000000000000000000000000000000"; //dummy value
        if(hexCharArray.length <= 7){//format: 0xxxxxxx
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
        } else if(hexCharArray.length <= 11){//format: 110xxxxx 10xxxxxx
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
//            System.out.println(answer);
            decimal = Integer.parseInt(answer, 2);
            answer = Integer.toString(decimal, 16);
        } else if (hexCharArray.length <= 16){//format: 1110xxxx 10xxxxxx 10xxxxxx
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
//            System.out.println(answer);
            decimal = Integer.parseInt(answer, 2);
            answer = Integer.toString(decimal, 16);
        } else if (hexCharArray.length <= 21){//format: 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
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

//            System.out.println(answer);
            Long other;
            other = Long.parseLong(answer, 2);
            answer = Long.toString(other, 16);
        }
        return "0x" + answer.toUpperCase();
    }

    public String convertUTF16(String toConvert){
        if (!Pattern.matches("^[a-fA-F0-9]+$", toConvert)){
            return "invalid!";
        } else if (toConvert.length() > 8){
            return "Number is too big!";
        }
        String answer;
        int difference;
        char[] answerarrray = new char[24];
        int i, j, decimal;
        Long tempX = Long.parseLong(toConvert, 16);

        if(tempX > 0x10FFFF) {//UTF-8 is only capable of UNICODE input from 0 to 0x1FFFFF
            return "TOO BIG!!";
        } else if (tempX < 0x10000){
            return "0x" + String.format("%04x", Integer.parseInt(toConvert, 16)).toUpperCase();
        }

        int temp = Integer.parseInt(toConvert, 16);
        temp = temp - 0x10000;
        hexCharArray = Integer.toBinaryString(temp).toCharArray();

        for (i = hexCharArray.length - 1, j = 23; j >= 0; j--){
            if (i == -1){
                answerarrray[j] = '0';
            } else if(j == 13 || j == 12) {
                answerarrray[j] = '0';
            } else {
                answerarrray[j] = hexCharArray[i];
                i--;
            }
        }
        System.out.println(new String(answerarrray));

        char[] highSurrogate = Arrays.copyOfRange(answerarrray, 0, 12);
        char[] lowSurrogate = Arrays.copyOfRange(answerarrray,12, 24);

        String highSurr = new String(highSurrogate);
        String lowSurr = new String(lowSurrogate);

        Long hold; // long was used as apparently 2^10 - 1 cannot be represented in integer
        hold = Long.parseLong(highSurr, 2) + 0xD800;
        highSurr = Long.toString(hold, 16);

        hold = Long.parseLong(lowSurr, 2) + 0xDC00;
        lowSurr = Long.toString(hold, 16);

        answer = highSurr + lowSurr;
        return "0x" + answer.toUpperCase();
    }

    public String convertUTF32(String toConvert){
        if (!Pattern.matches("^[a-fA-F0-9]+$", toConvert)){
            return "invalid!";
        } else if (toConvert.length() > 8){
            return "Number is too big!";
        }

        Long tempX = Long.parseLong(toConvert, 16);

        if(tempX > 0x10FFFF) {//UTF-32 is only capable of UNICODE input from 0 to 0x1FFFFF
            return "0x" + String.format("%08x", tempX).toUpperCase();
        } else {
            return "0x" + String.format("%08x", Integer.parseInt(toConvert, 16)).toUpperCase();
        }
    }
}
