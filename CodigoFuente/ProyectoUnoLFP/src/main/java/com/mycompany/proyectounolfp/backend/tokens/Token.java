package com.mycompany.proyectounolfp.backend.tokens;

import com.mycompany.proyectounolfp.backend.util.ExpresionRegular;

public class Token {

    private Object tipoToken;
    private String lexema;
    private String contexto;
    private ExpresionRegular expRegular;// representa el contexto html, css, js

    public Token(){
    }
    public Token(Object tipoToken ,  String lexema  , String contexto) {
        this.contexto = contexto;
        this.lexema = lexema;
        this.tipoToken = tipoToken;
        this.expRegular = new ExpresionRegular();
    }

    public Object getTipoToken() {
        return tipoToken;
    }

    public void setTipoToken(Object tipoToken) {
        this.tipoToken = tipoToken;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }

    public Object getExpresionRegular() {
        if (contexto.equalsIgnoreCase("javascript")) {
            return expRegular.obtenerExpresionJS(this.tipoToken.toString(), this.lexema);
        } else if (contexto.equalsIgnoreCase("css")) {
            return expRegular.obtenerExpresionCSS(this.tipoToken.toString(), this.lexema);
        } else {
            return this.lexema;
        }
    }
}
