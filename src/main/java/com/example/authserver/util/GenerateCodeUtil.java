package com.example.authserver.util;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class GenerateCodeUtil {
    private GenerateCodeUtil(){

    }
    public static String generateCode(){
        String code="";
        try{
            SecureRandom random=
                    SecureRandom.getInstanceStrong();
            int c = random.nextInt(9000) + 1000;
            code = String.valueOf(c);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return code;
    }
}
