import java.io.*;
import java.time.LocalDate;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private Button encryptButton;
    @FXML
    private Button decryptButton;
    @FXML
    private TextArea plainTextArea;
    @FXML
    private TextArea cipherTextArea;
    @FXML
    private TextField plainFilename;
    @FXML
    private TextField cipherFilename;


    private Cipher cipher = new Cipher();

    public void encrypt(ActionEvent event) throws IOException {
        String text = plainTextArea.getText();
        plainTextArea.clear();
        String result = cipher.encryptAndDecryptCommon(text,EncryptOrDecrypt.e, 0);
        String numeralDate = toNumeral(LocalDate.now().getDayOfMonth());
        StringBuilder finalStr = new StringBuilder(numeralDate);
        finalStr.append(result);
        String finish = finalStr.toString();
        cipherTextArea.clear();
        cipherTextArea.setText(finish);
    }

    public void decrypt(ActionEvent event) throws IOException {
        String text = cipherTextArea.getText();
        cipherTextArea.clear();
        StringBuilder str = new StringBuilder(text);
        int date_key = 0;
        for (int i = 0; i< 7; i++){
            if(str.charAt(0) == 'X'){
                date_key += 10;
            } else if (str.charAt(0) == 'V') {
                date_key += 5;
            } else if (str.charAt(0) == 'I') {
                date_key += 1;
            }
            str.deleteCharAt(0);
        }
        text = str.toString();
        String result = cipher.encryptAndDecryptCommon(text,EncryptOrDecrypt.d, date_key);
        plainTextArea.clear();
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


    public void loadPlain(ActionEvent event) throws IOException {
        String filename = plainFilename.getText();
        String text;
        try (FileReader filereader = new FileReader(filename);
             BufferedReader bufferedreader = new BufferedReader(filereader);
             Scanner in = new Scanner(bufferedreader)) {
            in.useDelimiter("\r?\n|\r");
            StringBuilder str = new StringBuilder();
            while (in.hasNextLine()) {
                str.append(in.nextLine());
            }
            text = str.toString();
            if (text.isEmpty()) {
                System.out.println("There is nothing in the plain text file");
            } else {
                plainTextArea.setText(text);
                plainFilename.clear();
            }
        } catch (IOException e) {
            System.out.println("Error: Plain text file not found");
        }

    }

    public void savePlain(ActionEvent event) throws IOException {
        String filename = plainFilename.getText();
        String text = plainTextArea.getText();
        try (FileWriter fw = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            if (!text.isEmpty()) {
                pw.println(text);
                pw.close();
                plainTextArea.clear();
            } else {
                pw.close();
            }
        } catch (IOException e) {
            System.out.println("Error: Plain text file not found.");
        }

    }

    public void loadCipher(ActionEvent event) throws IOException {
        String filename = cipherFilename.getText();
        String text;
        try (FileReader filereader = new FileReader(filename);
             BufferedReader bufferedreader = new BufferedReader(filereader);
             Scanner in = new Scanner(bufferedreader)) {
            in.useDelimiter("\r?\n|\r");
            StringBuilder str = new StringBuilder();
            while (in.hasNextLine()) {
                str.append(in.nextLine());
            }
            text = str.toString();
            if (text.isEmpty()) {
                System.out.println("There is nothing in the cipher text file");
            } else {
                cipherTextArea.setText(text);
                cipherFilename.clear();
            }
        } catch (IOException e) {
            System.out.println("Error: Cipher text file not found");
        }
    }

    public void saveCipher(ActionEvent event) throws IOException {
        String filename = cipherFilename.getText();
        String text = cipherTextArea.getText();
        try (FileWriter fw = new FileWriter(filename);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            if (!text.isEmpty()) {
                pw.println(text);
                pw.close();
                cipherTextArea.clear();
            } else {
                pw.close();
            }
        } catch (IOException e) {
            System.out.println("Error: Cipher text file not found.");
        }

    }
}
