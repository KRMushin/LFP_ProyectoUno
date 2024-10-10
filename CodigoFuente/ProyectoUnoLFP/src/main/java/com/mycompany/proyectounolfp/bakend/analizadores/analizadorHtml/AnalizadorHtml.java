/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml;

import com.mycompany.proyectounolfp.backend.Secciones.Seccion;
import com.mycompany.proyectounolfp.backend.tokens.EstadoToken;
import com.mycompany.proyectounolfp.backend.tokens.Token;
import com.mycompany.proyectounolfp.backend.util.TipoEstadoToken;
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

        tokensHtml.add(new Token(TipoEstadoToken.HTML, ">>[html]", "Html", seccion.getLineaInicio(), 9));

        int filaActual = seccion.getLineaInicio() - 1;
        int columnaActual = 1;

        for (int i = 0; i < contenido.length(); i++) {
            char caracter = contenido.charAt(i);

            switch (estadoActual) {
                case Q0:
                    if (caracter == '<') {
                        if (lexemaActual.length() > 0) {
                            String lexema = lexemaActual.toString().trim();
                            if (!lexema.contains("//") && !lexema.isEmpty()) {
                                tokensHtml.add(new Token(TIpoTokenHtml.TEXTO, lexema, "Html", filaActual, columnaActual));
                            }
                            lexemaActual.setLength(0);  // Limpia el lexema
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
                        Optional<List<Token>> tokensOpt = obtenerTokens(lexemaActual.toString(),filaActual,columnaActual);

                        if (tokensOpt.isPresent()) {
                            tokensHtml.addAll(tokensOpt.get());
                            filaActual = autEtiqueta.getNumeroFila();
                            columnaActual = autEtiqueta.getNumeroColumna();
                        } else {
                            if (!lexemaActual.toString().equals(" ")){
                                tokensHtml.add(new Token(TIpoTokenHtml.ERROR, lexemaActual.toString(), "Html",filaActual,columnaActual));
                            }
                        }
                        lexemaActual.setLength(0);
                        estadoActual = Estado.Q0;
                    }
                    break;

                case Q2: //texto
                    if (caracter == '<') {
                        if (lexemaActual.length() > 0) {
                            String lexema = lexemaActual.toString().trim();
                            if (!lexema.contains("//") || !lexema.equals(" ")) {
                                tokensHtml.add(new Token(TIpoTokenHtml.TEXTO, lexema, "Html", filaActual, columnaActual));
                            }
                            lexemaActual.setLength(0);  // Limpia el lexema
                        }
                        lexemaActual.append(caracter);
                        estadoActual = Estado.Q1;  // cambiar al estado de lectura de etiqueta
                    } else {
                        lexemaActual.append(caracter);
                    }
                    break;
            }
            if (caracter == '\n') {
                filaActual++;
                columnaActual = 1;
            } else {
                columnaActual++;
            }
        }


        return tokensHtml;
    }

    private Optional<List<Token>> obtenerTokens(String etiqueta,int numeroFila, int numeroColumna) {
        return autEtiqueta.obtenerTokens(etiqueta,numeroFila, numeroColumna);
    }
}
