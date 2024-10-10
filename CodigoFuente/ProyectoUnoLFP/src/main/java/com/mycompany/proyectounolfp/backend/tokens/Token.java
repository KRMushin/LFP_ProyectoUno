package com.mycompany.proyectounolfp.backend.tokens;

import com.mycompany.proyectounolfp.backend.util.ExpresionRegular;

public class Token {

    private Object tipoToken;
    private String lexema;
    private String contexto;// representa el contexto html, css, js
    private ExpresionRegular expRegular;
    private int fila;
    private int columna;

    public Token(){
    }
    public Token(Object tipoToken ,  String lexema  , String contexto, int fila, int columna) {
        this.contexto = contexto;
        this.lexema = lexema;
        this.tipoToken = tipoToken;
        this.expRegular = new ExpresionRegular();
        this.fila = fila;
        this.columna = columna - lexema.length();
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

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public Object getExpresionRegular() {
        if (contexto.equalsIgnoreCase("javascript")) {
            return expRegular.obtenerExpresionJS(this.tipoToken.toString(), this.lexema);
        } else if (contexto.equalsIgnoreCase("css")) {
            return expRegular.obtenerExpresionCSS(this.tipoToken.toString(), this.lexema);
        } else if (contexto.equalsIgnoreCase("optimizacion")) {
            return expRegular.obtenerExpresionOptimizar(this.tipoToken.toString(), this.lexema);

        } else {
            return this.lexema;
        }
    }
}
