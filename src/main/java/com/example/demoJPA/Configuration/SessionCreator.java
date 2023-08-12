package com.example.demoJPA.Configuration;

import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.UUID;
import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.Base64;

public class SessionCreator {
    private static final String ENCRYPTION_ALGORITHM = "AES";
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static  SecretKey key =null;



    // Encrypts a string by XOR-ing each character with a key
    public static String encrypt(String input, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(input.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static String decrypt(String encryptedInput, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedInput));
        return new String(decryptedBytes);
    }

    // Extracts the date from the decrypted string and checks if it's passed the current time
    public static boolean hasDatePassed(String decryptedDate) throws Exception{
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(decryptedDate);
            Date currentDate = new Date();
            return parsedDate.before(currentDate);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String createSession() throws Exception {
        String guid = UUID.randomUUID().toString();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.HOUR_OF_DAY, 10);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = dateFormat.format(calendar.getTime());

        // Encryption key (use a constant for simplicity, not secure)


        // Encrypt the combined string (GUID + date)
        String inputToEncrypt = guid + "|" + dateString; KeyGenerator keyGenerator = KeyGenerator.getInstance(ENCRYPTION_ALGORITHM);
        keyGenerator.init(128); // 128 bits for AES
        if(key == null){
            keyGenerator.init(128); // 128 bits for AES
            key = keyGenerator.generateKey();
        }

        return encrypt(inputToEncrypt, key);
    }

    public static boolean checkSession(String encrypted) {
        try {
            if (key==null)return true;
            String decrypted = decrypt(encrypted, key);


            // Extract the date from the decrypted string and check if it has passed the current time
            String decryptedDate = decrypted.split("\\|")[1];
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


            return hasDatePassed(decryptedDate);
        } catch (Exception e) {
            return true;
        }
    }
}



