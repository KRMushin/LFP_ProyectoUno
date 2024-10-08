package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorJavascript.AutomatasJs;

import com.mycompany.proyectounolfp.backend.tokens.Token;

import java.util.Optional;

public class AutValoresSimples {

    public Optional<Token> clasificarValorSimple(String valor){
        String valorString = String.valueOf(valor);
        switch (valorString){
            case "+":
                return Optional.of(new Token(TipoTokenJs.ARITMETICO_SUMA, String.valueOf(valor)));
            case "-":
                return Optional.of(new Token(TipoTokenJs.ARITMETICO_RESTA, String.valueOf(valor)));
            case "*":
                return Optional.of(new Token(TipoTokenJs.ARITMETICO_MULTIPLICACION, String.valueOf(valor)));
            case "/":
                return Optional.of(new Token(TipoTokenJs.ARITMETICO_DIVISION, String.valueOf(valor)));
            case ">":
                return Optional.of(new Token(TipoTokenJs.RELACIONAL_MAYOR, valor));
            case "<":
                return Optional.of(new Token(TipoTokenJs.RELACIONAL_MENOR, valor));
            case "!":
                return Optional.of(new Token(TipoTokenJs.LOGICO_NEGACION, valor));
            case ")":
                return Optional.of(new Token(TipoTokenJs.PARENTESIS_CERRADO, valor));
            case "(":
                return Optional.of(new Token(TipoTokenJs.PARENTESIS_ABIERTO, valor));
            case "{":
                return Optional.of(new Token(TipoTokenJs.LLAVE_ABIERTA, valor));
            case "}":
                return Optional.of(new Token(TipoTokenJs.LLAVE_CERRADA, valor));
            case "[":
                return Optional.of(new Token(TipoTokenJs.CORCHETE_ABIERTO, valor));
            case "]":
                return Optional.of(new Token(TipoTokenJs.CORCHETE_CERRADO, valor));
            case "=":
                return Optional.of(new Token(TipoTokenJs.RELACIONAL_IGUAL, valor));
            case ".":
                return Optional.of(new Token(TipoTokenJs.PUNTO, valor));
            case ";":
                return Optional.of(new Token(TipoTokenJs.PUNTO_COMA, valor));
            case ",":
                return Optional.of(new Token(TipoTokenJs.COMA, valor));
            case ":":
                return Optional.of(new Token(TipoTokenJs.DOS_PUNTOS, valor));
            default:
                return Optional.empty();
        }
    }

}
