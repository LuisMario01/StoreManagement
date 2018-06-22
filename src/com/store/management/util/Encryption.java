package com.store.management.util;

import java.security.Key;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Encryption {
	
	private static String key = "Bar12345Bar12345";
	
	public static String encrypt(String text) {
		try 
        {
         // Create key and cipher
         Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
         Cipher cipher = Cipher.getInstance("AES");
         // encrypt the text
         cipher.init(Cipher.ENCRYPT_MODE, aesKey);
         byte[] encrypted = cipher.doFinal(text.getBytes());

         StringBuilder sb = new StringBuilder();
         for (byte b: encrypted) {
             sb.append((char)b);
         }

         // the encrypted String
         String enc = sb.toString();
         return enc;
         
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        	return null;
        }
	}
	
	public static String decrypt(String enc) {
		try {
			Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
			Cipher cipher = Cipher.getInstance("AES");
			byte[] bb = new byte[enc.length()];
            for (int i=0; i<enc.length(); i++) {
                bb[i] = (byte) enc.charAt(i);
            }

            // decrypt the text
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            String decrypted = new String(cipher.doFinal(bb));
            System.err.println("decrypted:" + decrypted);
            return decrypted;
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}

}
