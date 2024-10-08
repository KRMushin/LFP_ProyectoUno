package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorJavascript.AutomatasJs;

import com.mycompany.proyectounolfp.backend.tokens.Token;

import java.util.Optional;

public class AutValoresNumericos {
 /* automata que solo reconoce valores numerocos enteroso o dobles*/
    enum ESTADO{
        Q0,
        Q1,
        Q2,
        Q3,
        ERROR
    }

    public Optional<Token> evaluarNumero(String lexema){
        ESTADO estadoActual = ESTADO.Q0;

        for (int i = 0; i < lexema.length(); i++) {
            char caracter = lexema.charAt(i);

            switch (estadoActual){
                case Q0:
                    if (Character.isDigit(caracter) || caracter == '-'){
                        estadoActual = ESTADO.Q1;
                    } else {
                        estadoActual = ESTADO.ERROR;
                    }
                    break;
                case Q1:
                    if (Character.isDigit(caracter)){
                        estadoActual = ESTADO.Q1; // se mantiene en este estado
                    } else if (caracter == '.'){
                        estadoActual = ESTADO.Q2;
                    } else {
                        estadoActual = ESTADO.ERROR;
                    }
                    break;
                case Q2:
                    if (Character.isDigit(caracter)){
                        estadoActual = ESTADO.Q2; // se mantiene en este estado
                    } else {
                        estadoActual = ESTADO.ERROR;
                    }
                    break;
                case ERROR:
                    return Optional.empty();
            }
        }
        if (lexema.equals("-")) {
            return Optional.of(new Token(TipoTokenJs.ARITMETICO_RESTA, lexema));
        } else if (estadoActual == ESTADO.Q1){
            return Optional.of(new Token(TipoTokenJs.NUMERO_ENTERO, lexema));
        }else if (estadoActual == ESTADO.Q2){
            return Optional.of(new Token(TipoTokenJs.NUMERO_DECIMAL, lexema));
        }
        return Optional.empty();}
}
