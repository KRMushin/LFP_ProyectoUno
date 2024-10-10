package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorJavascript.AutomatasJs;

import com.mycompany.proyectounolfp.backend.tokens.Token;

import java.util.Optional;

public class AutValoresNumericos {
    private int nF;
    private int nC;
 /* automata que solo reconoce valores numerocos enteroso o dobles*/
    enum ESTADO{
        Q0,
        Q1,
        Q2,
        Q3,
        ERROR
    }

    public Optional<Token> evaluarNumero(String lexema, int nF, int nC){
        ESTADO estadoActual = ESTADO.Q0;
        this.nF = nF;
        this.nC = nC;

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
            if (caracter == '\n') {
                nF++;
                nC = 1;
            } else {
                nC++;
            }
        }
        if (lexema.equals("-")) {
            return Optional.of(new Token(TipoTokenJs.ARITMETICO_RESTA, lexema, "Javascript", nF, nC));
        } else if (estadoActual == ESTADO.Q1){
            return Optional.of(new Token(TipoTokenJs.NUMERO_ENTERO, lexema, "Javascript", nF, nC));
        }else if (estadoActual == ESTADO.Q2){
            return Optional.of(new Token(TipoTokenJs.NUMERO_DECIMAL, lexema, "Javascript", nF, nC));
        }
        return Optional.empty();
    }

    public int getnF() {
        return nF;
    }

    public void setnF(int nF) {
        this.nF = nF;
    }

    public int getnC() {
        return nC;
    }

    public void setnC(int nC) {
        this.nC = nC;
    }
}
