/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml;

import com.mycompany.proyectounolfp.backend.Secciones.Seccion;
import com.mycompany.proyectounolfp.backend.tokens.Token;
import com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml.Automatas.AutEtiqueta;

import java.util.*;

/**
 *
 * @author kevin-mushin
 */
public class AnalizadorHtml {
    private AutEtiqueta autEtiqueta;

    public AnalizadorHtml() {
        this.autEtiqueta = new AutEtiqueta();
    }
    private enum Estado {
        Q0,//INICIO
        Q1, // LECTURA ETIQUETA
        Q2, // LECTURA ATRIBUTOS
        Q3 // LECTURA TEXTO
    }
    private Estado estadoActual;

    public List<Token> analizarSeccion(Seccion seccion) {
        List<Token> tokensHtml = new ArrayList<>();
        StringBuilder lexemaActual = new StringBuilder();
        Estado estadoActual = Estado.Q0;
        String contenido = seccion.getContenido();

        for (int i = 0; i < contenido.length(); i++) {
            char caracter = contenido.charAt(i);

            switch (estadoActual) {
                case Q0:
                    if (caracter == '<') {
                        if (lexemaActual.length() > 0) {
                            if (lexemaActual.length() > 0 && !lexemaActual.toString().trim().isEmpty()) {
                            tokensHtml.add(new Token(TIpoTokenHtml.TEXTO, lexemaActual.toString(), "Html"));
                            lexemaActual.setLength(0);
                            }
                        }
                        lexemaActual.append(caracter);
                        estadoActual = Estado.Q1;
                    } else {
                        lexemaActual.append(caracter);
                    }
                    break;

                case Q1:
                    if (caracter != '>') {
                        lexemaActual.append(caracter);
                    } else {
                        lexemaActual.append(caracter);
                        Optional<List<Token>> tokensOpt = obtenerTokens(lexemaActual.toString());

                        if (tokensOpt.isPresent()) {
                            tokensHtml.addAll(tokensOpt.get());
                        } else {
                            tokensHtml.add(new Token(TIpoTokenHtml.ERROR, lexemaActual.toString(), "Html"));
                        }
                        lexemaActual.setLength(0);
                        estadoActual = Estado.Q0;
                    }
                    break;

                case Q2: //texto
                    if (caracter == '<') {
                        if (lexemaActual.length() > 0) {
                            if (lexemaActual.length() > 0 && !lexemaActual.toString().trim().isEmpty()) {
                                tokensHtml.add(new Token(TIpoTokenHtml.TEXTO, lexemaActual.toString(), "Html"));
                                lexemaActual.setLength(0);
                            }
                        }
                        lexemaActual.append(caracter);
                        estadoActual = Estado.Q1;  // cambiar al estado de lectura de etiqueta
                    } else {
                        lexemaActual.append(caracter);
                    }
                    break;
            }
        }

        if (lexemaActual.length() > 0) {
            tokensHtml.add(new Token(TIpoTokenHtml.TEXTO, lexemaActual.toString(), "Html"));
        }

        return tokensHtml;
    }

    private Optional<List<Token>> obtenerTokens(String etiqueta) {
        return autEtiqueta.obtenerTokens(etiqueta);
    }
}
/*
public List<Token> analizarSeccion(Seccion seccion) {
        List<Token> tokens = new ArrayList<>();
        String contenido = seccion.getContenido();
        StringBuilder tokenBuilder = new StringBuilder();

        for (int i = 0; i < contenido.length(); i++) {
            char caracter = contenido.charAt(i);

            switch (estadoActual){
                case Q0:
                    if (caracter == '<'){
                        if (!tokenBuilder.isEmpty()) { // si el builder no esta vacio se toma como texto
                            tokens.add(new Token(TIpoTokenHtml.TEXTO, tokenBuilder.toString(), "Html"));
                            tokenBuilder.setLength(0);
                        }
                        tokenBuilder.append(caracter);
                        estadoActual = Estado.Q1;
                    }else if(Character.isLetter(caracter)){
                        tokenBuilder.append(caracter);
                        estadoActual = Estado.Q3;//posible texto en la seccion
                    }else{
                        tokenBuilder.append(caracter);
                    }
                    break;
                case Q1:
                    if (Character.isLetterOrDigit(caracter) || caracter == '=' || caracter == '\"' || caracter == ' ' || caracter == '/'){
                        tokenBuilder.append(caracter);
                        estadoActual = Estado.Q1; // mantenerse en este estado

                    }else if (caracter == '>'){ // cierre de etiqueta
                        tokenBuilder.append(caracter);


                        if (esTokenCorrecto(tokenBuilder.toString())){
                                if(tokenBuilder.charAt(1) == '/'){
                                    tokens.add(new Token(TIpoTokenHtml.ETIQUETA_CIERRE, tokenBuilder.toString(), "Html"));
                                }else if (tokenBuilder.toString().trim().endsWith("/>") && !tokenBuilder.toString().startsWith("</")) {
                                    tokens.add(new Token(TIpoTokenHtml.ETIQUETA_UNA_LINEA, tokenBuilder.toString(), "Html"));
                                }
                                else{
                                    tokens.add(new Token(TIpoTokenHtml.ETIQUETA_APERTURA, tokenBuilder.toString(), "Html"));
                                }
                                tokenBuilder.setLength(0);
                        }else {
                            tokens.add(new Token(TIpoTokenHtml.ERROR, tokenBuilder.toString(), "Html"));
                            tokenBuilder.setLength(0);
                        }
                        estadoActual = Estado.Q0;
                    }
                    break;
            case Q3:
                    if (caracter == '<') {
                        if (!tokenBuilder.isEmpty()) {
                            tokens.add(new Token(TIpoTokenHtml.TEXTO, tokenBuilder.toString(), "Html"));
                            tokenBuilder.setLength(0);
                        }
                        tokenBuilder.append(caracter);
                        estadoActual = Estado.Q1;
                    } else {
                        tokenBuilder.append(caracter);
                    }
                    break;
            }
        }
        if (!tokenBuilder.isEmpty()) {
            tokens.add(new Token(TIpoTokenHtml.TEXTO, tokenBuilder.toString(), "Html"));
        }
        return tokens;
    }
    private boolean esTokenCorrecto(String etiqueta) {

        String etiquetaSinSimbolos;
        if (etiqueta.startsWith("</")) {
            etiquetaSinSimbolos = etiqueta.substring(2, etiqueta.length() - 1);
        } else if (etiqueta.endsWith("/>")) {
            etiquetaSinSimbolos = etiqueta.substring(1, etiqueta.length() - 2);
        } else {
            etiquetaSinSimbolos = etiqueta.substring(1, etiqueta.length() - 1);
        }

        List<String> partesEtiqueta = new ArrayList<>();
        StringBuilder palabra = new StringBuilder();
        boolean dentroComillas = false;

        for (int i = 0; i < etiquetaSinSimbolos.length(); i++) {
            char c = etiquetaSinSimbolos.charAt(i);

            if (c == '\"') {
                dentroComillas = !dentroComillas;
                palabra.append(c);
            } else if (c == ' ' && !dentroComillas) {

                if (!palabra.isEmpty()) {
                    partesEtiqueta.add(palabra.toString());
                    palabra.setLength(0); //reiniciar el buiilderString
                }
            } else {
                palabra.append(c);
            }
        }
        if (!palabra.isEmpty()) {
            partesEtiqueta.add(palabra.toString());
        }

        if (partesEtiqueta.isEmpty()) {
            return false; // No se encontró una etiqueta válida
        }

        String tipoEtiqueta = partesEtiqueta.getFirst();
        if (!etiquetaValida(tipoEtiqueta)) {
            return false; //si la primera parte no es una etiqueta valida retorna false
        }
        // evaluar las partes que son palabras reservadas
        for (int i = 1; i < partesEtiqueta.size(); i++) {
            String parte = partesEtiqueta.get(i);
            if (!parte.isEmpty()) {
                if (!PalabrasReservadas.esPatronAtributo(parte)) {
                    return false; // al no ser una palabra reservada retorna false
                }
            }
        }
        return true; //retornar true si la etiqueta es válida
    }




    String etiquetaSinSimbolos;
        TIpoTokenHtml tipoToken;

        if (etiqueta.startsWith("</")) { // evalauar etiqueta de cierre
            etiquetaSinSimbolos = etiqueta.substring(2, etiqueta.length() - 1);
        } else if (etiqueta.endsWith("/>")) { // evaluar etiqueta de una linea
            etiquetaSinSimbolos = etiqueta.substring(1, etiqueta.length() - 2);
        } else if (etiqueta.endsWith("/>") && etiqueta.startsWith("</")) { // evaluar etiqueta de una linea
            tokens.add(new Token(TIpoTokenHtml.ERROR, etiqueta, "Html"));
        } else { // evaluar etiqueta de apertura
            etiquetaSinSimbolos = etiqueta.substring(1, etiqueta.length() - 1);
        }
* */