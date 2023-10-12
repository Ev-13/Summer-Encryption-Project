import java.io.IOException;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class Controller {
    @FXML
    private Button encryptButton;
    @FXML
    private Button decryptButton;
    @FXML
    private TextArea plainTextArea;
    @FXML
    private TextArea cipherTextArea;
    private Cipher cipher = new Cipher();

    public void encrypt(ActionEvent event) throws IOException {
        String text = plainTextArea.getText();
        String result = cipher.encryptAndDecryptCommon(text,EncryptOrDecrypt.e);
        String numeralDate = toNumeral(LocalDate.now().getDayOfMonth());
        StringBuilder finalStr = new StringBuilder(numeralDate);
        finalStr.append(result);
        String finish = finalStr.toString();
        cipherTextArea.setText(finish);
    }

    public void decrypt(ActionEvent event) throws IOException {
        String text = cipherTextArea.getText();
        StringBuilder str = new StringBuilder(text);
        for (int i = 0; i< 7; i++){
            str.deleteCharAt(0);
        }
        text = str.toString();
        String result = cipher.encryptAndDecryptCommon(text,EncryptOrDecrypt.d);
        plainTextArea.setText(result);
    }


    private String toNumeral(int date){
        StringBuilder numeralDate = new StringBuilder();
        int tens = date / 10;
        int rem = date % 10;
        int fives = rem / 5;
        rem = rem % 5;
        int count;
        for (count = 0; count < tens; count++){
            numeralDate.append("X");
        }
        count = 0;
        for (count = 0; count < fives; count++){
            numeralDate.append("V");
        }
        count = 0;
        for (count = 0; count < rem; count++){
            numeralDate.append("I");
        }
        rem = 7- numeralDate.length();
        count = 0;
        for (count = 0; count < rem; count++){
            numeralDate.append("0");
        }
        return numeralDate.toString();
    }
}
