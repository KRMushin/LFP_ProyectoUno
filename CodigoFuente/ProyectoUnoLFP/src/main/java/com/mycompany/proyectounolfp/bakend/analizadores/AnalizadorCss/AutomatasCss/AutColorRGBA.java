package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.AutomatasCss;

public class AutColorRGBA {
    public boolean esColorValido(String cadena) {
        int indice = 0;
        int tamañoLexema = cadena.length();

        if (tamañoLexema < 5 || !cadena.startsWith("rgba(")) {
            return false;
        }
        indice += 5; // me muevo 5 posiciones saltándome el rgba(

        for (int i = 0; i < 3; i++) {
            int tamañoNumero = evaluarEntero(cadena, indice);
            if (tamañoNumero > 255 || tamañoNumero < 0) {
                return false;
            }
            indice = siguienteIndice(cadena, indice);
            if (i < 2 && (indice >= tamañoLexema || cadena.charAt(indice) != ',')) {
                return false;
            }
            indice++;
        }
        if (indice < tamañoLexema && cadena.charAt(indice) != ')') {
            double alpha = evaluarAFinal(cadena, indice);
            if (alpha == -1.0) {
                return false;
            }
            indice = siguienteIndice(cadena, indice);
        }

        if (indice >= tamañoLexema || cadena.charAt(indice) != ')') {
            return false;
        }

        return true;
    }
    private int evaluarEntero(String cadena, int indice) {
        int longitud = cadena.length();
        int inicio = indice;
        while (indice < longitud && Character.isDigit(cadena.charAt(indice))) {
            indice++;
        }
        if (inicio == indice) {
            return -1; // noo se encontraron dígitos
        }
        try {
            return Integer.parseInt(cadena.substring(inicio, indice));
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    private double evaluarAFinal(String cadena, int indice) {
        int longitud = cadena.length();
        int inicio = indice;
        boolean tienePunto = false;
        while (indice < longitud && (Character.isDigit(cadena.charAt(indice)) || (cadena.charAt(indice) == '.' && !tienePunto))) {
            if (cadena.charAt(indice) == '.') {
                tienePunto = true;
            }
            indice++;
        }
        if (inicio == indice) {
            return -1.0; // no hay digitos xdd
        }
        try {
            return Double.parseDouble(cadena.substring(inicio, indice));
        } catch (NumberFormatException e) {
            return -1.0;
        }
    }
    private int siguienteIndice(String cadena, int indice) {
        int longitud = cadena.length();
        while (indice < longitud && (cadena.charAt(indice) == ' ' || cadena.charAt(indice) == ',')) {
            indice++;
        }
        return indice;
    }
}
