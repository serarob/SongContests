/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package encryption;

import java.security.Key;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author nikolaos
 */
public class CryptoConverter {

    private static final String ALGORITHM = "AES/ECB/PKCS5Padding";
    private static final byte[] KEY = "NsP#ikr0Ol$1697!".getBytes(); //16 bytes length allowable AES Key length

    public static String encrypt(String pwd) {
      // do some encryption
      Key key = new SecretKeySpec(KEY, "AES");
      try {
         Cipher c = Cipher.getInstance(ALGORITHM);
         c.init(Cipher.ENCRYPT_MODE, key);
         return new String(Base64.getEncoder().encode(c.doFinal(pwd.getBytes())));
      } catch (Exception e) {
         throw new RuntimeException(e);
      }
    }

    public static String decrypt(String dbData) {
      // do some decryption
      Key key = new SecretKeySpec(KEY, "AES");
      try {
        Cipher c = Cipher.getInstance(ALGORITHM);
        c.init(Cipher.DECRYPT_MODE, key);
        return new String(c.doFinal(Base64.getDecoder().decode(dbData)));
      } catch (Exception e) {
        throw new RuntimeException(e);
      }
    }
    
}
