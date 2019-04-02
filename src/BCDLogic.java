import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class BCDLogic {
    @FXML
    private Button btnOK, btnBack;

    @FXML
    private TextField txtFieldInput;

    @FXML
    private Label lblUBCD, lblPBCD, lblDPBCD;
    private int dig1, dig2, dig3, size;
    private String bin1, bin2, bin3, uBCD, pBCD, dPBCD;
    private char[] binaryArray;
    private ArrayList<Character> aEI;

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
            dig1 = 0;
            dig2 = 0;
            dig3 = 0;
            System.out.println(txtFieldInput.getText());
            String text = txtFieldInput.getText();
            size = text.length();
            if(text.isEmpty()){
                System.out.println("Error!");
                lblUBCD.setText("ERROR");
                lblPBCD.setText("ERROR");
                lblDPBCD.setText("ERROR");
            }
            if(text.length()>3){
                System.out.println("Error!");
                lblUBCD.setText("Input 3 digits only please");
                lblPBCD.setText("Input 3 digits only please");
                lblDPBCD.setText("Input 3 digits only please");
            }
            else {
                lblUBCD.setText("");
                lblPBCD.setText("");
                lblDPBCD.setText("");
                lblUBCD.setText(convertUBCD(text));
                lblPBCD.setText(convertPBCD(text));
                lblDPBCD.setText(convertDPBCD(text));
            }
        });

    }

    public String convertUBCD(String toConvert){
        if(toConvert.length() == 1) {
            dig3 = Character.getNumericValue(toConvert.charAt(0));
        }
        else if (toConvert.length() == 2){
            dig2 = Character.getNumericValue(toConvert.charAt(0));
            dig3 = Character.getNumericValue(toConvert.charAt(1));
        }
        else if (toConvert.length() == 3){
            dig1 = Character.getNumericValue(toConvert.charAt(0));
            dig2 = Character.getNumericValue(toConvert.charAt(1));
            dig3 = Character.getNumericValue(toConvert.charAt(2));
        }
        bin1 = to8Binary(dig1);
        bin2 = to8Binary(dig2);
        bin3 = to8Binary(dig3);
        if (size == 3) {
            uBCD = bin1.concat(" ").concat(bin2).concat(" ").concat(bin3);
        }
        else if (size == 2) {
            uBCD = bin2.concat(" ").concat(bin3);
        }
        else if (size == 1) {
            uBCD = bin3;
        }
        return uBCD;
    }

    public String convertPBCD(String toConvert){
//        dig1=Character.getNumericValue(toConvert.charAt(0));
//        dig2=Character.getNumericValue(toConvert.charAt(1));
//        dig3=Character.getNumericValue(toConvert.charAt(2));

        bin1 = to4Binary(dig1);
        System.out.println(bin1);
        bin2 = to4Binary(dig2);
        System.out.println(bin2);
        bin3 = to4Binary(dig3);
        System.out.println(bin3);

        pBCD = bin1.concat(" ").concat(bin2).concat(" ").concat(bin3);
        binaryArray = pBCD.toCharArray();
        return pBCD;
    }

    public String convertDPBCD(String toConvert){
        aEI = new ArrayList<>();
        ArrayList<Character> dPBCDList = new ArrayList<>();
        ArrayList<Character> binaryString =  new ArrayList<>();
        binaryString = arrayFunc(binaryArray);
        String aEIString;
        aEI.add(binaryString.get(0));
        aEI.add(binaryString.get(4));
        aEI.add(binaryString.get(8));
        int x=0, z;
        if(checkBin(aEI) == 1){
            for(z=0;z<binaryString.size();z++) {
                if (z == 8 || z % 4 != 0) {
//                    if (x > 0 && x % 3 == 0)
//                        dPBCDList.add(' ');
                    dPBCDList.add(binaryString.get(z));
                }
//                x++;
            }
        }
        else if(checkBin(aEI)==2){
            for(z=0;z<binaryString.size();z++) {
                if(z==8 || z==9 || z==10) {
                    if(z==8)
                        dPBCDList.add('1');
                    else
                        dPBCDList.add('0');
                }
                if ((z % 4 != 0) && z != 9 && z != 10) {
                    dPBCDList.add(binaryString.get(z));
                }
            }
        }
        else if(checkBin(aEI)==3){
            for(z=0;z<binaryString.size();z++) {
                if(z==8 || z==9 || z==10) {
                    if(z==8 && z==10)
                        dPBCDList.add('1');
                    else
                        dPBCDList.add('0');
                }
                if ((z % 4 != 0) && z != 9 && z != 10) {
                    if(z==5 || z==6){
                        if (z==5)
                            dPBCDList.add(binaryString.get(5));
                        else
                            dPBCDList.add(binaryString.get(6));
                    }
                else
                    dPBCDList.add(binaryString.get(z));
                }
            }
        }
        else if(checkBin(aEI)==4){
            for(z=0;z<binaryString.size();z++) {
                if(z==8 || z==9 || z==10) {
                    dPBCDList.add('1');
                }
                if ((z % 4 != 0) && z != 9 && z != 10) {
                    if(z==5 || z==6){
                        if (z==5)
                            dPBCDList.add('1');
                        else
                            dPBCDList.add('0');
                    }
                else
                    dPBCDList.add(binaryString.get(z));
                }
            }
        }
        else if(checkBin(aEI)==5){
            for(z=0;z<binaryString.size();z++) {
                if(z==8 || z==9 || z==10) {
                    if(z==10)
                        dPBCDList.add('0');
                    else
                        dPBCDList.add('1');
                }
                if ((z % 4 != 0) && z != 9 && z != 10) {
                    if(z==1 || z==2){
                        if (z==1)
                            dPBCDList.add(binaryString.get(9));
                        else
                            dPBCDList.add(binaryString.get(10));
                    }
                else
                    dPBCDList.add(binaryString.get(z));
                }
            }
        }
        else if(checkBin(aEI)==6){
            for(z=0;z<binaryString.size();z++) {
                if(z==8 || z==9 || z==10) {
                    dPBCDList.add('1');
                }
                if ((z % 4 != 0) && z != 9 && z != 10) {
                    if(z==1 || z==2){
                        if (z==1)
                            dPBCDList.add(binaryString.get(5));
                        else
                            dPBCDList.add(binaryString.get(6));
                    }
                    else if(z==5 || z==6){
                        if(z==6)
                            dPBCDList.add('0');
                        else
                            dPBCDList.add('1');
                    }
                    else
                        dPBCDList.add(binaryString.get(z));
                }
            }
        }
        else if(checkBin(aEI)==7){
            for(z=0;z<binaryString.size();z++) {
                if(z==8 || z==9 || z==10) {
                    dPBCDList.add('1');
                }
                if ((z % 4 != 0) && z != 9 && z != 10) {
                    if(z==1 || z==2){
                        if (z==1)
                            dPBCDList.add(binaryString.get(9));
                        else
                            dPBCDList.add(binaryString.get(10));
                    }
                    else if(z==5 || z==6){
                        dPBCDList.add('0');
                    }
                    else
                        dPBCDList.add(binaryString.get(z));
                }
            }
        }
        else if(checkBin(aEI)==8){
            for(z=0;z<binaryString.size();z++) {
                if(z==8 || z==9 || z==10) {
                    dPBCDList.add('1');
                }
                if ((z % 4 != 0) && z != 9 && z != 10) {
                    if(z==1 || z==2){
                        dPBCDList.add('0');
                    }
                    else if(z==5 || z==6){
                        dPBCDList.add('1');
                    }
                    else
                        dPBCDList.add(binaryString.get(z));
                }
            }
        }
        StringBuilder string = new StringBuilder();
        int y=0;
        for(z=0;z<dPBCDList.size();z++){
            y++;
            if(z%3==0 && z>0 && y<8){
                string.append(" ");
            }
            string.append(dPBCDList.get(z));
        }
        dPBCD=string.toString();
        return dPBCD;
    }

    public ArrayList<Character> arrayFunc(char[] chars){
        ArrayList<Character> z= new ArrayList<>();
        for(int i=0;i<chars.length;i++){
            if(chars[i] != ' ')
                z.add(chars[i]);
        }
        return z;
    }

    public String to8Binary(int convertThis){
        switch (convertThis){
            case 1:
                return "0000 0001";
            case 2:
                return "0000 0010";
            case 3:
                return "0000 0011";
            case 4:
                return "0000 0100";
            case 5:
                return "0000 0101";
            case 6:
                return "0000 0110";
            case 7:
                return "0000 0111";
            case 8:
                return "0000 1000";
            case 9:
                return "0000 1001";
            default:
                return "0000 0000";
        }
    }
    public String to4Binary(int convertThis){
        switch (convertThis){
            case 1:
                return "0001";
            case 2:
                return "0010";
            case 3:
                return "0011";
            case 4:
                return "0100";
            case 5:
                return "0101";
            case 6:
                return "0110";
            case 7:
                return "0111";
            case 8:
                return "1000";
            case 9:
                return "1001";
            default:
                return "0000";
        }
    }
    public int checkBin(ArrayList<Character> list){
        switch (list.toString()){
            case "001":
                return 2;
            case "010":
                return 3;
            case "011":
                return 4;
            case "100":
                return 5;
            case "101":
                return 6;
            case "110":
                return 7;
            case "111":
                return 8;
            default:
                return 1;
        }
    }
}
