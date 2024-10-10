package com.mycompany.proyectounolfp.backend.util;

public class ExpresionRegular {

    public String obtenerExpresionJS(String string, String token) {
        switch (string.toUpperCase()) {
            case "IDENTIFICADOR":
                return "[a-zA-Z]([a-zA-Z] | [0-9] | [ _ ])*";
            case "NUMERO_ENTERO":
                return  "[0-9]+";
            case "NUMERO_DECIMAL":
                return "[0-9]+.[0-9]+";
            default:
                return token;
        }
    }

    public Object obtenerExpresionCSS(String string, String lexema) {
        switch (string.toUpperCase()) {
            case "DE_ID":
                return "#[a-z]+ [0-9]* (- ([a-z] | [0-9])+)*";
            case "DE_CLASE", "IDENTIFICADOR":
                return "[a-z]+ [0-9]* (- ([a-z] | [0-9])+)*";
            case "ENTERO":
                return "[0-9]+";
            default:
                return lexema;
        }
    }

    public Object obtenerExpresionOptimizar(String string, String lexema) {

        switch (string.toUpperCase()) {
            case "COMENTARIO":
                return "// [a-zA-Z]|[0-9]|[.]";
            default:
                return lexema;
        }
    }
}
