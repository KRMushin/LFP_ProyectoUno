package com.mycompany.proyectounolfp.backend.tokens;

public class Token {

    private Object tipoToken;
    private String lexema;
    private String contexto; // representa el contexto html, css, js

    public Token(){
    }
    public Token(Object tipoToken ,  String lexema  ) {
        this.contexto = contexto;
        this.lexema = lexema;
        this.tipoToken = tipoToken;
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
}
