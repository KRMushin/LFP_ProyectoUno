package com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml.Automatas;

import com.mycompany.proyectounolfp.backend.tokens.Token;
import com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml.TIpoTokenHtml;
import com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml.TraductorHtml;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutEtiqueta {

    private AutPalabraReservada palabraReservada;
    private TraductorHtml traductorHtml;
    private int numeroFila;
    private int numeroColumna;

    public AutEtiqueta() {
        this.palabraReservada = new AutPalabraReservada();
        this.traductorHtml = new TraductorHtml();
    }
    enum ESTADO{
        Q0,
        Q1,
        Q2,
        Q3,
        Q4,
        Q5,
        CIERRE_ETIQUETA,
        Q6, Q7, Q8, ERROR
    }

    public Optional<List<Token>> obtenerTokens(String etiqueta, int numeroFila, int numeroColumna){
        List<Token> tokens = new ArrayList<>();
        ESTADO estadoActual = ESTADO.Q0;
        StringBuilder lexemaActual = new StringBuilder();
        StringBuilder etiquetaCompleta = new StringBuilder();
        boolean esEtiquetaLinea = false;

        this.numeroFila = numeroFila;
        this.numeroColumna = numeroColumna;

        for (int i = 0; i < etiqueta.length(); i++) {
            char caracter = etiqueta.charAt(i);

            switch (estadoActual) {
                case Q0:
                    if (caracter == '<') {
                        etiquetaCompleta.append(caracter);
                        estadoActual = ESTADO.Q1;
                    }
                    break;

                case Q1:
                    if (caracter == '/' && etiqueta.charAt(i-1) == '<') {
                        estadoActual = ESTADO.CIERRE_ETIQUETA;
                    } else if (Character.isLetterOrDigit(caracter)) {
                        lexemaActual.append(caracter);
                    } else if (caracter == ' ') {
                        String traduccion = traductorHtml.traducirEtiqueta(lexemaActual.toString());
                        if (traduccion != null){
                            etiquetaCompleta.append(traduccion);
                            etiquetaCompleta.append(caracter);
                            lexemaActual.setLength(0);
                            estadoActual = ESTADO.Q5;
                        }else {
                            estadoActual = ESTADO.ERROR;
                        }
                    } else if (caracter == '>') {
                        String traduccion = traductorHtml.traducirEtiqueta(lexemaActual.toString());
                        if (traduccion != null){
                            lexemaActual.setLength(0);
                            lexemaActual.append(traduccion);
                            lexemaActual.append(caracter);
                            tokens.add(new Token(TIpoTokenHtml.ETIQUETA_APERTURA, "<" + lexemaActual.toString(), "Html",numeroFila, numeroColumna));
                            estadoActual = ESTADO.Q0;
                        } else {
                            estadoActual = ESTADO.ERROR;
                        }
                    }else {
                        estadoActual = ESTADO.ERROR;
                    }
                    break;

                case CIERRE_ETIQUETA:
                    if (Character.isLetterOrDigit(caracter)) {
                        lexemaActual.append(caracter);
                    } else if (caracter == '>') {
                        String traduccion = traductorHtml.traducirEtiqueta(lexemaActual.toString());
                        if (traduccion != null){
                            lexemaActual.setLength(0);
                            lexemaActual.append(traduccion);
                            lexemaActual.append(caracter);
                            tokens.add(new Token(TIpoTokenHtml.ETIQUETA_CIERRE, "</"+ lexemaActual.toString(), "Html",numeroFila, numeroColumna));
                            estadoActual = ESTADO.Q0;
                        } else {
                            estadoActual = ESTADO.ERROR;
                        }
                    } else {
                        estadoActual = ESTADO.ERROR;
                    }
                    break;
                case Q5:
                    if (Character.isLetterOrDigit(caracter)){
                        etiquetaCompleta.append(caracter);
                        lexemaActual.append(caracter);
                    } else if (caracter == '=') {
                        if (palabraReservada.esPalabraReservada(lexemaActual.toString())){
                            lexemaActual.append(caracter);
                            etiquetaCompleta.append(caracter);
                            estadoActual = ESTADO.Q6;
                        }else {
                            estadoActual = ESTADO.ERROR;
                        }
                    }else {
                            estadoActual = ESTADO.ERROR;

                    }
                    break;
                case Q6:
                    if (caracter == '\"'){
                        etiquetaCompleta.append(caracter);
                        lexemaActual.append(caracter);
                        estadoActual = ESTADO.Q7;
                    }else {
                        estadoActual = ESTADO.ERROR;
                    }

                    break;
                case Q7:
                    if (caracter == '>' || caracter == '/'){
                        estadoActual = ESTADO.ERROR;
                    }
                    if (caracter != '\"') {
                        etiquetaCompleta.append(caracter);
                        lexemaActual.append(caracter);
                    } else {
                        etiquetaCompleta.append(caracter);
                        lexemaActual.append(caracter);
                        lexemaActual.setLength(0);
                        estadoActual = ESTADO.Q8;
                    }
                    break;
                case Q8:
                    if (caracter == '>') {
                        lexemaActual.append(caracter);
                        etiquetaCompleta.append(caracter);
                        tokens.add(new Token(TIpoTokenHtml.ETIQUETA_APERTURA, etiquetaCompleta.toString(), "Html", numeroFila, numeroColumna));
                        lexemaActual.setLength(0);
                        estadoActual = ESTADO.Q0;
                    } else if (caracter == '/' ) {
                        etiquetaCompleta.append(caracter);
                        tokens.add(new Token(TIpoTokenHtml.ETIQUETA_UNA_LINEA, etiquetaCompleta.toString() + ">", "Html", numeroFila, numeroColumna));
                        estadoActual = ESTADO.Q0;
                    }else {
                        estadoActual = ESTADO.ERROR;
                    }
                    break;

                case ERROR:
                    return Optional.empty();
            }
            if (caracter == '\n') {
                numeroFila++;
                numeroColumna = 1;
            } else {
                numeroColumna++;
            }
        }

        if (estadoActual == ESTADO.ERROR) {
            return Optional.empty();
        }

        return Optional.of(tokens);
    }


    private boolean esEtiquetaValida(String etiqueta) {
        String etiquetaTraducida = traductorHtml.traducirEtiqueta(etiqueta); // traducir siempre el tag
        return switch (etiquetaTraducida) {
            case "main", "header","div", "nav", "aside", "ul", "ol", "li", "a", "section", "article",
                 "form", "label", "textarea", "button", "footer", "h1", "h2", "h3", "h4", "h5", "h6", "p", "span",
                 "input", "container" , "body"-> true;
            default -> false;
        };
    }

    public int getNumeroFila() {
        return numeroFila;
    }

    public void setNumeroFila(int numeroFila) {
        this.numeroFila = numeroFila;
    }

    public int getNumeroColumna() {
        return numeroColumna;
    }

    public void setNumeroColumna(int numeroColumna) {
        this.numeroColumna = numeroColumna;
    }
}
