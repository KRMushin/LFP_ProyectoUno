/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorJavascript;

import com.mycompany.proyectounolfp.backend.Secciones.Seccion;
import com.mycompany.proyectounolfp.backend.tokens.Token;
import com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorJavascript.AutomatasJs.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class AnalizadorJavascript {

    private AutIdentificador automataIdentificador;
    private AutPalabrasReservadas palabrasReservadas;
    private AutValoresSimples valoresSimples;
    private AutValoresDobles valoresDobles;

    public AnalizadorJavascript(){
        this.automataIdentificador = new AutIdentificador();
        this.palabrasReservadas = new AutPalabrasReservadas();
        this.valoresSimples = new AutValoresSimples();
        this.valoresDobles = new AutValoresDobles();
    }

    enum ESTADO{
        Q0,
        IDENTIFICADOR_RESERVADA,
        ERROR,
        SIGNO_SIMBOLO,
        COMENTARIO,
        EVALUAR_SLASH
    }

    public List<Token> analizarSeccion(Seccion seccion) {
        List<Token> tokensJs = new ArrayList<>();
        String contenido = seccion.getContenido();
        StringBuilder lexemaActual = new StringBuilder();

        ESTADO estadoActual = ESTADO.Q0;

        for (int i = 0; i < contenido.length(); i++) {
            char caracter = contenido.charAt(i);

            switch (estadoActual) {
                case Q0:
                    if (Character.isLetter(caracter)) {
                        lexemaActual.append(caracter);
                        estadoActual = ESTADO.IDENTIFICADOR_RESERVADA; // pueda que sea resrvada o identificador
                    } else if (esSignoSimbolo(caracter)) {
                        lexemaActual.append(caracter);
                        estadoActual = ESTADO.SIGNO_SIMBOLO; // pueda que sea resrvada o identificador
                    } else if (caracter == '/') {
                        lexemaActual.append(caracter);
                        estadoActual = ESTADO.EVALUAR_SLASH;
                    } else {

                    }
                    break;
                case IDENTIFICADOR_RESERVADA:
                    if (Character.isLetterOrDigit(caracter) || caracter == '_') {
                        lexemaActual.append(caracter);// permanece en el mismo estado
                    } else if (caracter == ' ' || caracter == '\n' || caracter == '(' || caracter == ')' || caracter == '.') {
                        if (palabrasReservadas.esPalabraReservada(lexemaActual.toString())) {
                            tokensJs.add(new Token(TipoTokenJs.RESERVADA, lexemaActual.toString()));

                        } else if (automataIdentificador.esIdentificadorValido(lexemaActual.toString())) {
                            tokensJs.add(new Token(TipoTokenJs.IDENTIFICADOR, lexemaActual.toString()));

                        } else {
                            tokensJs.add(new Token(TipoTokenJs.ERROR, lexemaActual.toString()));
                        }
                        if (valoresSimples.clasificarValorSimple(String.valueOf(caracter)).isPresent()) { // retorna el alor por ejemplo  . o ( o )
                            tokensJs.add(valoresSimples.clasificarValorSimple(String.valueOf(caracter)).get()); // esto xq existen palabras como (section){}
                        }
                        estadoActual = ESTADO.Q0;
                        lexemaActual.setLength(0); // limpiar el builder
                    } else {
                        tokensJs.add(new Token(TipoTokenJs.ERROR, lexemaActual.toString()));
                        estadoActual = ESTADO.Q0;
                        lexemaActual.setLength(0);
                    }
                    break;
                case SIGNO_SIMBOLO:
                    if (esSignoSimbolo(caracter)) {
                        lexemaActual.append(caracter);
                    } else if (caracter == ' ' || caracter == '\n' || caracter == '/') {

                        String lex = lexemaActual.toString();
                        if (valoresSimples.clasificarValorSimple(lex).isPresent()) {
                            tokensJs.add(valoresSimples.clasificarValorSimple(lex).get());
                            estadoActual = ESTADO.Q0;
                            if (caracter == '/') {
                                lexemaActual.setLength(0);
                                lexemaActual.append(caracter);
                                estadoActual = ESTADO.EVALUAR_SLASH;
                                break;
                            }
                            lexemaActual.setLength(0);
                        } else if (valoresDobles.clasificarValorDoble(lex).isPresent()) {
                            tokensJs.add(valoresDobles.clasificarValorDoble(lex).get());
                            estadoActual = ESTADO.Q0;
                            lexemaActual.setLength(0);
                        } else if (lex.equals("//")) {
                            lexemaActual.append(caracter);
                            estadoActual = ESTADO.COMENTARIO;
                            break;
                        } else {
                            tokensJs.add(new Token(TipoTokenJs.ERROR, lex));
                            estadoActual = ESTADO.Q0;
                            lexemaActual.setLength(0);
                        }
                    } else {
                        tokensJs.add(new Token(TipoTokenJs.ERROR, lexemaActual.toString()));
                        estadoActual = ESTADO.Q0;
                        lexemaActual.setLength(0);
                    }
                    break;
                case EVALUAR_SLASH:
                    if (caracter == '/'){
                        lexemaActual.append(caracter);
                        estadoActual = ESTADO.COMENTARIO;
                    } else {
                        if (valoresSimples.clasificarValorSimple(lexemaActual.toString()).isPresent()){
                            tokensJs.add(valoresSimples.clasificarValorSimple(lexemaActual.toString()).get());
                        }else {
                            tokensJs.add(new Token(TipoTokenJs.ERROR, lexemaActual.toString()));
                        }
                        estadoActual = ESTADO.Q0;
                        lexemaActual.setLength(0);
                    }
                    break;
                case COMENTARIO:
                    if (caracter == '\n'){
                        tokensJs.add(new Token(TipoTokenJs.COMENTARIO, lexemaActual.toString()));
                        estadoActual = ESTADO.Q0;
                        lexemaActual.setLength(0);
                    } else {
                        lexemaActual.append(caracter);
                    }
                    break;
                default:
                    estadoActual = ESTADO.ERROR;
            }

        }
        return tokensJs;
    }

    private boolean esSignoSimbolo(char caracter) {
        return caracter == '(' || caracter == ')' || caracter == '{' || caracter == '}' || caracter == '[' || caracter == ']'
                || caracter == '+' || caracter == '-'  || caracter == '*'
                || caracter == '=' || caracter == ';' || caracter == ',' || caracter == '.' || caracter == ':'
                || caracter == '!' || caracter == '&' || caracter == '|'
                || caracter == '<' || caracter == '>';

    }



}
