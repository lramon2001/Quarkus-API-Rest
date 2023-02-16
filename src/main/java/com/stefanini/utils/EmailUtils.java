package com.stefanini.utils;

public class EmailUtils {

    public static String extrairDominioEmail(String email) {
        String dominio = email.substring(email.indexOf("@") + 1);
        return dominio;
    }
    
}
