package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorJavascript.AutomatasJs;

import com.mycompany.proyectounolfp.backend.tokens.Token;

import java.util.Optional;

public class AutBooleano {

    public Optional<Token> evaluarBooleano(String lexema,int nF, int nC){
        if (lexema.equals("true") || lexema.equals("false")){
            return Optional.of(new Token(TipoTokenJs.BOOLEANO, lexema, "Javascript", nF, nC));
        }
        return Optional.empty();
    }
}
