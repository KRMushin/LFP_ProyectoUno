package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorJavascript.AutomatasJs;

import com.mycompany.proyectounolfp.backend.tokens.Token;

import java.util.Optional;

public class AutValoresDobles {

    public Optional<Token> clasificarValorDoble(String valor, int fila, int columna){
        String valorString = String.valueOf(valor);
        switch (valorString){
            case "==":
                return Optional.of(new Token(TipoTokenJs.RELACIONAL_IGUAL, String.valueOf(valor), "Javascript",fila,columna));
            case "<=":
                return Optional.of(new Token(TipoTokenJs.RELACIONAL_MENOR_IGUALQUE, String.valueOf(valor), "Javascript",fila,columna));
            case ">=":
                return Optional.of(new Token(TipoTokenJs.RELACIONAL_MAYOR_IGUALQUE, String.valueOf(valor), "Javascript",fila,columna));
            case "!=":
                return Optional.of(new Token(TipoTokenJs.RELACIONAL_DIFERENTE_DE, String.valueOf(valor), "Javascript",fila,columna));
            case "||":
                return Optional.of(new Token(TipoTokenJs.LOGICO_OR, valor, "Javascript",fila,columna));
            case "&&":
                return Optional.of(new Token(TipoTokenJs.LOGICO_AND, valor, "Javascript",fila,columna));
            case "++":
                return Optional.of(new Token(TipoTokenJs.INCREMENTAL_INCREMENTO, valor, "Javascript",fila,columna));
            case "--":
                return Optional.of(new Token(TipoTokenJs.INCREMENTAL_DECREMENTO, valor, "Javascript",fila,columna));
            case "=>":
                return Optional.of(new Token(TipoTokenJs.LAMBDA_EXPRESION, valor, "Javascript",fila,columna));
            case "})":
                return Optional.of(new Token(TipoTokenJs.CIERRE_DOBLE, valor, "Javascript",fila,columna));
            default:
                return Optional.empty();
        }
    }
}
