package com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml.Automatas;

import com.mycompany.proyectounolfp.backend.tokens.Token;
import com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml.TIpoTokenHtml;
import com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml.TraductorHtml;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AutEtiqueta {

    private AutPalabraReservada palabraReservada;
    private TraductorHtml traductorHtml;

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
        CIERRE_ETIQUETA,
        ERROR
    }

    public Optional<List<Token>> obtenerTokens(String etiqueta){
        List<Token> tokens = new ArrayList<>();
        ESTADO estadoActual = ESTADO.Q0;
        StringBuilder lexemaActual = new StringBuilder();
        boolean esEtiquetaLinea = false;

        for (int i = 0; i < etiqueta.length(); i++) {
            char caracter = etiqueta.charAt(i);

            switch (estadoActual) {
                case Q0:
                    if (caracter == '<') {
                        lexemaActual.append(caracter);
                        estadoActual = ESTADO.Q1;
                    }
                    break;

                case Q1:
                    if (caracter == '/') {
                        lexemaActual.append(caracter);
                        estadoActual = ESTADO.CIERRE_ETIQUETA;
                    } else if (Character.isLetterOrDigit(caracter)) {
                        lexemaActual.append(caracter);
                    } else if (caracter == ' ') {
                        lexemaActual.append(caracter);
                        estadoActual = ESTADO.Q2;
                    } else if (caracter == '>') {
                        lexemaActual.append(caracter);
                        tokens.add(new Token(TIpoTokenHtml.ETIQUETA_APERTURA, lexemaActual.toString(), "Html"));
                        lexemaActual.setLength(0);
                        estadoActual = ESTADO.Q0;
                    }
                    break;

                case CIERRE_ETIQUETA:
                    if (Character.isLetterOrDigit(caracter)) {
                        lexemaActual.append(caracter);
                    } else if (caracter == '>') {
                        lexemaActual.append(caracter);
                        tokens.add(new Token(TIpoTokenHtml.ETIQUETA_CIERRE, lexemaActual.toString(), "Html"));
                        lexemaActual.setLength(0);
                        estadoActual = ESTADO.Q0;
                    } else {
                        estadoActual = ESTADO.ERROR;
                    }
                    break;

                case Q2:
                    if (Character.isLetterOrDigit(caracter) || caracter == '-' || caracter == '_') {
                        lexemaActual.append(caracter);
                    } else if (caracter == '=') {
                        lexemaActual.append(caracter);
                        estadoActual = ESTADO.Q3;
                    } else if (caracter == '/') {
                        esEtiquetaLinea = true;
                        lexemaActual.append(caracter);
                    } else if (caracter == '>') {
                        lexemaActual.append(caracter);
                        if (esEtiquetaLinea) {
                            tokens.add(new Token(TIpoTokenHtml.ETIQUETA_UNA_LINEA, lexemaActual.toString(), "Html"));
                        } else {
                            tokens.add(new Token(TIpoTokenHtml.ETIQUETA_APERTURA, lexemaActual.toString(), "Html"));
                        }
                        lexemaActual.setLength(0);
                        esEtiquetaLinea = false;
                        estadoActual = ESTADO.Q0;
                    }
                    break;

                case Q3:
                    if (caracter == '"') {
                        lexemaActual.append(caracter);
                        if (lexemaActual.toString().endsWith("\"")) {
                            estadoActual = ESTADO.Q2;
                        }
                    } else {
                        lexemaActual.append(caracter);
                    }
                    break;

                case Q4:
                    if (caracter == '/') {
                        lexemaActual.append(caracter);
                    } else if (caracter == '>') {
                        lexemaActual.append(caracter);
                        tokens.add(new Token(TIpoTokenHtml.ETIQUETA_UNA_LINEA, lexemaActual.toString(), "Html"));
                        lexemaActual.setLength(0);
                        estadoActual = ESTADO.Q0; // regresar al estado inicial
                    }
                    break;
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


}
