package com.page.page.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class GeneratePasswordUtil {
    public String generatePassword( String password, String securityKey ) {

        byte[] passwordBytes 		= null;
        MessageDigest sha512 		= null;
        byte[] resultBytes 			= null;
        StringBuilder resultBuffer 	= new StringBuilder();
        String result 				= null;

        try {

            passwordBytes 	= password.getBytes(StandardCharsets.UTF_8);
            sha512 			= MessageDigest.getInstance("SHA-512");
            sha512.reset();
            sha512.update(passwordBytes);
            resultBytes = sha512.digest(securityKey.getBytes(StandardCharsets.UTF_8));

            for (int i = 0; i < resultBytes.length; i++) {
                resultBuffer.append(Integer.toString((resultBytes[i] & 0xf0) >> 4, 16));
                resultBuffer.append(Integer.toString(resultBytes[i] & 0x0f, 16));
            }

            result = resultBuffer.toString();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;

    }
}
