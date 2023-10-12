import java.time.*;

public class Cipher {
    private int key;

    public Cipher(){
        key = LocalDate.now().getDayOfMonth();
    }

    public int stringToInt(char character) {
        int index = character - 32;
        return index;
    }


    public String encryptAndDecryptCommon(String message, EncryptOrDecrypt enOrDe){
        StringBuilder str = new StringBuilder();
        for (int c = 0; c< message.length(); c++){
            int currentValue = stringToInt(message.charAt(c));
            if (currentValue < 96 && currentValue > -1){
                int newInt;
                if (enOrDe == EncryptOrDecrypt.e){
                    newInt = (key + currentValue) % 95;
                }else{
                    newInt = Math.floorMod((currentValue - key), 95);
                }
                newInt = newInt + 32;
                char newChar = (char) newInt;
                str.append(newChar);
            }
        }
        return str.toString();
    }
}
