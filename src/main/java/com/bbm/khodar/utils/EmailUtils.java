package com.bbm.khodar.utils;

public class EmailUtils {

    public static String getEmailMessage(String name, String eventName, String host) {
        return "Olá " + name + ",\n\nO seu ticket para o evento: "+ eventName
                +" foi criado com sucesso." +
                "\n\nA equipe de Suporte.";
    }
}
