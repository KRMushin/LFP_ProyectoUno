/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml;

import com.mycompany.proyectounolfp.backend.Secciones.Seccion;
import com.mycompany.proyectounolfp.backend.tokens.Token;

import java.util.*;

/**
 *
 * @author kevin-mushin
 */
public class AnalizadorHtml {
    private enum Estado {
        Q0,//INICIO
        Q1, // LECTURA ETIQUETA
        Q2, // LECTURA ATRIBUTOS
        Q3 // LECTURA TEXTO
    }
    private Estado estadoActual;

    public AnalizadorHtml() {
        this.estadoActual = Estado.Q0;
    }
    public List<Token> analizarSeccion(Seccion seccion) {
        List<Token> tokens = new ArrayList<>();
        String contenido = seccion.getContenido();
        StringBuilder tokenBuilder = new StringBuilder();

        for (int i = 0; i < contenido.length(); i++) {
            char caracter = contenido.charAt(i);

            switch (estadoActual){
                case Q0:
                    if (caracter == ' '){
                        //se mantiene en este estado
                        estadoActual = Estado.Q0;
                    }else if (caracter == '<'){
                        if (tokenBuilder.length() > 0) {
                            tokens.add(new Token(TIpoTokenHtml.TEXTO, tokenBuilder.toString()));
                            tokenBuilder.setLength(0);
                        }
                        tokenBuilder.append(caracter);
                        estadoActual = Estado.Q1;
                    }else if(Character.isLetter(caracter)){
                        tokenBuilder.append(caracter);
                        estadoActual = Estado.Q3;//posible texto en la seccion
                    }
                    break;
                case Q1:
                    if (Character.isLetterOrDigit(caracter) || caracter == '=' || caracter == '\"' || caracter == ' ' || caracter == '/'){
                        tokenBuilder.append(caracter);
                        estadoActual = Estado.Q1;

                    }else if (caracter == '>'){
                        tokenBuilder.append(caracter);
                        if (esTokenCorrecto(tokenBuilder.toString())){
                                if(tokenBuilder.charAt(1) == '/'){
                                    tokens.add(new Token(TIpoTokenHtml.ETIQUETA_CIERRE, tokenBuilder.toString()));
                                }else if (tokenBuilder.toString().trim().endsWith("/>") && !tokenBuilder.toString().startsWith("</")) {
                                    tokens.add(new Token(TIpoTokenHtml.ETIQUETA_UNA_LINEA, tokenBuilder.toString()));
                                }
                                else{
                                    tokens.add(new Token(TIpoTokenHtml.ETIQUETA_APERTURA, tokenBuilder.toString()));
                                }
                                tokenBuilder.setLength(0);
                        }else {
                            tokens.add(new Token(TIpoTokenHtml.ERROR, tokenBuilder.toString()));
                            tokenBuilder.setLength(0);
                        }
                        estadoActual = Estado.Q0;
                    }
                    break;
            case Q3:
                    if (caracter == '<') {
                        if (tokenBuilder.length() > 0) {
                            tokens.add(new Token(TIpoTokenHtml.TEXTO, tokenBuilder.toString()));
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
        if (tokenBuilder.length() > 0) {
            tokens.add(new Token(TIpoTokenHtml.TEXTO, tokenBuilder.toString()));
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

        System.out.println("etiqueta   " + etiqueta);
        System.out.println("dep" + etiquetaSinSimbolos);
        List<String> partesEtiqueta = new ArrayList<>();
        StringBuilder palabra = new StringBuilder();
        boolean dentroComillas = false;

        for (int i = 0; i < etiquetaSinSimbolos.length(); i++) {
            char c = etiquetaSinSimbolos.charAt(i);

            if (c == '\"') {
                dentroComillas = !dentroComillas;
                palabra.append(c);
            } else if (c == ' ' && !dentroComillas) {

                if (palabra.length() > 0) {
                    partesEtiqueta.add(palabra.toString());
                    System.out.println(palabra.toString());
                    palabra.setLength(0); //reiniciar el buiilderString
                }
            } else {
                palabra.append(c);
            }
        }
        if (palabra.length() > 0) {
            partesEtiqueta.add(palabra.toString());
        }

        if (partesEtiqueta.isEmpty()) {
            return false; // No se encontró una etiqueta válida
        }

        String tipoEtiqueta = partesEtiqueta.get(0);
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
    private boolean etiquetaValida(String tag) {
        String etiquetaTraducida = TIpoTokenHtml.traducirEtiqueta(tag); // traducir siempre el tag
        return switch (etiquetaTraducida) {
            case "main", "header","div", "nav", "aside", "ul", "ol", "li", "a", "section", "article",
                 "form", "label", "textarea", "button", "footer", "h1", "h2", "h3", "h4", "h5", "h6", "p", "span",
                 "input", "container" , "body"-> true;
            default -> false;
        };
    }
}
/*
*   ublic List<Token> analizarSeccion(Seccion seccion) {
        List<Token> tokens = new ArrayList<>();
        String contenido = seccion.getContenido();
        StringBuilder tokenBuilder = new StringBuilder();

        for (int i = 0; i < contenido.length(); i++) {
            char caracter = contenido.charAt(i);

            switch (estadoActual){
                case Q0:
                    if (caracter == ' '){
                        //se mantiene en este estado
                        estadoActual = Estado.Q0;
                    }else if (caracter == '<'){
                        tokenBuilder.append(caracter);
                        estadoActual = Estado.Q1;
                    }else if(Character.isLetter(caracter)){
                        tokenBuilder.append(caracter);
                        estadoActual = Estado.Q0;//posible texto en la seccion
                    }
                    break;
                case Q1:
                    if (Character.isLetterOrDigit(caracter) || caracter == '=' || caracter == '\"' || caracter == ' ' || caracter == '/'){
                        tokenBuilder.append(caracter);
                        estadoActual = Estado.Q1;

                    }else if (caracter == '>'){
                        tokenBuilder.append(caracter);
                        if (esTokenCorrecto(tokenBuilder.toString())){
                                if(tokenBuilder.charAt(1) == '/'){
                                    tokens.add(new Token(TIpoTokenHtml.ETIQUETA_CIERRE, tokenBuilder.toString()));
                                }else{
                                    tokens.add(new Token(TIpoTokenHtml.ETIQUETA_APERTURA, tokenBuilder.toString()));
                                }
                                tokenBuilder.setLength(0);
                        }else {
                            tokens.add(new Token(TIpoTokenHtml.ERROR, tokenBuilder.toString()));
                            tokenBuilder.setLength(0);
                        }
                        estadoActual = Estado.Q0;
                    }
            }
        }


        return tokens;
    }
*
* */