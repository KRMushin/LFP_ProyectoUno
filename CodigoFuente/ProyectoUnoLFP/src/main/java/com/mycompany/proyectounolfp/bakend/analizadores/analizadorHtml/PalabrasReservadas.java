package com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml;

public enum PalabrasReservadas {
    CLASS("class"),
    ID("id"),
    TYPE("type"),
    NAME("name"),
    HREF("href"),
    ONCLICK("onclick"),
    STYLE("style"),
    PLACEHOLDER("placeholder"),
    REQUIRED("required"),
    EQUAL("=");

    private final String palabra;

    PalabrasReservadas(String palabra) {
        this.palabra = palabra;
    }
    public String getPalabra() {
        return palabra;
    }
    public static boolean esPalabraReservada(String palabra) {
        for (PalabrasReservadas pr : PalabrasReservadas.values()) {
            if (pr.getPalabra().equals(palabra)) {
                return true;
            }
        }
        return false;
    }
    public static boolean esPatronAtributo(String cadena) {
        // comprobar que tenga un signo igual y que no esté al inicio o al final
        int igualIndex = cadena.indexOf('=');
        if (igualIndex == -1 || igualIndex == 0 || igualIndex == cadena.length() - 1) {
            return false; //retornar false si no esta en el lugar correcto el signo igual
        }
        // extraer la palabra reservada y el valor
        String palabra = cadena.substring(0, igualIndex).trim();
        String valor = cadena.substring(igualIndex + 1).trim();

        if (valor.length() < 2 || !(valor.startsWith("\"") && valor.endsWith("\""))) {
            return false; // el valor esta fuera de comillas
        }
        //verificar si la palabra reservada es valida
        return esPalabraReservada(palabra);
    }
}
