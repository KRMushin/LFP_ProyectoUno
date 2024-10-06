package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.AutomatasCss;

import java.util.IllegalFormatCodePointException;

public class AutSelecUnivesalesEtiquetas {
    /*  ejemplos de entrada textarea, buttom , section , article */
    enum ESTADO{
        Q0, //estado inicial
        Q1, // Leyendo identificador de la etiquetad
        ERROR,// Estado de error
        ACEPTACION // Estado de aceptacion
    }

    public boolean esSelectorValido(String lexema){
        ESTADO estadoActual = ESTADO.Q0;// estableciendo el estado inicial

        for (int i = 0; i < lexema.length(); i++) {
            char caracter = lexema.charAt(i);

            switch (estadoActual){
                case Q0:
                    if (caracter == '*') {
                        estadoActual = ESTADO.ACEPTACION;
                    } else if (Character.isLetter(caracter)) {
                        estadoActual = ESTADO.Q1;
                    } else {
                        estadoActual = ESTADO.ERROR;
                    }
                    break;
                case Q1:
                    if (Character.isLetter(caracter) || Character.isDigit(caracter) || caracter == '-') {
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
        return estadoActual == ESTADO.ACEPTACION || estadoActual == ESTADO.Q1; //aceptacion para valor universar '*' o etiqueta


    }

}
