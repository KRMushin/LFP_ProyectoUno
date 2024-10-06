package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.AutomatasCss;

public class AutDeClase_ID {

    enum ESTADO{
        Q0, //COMIENZA CON PUNTO
        Q1,
        Q2,//
        ERROR,// Estado de error
        ACEPTACION // Estado de aceptacion
    }
    public boolean esClaseValida(String lexema){
        ESTADO estadoActual = ESTADO.Q0;

        for (int i = 0; i < lexema.length(); i++) {
            char caracter = lexema.charAt(i);

            switch (estadoActual){
                case Q0:
                    if (caracter == '.' || caracter == '#'){
                        estadoActual = ESTADO.Q1;
                    }else{
                        return false;
                    }
                    break;
                case Q1:
                    if (esLetraMinuscula(caracter)){
                        estadoActual = ESTADO.Q2;
                    }else{
                        return false;
                    }
                    break;
                case Q2:
                    if (esLetraMinuscula(caracter) || Character.isDigit(caracter) || caracter == '-'){
                        estadoActual = ESTADO.Q2;// permanence en este estado
                    }else {
                        return false;
                    }
                    break;
                default:
                    estadoActual = ESTADO.ERROR;
                    break;
            }
        }
            return ESTADO.Q2 == estadoActual; //RETORNA TRU SI ESTA EN ESTE ESTADO

    }

    public boolean esLetraMinuscula(char caracter){
        return caracter >= 'a' && caracter <= 'z';
    }
}
