package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorJavascript.AutomatasJs;

import com.mycompany.proyectounolfp.backend.tokens.Token;

import java.util.Optional;

public class AutValoresSimples {

    public Optional<Token> clasificarValorSimple(String valor, int fila, int columna){
        String valorString = String.valueOf(valor);
        switch (valorString){
            case "+":
                return Optional.of(new Token(TipoTokenJs.ARITMETICO_SUMA, String.valueOf(valor), "Javascript",fila,columna));
            case "-":
                return Optional.of(new Token(TipoTokenJs.ARITMETICO_RESTA, String.valueOf(valor), "Javascript",fila,columna));
            case "*":
                return Optional.of(new Token(TipoTokenJs.ARITMETICO_MULTIPLICACION, String.valueOf(valor), "Javascript",fila,columna));
            case "/":
                return Optional.of(new Token(TipoTokenJs.ARITMETICO_DIVISION, String.valueOf(valor), "Javascript",fila,columna));
            case ">":
                return Optional.of(new Token(TipoTokenJs.RELACIONAL_MAYOR, valor, "Javascript",fila,columna));
            case "<":
                return Optional.of(new Token(TipoTokenJs.RELACIONAL_MENOR, valor, "Javascript",fila,columna));
            case "!":
                return Optional.of(new Token(TipoTokenJs.LOGICO_NEGACION, valor, "Javascript",fila,columna));
            case ")":
                return Optional.of(new Token(TipoTokenJs.PARENTESIS_CERRADO, valor, "Javascript",fila,columna));
            case "(":
                return Optional.of(new Token(TipoTokenJs.PARENTESIS_ABIERTO, valor, "Javascript",fila,columna));
            case "{":
                return Optional.of(new Token(TipoTokenJs.LLAVE_ABIERTA, valor, "Javascript",fila,columna));
            case "}":
                return Optional.of(new Token(TipoTokenJs.LLAVE_CERRADA, valor, "Javascript",fila,columna));
            case "[":
                return Optional.of(new Token(TipoTokenJs.CORCHETE_ABIERTO, valor, "Javascript",fila,columna));
            case "]":
                return Optional.of(new Token(TipoTokenJs.CORCHETE_CERRADO, valor, "Javascript",fila,columna));
            case "=":
                return Optional.of(new Token(TipoTokenJs.RELACIONAL_IGUAL, valor, "Javascript",fila,columna));
            case ".":
                return Optional.of(new Token(TipoTokenJs.PUNTO, valor, "Javascript",fila,columna));
            case ";":
                return Optional.of(new Token(TipoTokenJs.PUNTO_COMA, valor, "Javascript",fila,columna));
            case ",":
                return Optional.of(new Token(TipoTokenJs.COMA, valor, "Javascript",fila,columna));
            case ":":
                return Optional.of(new Token(TipoTokenJs.DOS_PUNTOS, valor, "Javascript",fila,columna));
            default:
                return Optional.empty();
        }
    }

}
