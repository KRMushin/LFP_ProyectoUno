package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorJavascript.AutomatasJs;

public class AutIdentificador {
    enum ESTADO {
        Q0,
        Q1,
        ERROR
    }

    public boolean esIdentificadorValido(String string) {
        ESTADO estadoActual = ESTADO.Q0;

        for (int i = 0; i < string.length(); i++) {
            char caracter = string.charAt(i);

            switch (estadoActual) {
                case Q0:
                    if (esLetra(caracter)) {
                        estadoActual = ESTADO.Q1;
                    } else {
                        estadoActual = ESTADO.ERROR;
                    }
                    break;
                case Q1:
                    if (esLetra(caracter) || esDigito(caracter) || caracter == '_') {
                        estadoActual = ESTADO.Q1;
                    } else {
                        estadoActual = ESTADO.ERROR;
                    }
                    break;
                default:
                    estadoActual = ESTADO.ERROR;
                    break;
            }
            if (estadoActual == ESTADO.ERROR) {
                return false;
            }
        }
        return estadoActual == ESTADO.Q1;
    }

    private boolean esDigito(char caracter) {
        return caracter >= '0' && caracter <= '9';
    }

    private boolean esLetra(char caracter) {
        return (caracter >= 'a' && caracter <= 'z') || (caracter >= 'A' && caracter <= 'Z');
    }
}//cierr de clase

