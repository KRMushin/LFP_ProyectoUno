/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss;

import com.mycompany.proyectounolfp.backend.Secciones.Seccion;
import com.mycompany.proyectounolfp.backend.tokens.Token;
import com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.AutomatasCss.AutColorRGBA;
import com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.AutomatasCss.AutDeClase_ID;
import com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.AutomatasCss.AutOtrosCss;
import com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.AutomatasCss.AutReglas;
import com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.enumsCss.OtrosCss;
import com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.enumsCss.ReglasCss;
import com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.enumsCss.EtiquetasCss;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class AnalizadorCss {

    private AutDeClase_ID automataClase;
    private AutColorRGBA automataRGBA;
    private AutReglas automataReglas;
    private AutOtrosCss automataOtros;

    public AnalizadorCss() {
        this.automataClase = new AutDeClase_ID();
        this.automataRGBA = new AutColorRGBA();
        this.automataReglas = new AutReglas();
        this.automataOtros = new AutOtrosCss();
    }

    enum ESTADO {
        Q0,             // Estado inicial
        ETIQUETA_REGLA,       // Procesar etiqueta o selector
        COMBINADOR,
        CLASE_ID,
        OTROS,
        CADENA,
        COLOR,
        COLOR_RGBA,// Procesar combinador
        FINAL           // Estado final
    }
    public List<Token> analizarSeccionCss(Seccion seccion) {
        List<Token> tokensCss = new ArrayList<>();
        ESTADO estadoActual = ESTADO.Q0;
        int filaActual = seccion.getLineaInicio();
        int columnaActual = 1;

        char[] contenido = seccion.getContenido().toCharArray();
        boolean ultimoTokenEsSelector = false;  // Bandera para saber si el último token fue un selector
        StringBuilder lexemaActual = new StringBuilder();

        for (int i = 0; i < contenido.length; i++) {
            char caracter = contenido[i];

            switch (estadoActual) {
                case Q0:
                    if (Character.isLetter(caracter)) {
                        lexemaActual.append(caracter);
                        estadoActual = ESTADO.ETIQUETA_REGLA;

                    } else if (esCombinador(caracter) && ultimoTokenEsSelector) {
                            lexemaActual.append(caracter);
                            tokensCss.add(new Token(TipoTokenCss.COMBINADOR, lexemaActual.toString(), "Css",filaActual,columnaActual));
                            ultimoTokenEsSelector = false;
                            lexemaActual.setLength(0);
                        //permancer en el estado Q0
                    }else if (caracter == '*'){
                        lexemaActual.append(caracter);
                        tokensCss.add(new Token(TipoTokenCss.UNIVERSAL, lexemaActual.toString(), "Css",filaActual,columnaActual));
                        lexemaActual.setLength(0);
                        ultimoTokenEsSelector = false;
                        //permaneceer en el estado Q0
                    }else if (caracter == '.' || caracter == '#'){
                        lexemaActual.append(caracter);// evaluando id
                        estadoActual = ESTADO.CLASE_ID;
                    } else if (caracter == '%' || caracter == '(' || caracter == ')' || caracter == ',' || caracter == ';' || caracter == '{' || caracter == '}') {
                        tokensCss.add(new Token(TipoTokenCss.CARACTERES_VALOR, String.valueOf(caracter), "Css",filaActual,columnaActual));
                    } else if (caracter == ':' && contenido[i + 1] == ':'){
                        tokensCss.add(new Token(TipoTokenCss.CARACTERES_VALOR, "::", "Css",filaActual,columnaActual));
                    } else if (caracter == '\'') {
                        lexemaActual.append(caracter);
                        estadoActual = ESTADO.CADENA;
                    } else if (Character.isDigit(caracter)) { //clasificacion de enteros
                        tokensCss.add(new Token(TipoTokenCss.ENTERO, String.valueOf(caracter), "Css",filaActual,columnaActual));
                    } else if (caracter == ' ') {
                        // ignorar y permanecer en este estado
                    } else{
                            tokensCss.add(new Token(TipoTokenCss.ERROR, String.valueOf(caracter), "Css",filaActual,columnaActual));
                    }
                    break;

                case ETIQUETA_REGLA:
                    if (caracter == 'g' && contenido[i + 1] == 'b' && contenido[i + 2] == 'a' && contenido[i + 3] == '('){ //retornar a estado de rgba
                        lexemaActual.append(caracter);
                        estadoActual = ESTADO.COLOR_RGBA;
                        break;
                    }
                    if (Character.isLetterOrDigit(caracter) || caracter == '-' || caracter == '('){
                        lexemaActual.append(caracter);
                    } else if (caracter == ' ' || caracter == '\n' || caracter == ':' || caracter == ';' || caracter == ')') {
                        System.out.println(         "entro a metodo" + caracter);
                        if (caracter == ')' ){
                            lexemaActual.append(caracter);
                        }
                        if (EtiquetasCss.esEtiquetaCss(lexemaActual.toString())){
                            tokensCss.add(new Token(TipoTokenCss.ETIQUETA, lexemaActual.toString(), "Css",filaActual,columnaActual));
                            lexemaActual.setLength(0);  // Limpiar el lexema
                            ultimoTokenEsSelector = true;
                            estadoActual = ESTADO.Q0;
                        } else if (automataReglas.esReglaCssValida(lexemaActual.toString())){
                            tokensCss.add(new Token(TipoTokenCss.REGLAS_CSS, lexemaActual.toString(), "Css",filaActual,columnaActual));

                            if (caracter == ':') {
                                tokensCss.add(new Token(TipoTokenCss.CARACTERES_VALOR, ":", "Css",filaActual,columnaActual));
                            }
                            lexemaActual.setLength(0);  // Limpiar el lexema
                            estadoActual = ESTADO.Q0;
                        } else if (automataOtros.esValorCssValido(lexemaActual.toString())) {
                            tokensCss.add(new Token(TipoTokenCss.CARACTERES_VALOR, lexemaActual.toString(), "Css",filaActual,columnaActual));

                            if (caracter == ';'){
                                tokensCss.add(new Token(TipoTokenCss.CARACTERES_VALOR, ";", "Css",filaActual,columnaActual));
                            }
                            lexemaActual.setLength(0);  // Limpiar el lexema
                            estadoActual = ESTADO.Q0;
                        } else if (esLexemaIdentificador(lexemaActual.toString())) {

                            tokensCss.add(new Token(TipoTokenCss.IDENTIFICADOR, lexemaActual.toString(), "Css",filaActual,columnaActual));

                            if(caracter == ';'){
                                tokensCss.add(new Token(TipoTokenCss.CARACTERES_VALOR,";", "Css",filaActual,columnaActual));
                            }

                            lexemaActual.setLength(0);  // Limpiar el lexema
                            estadoActual = ESTADO.Q0;
                        } else {
                            if (!lexemaActual.toString().isEmpty()){
                                tokensCss.add(new Token(TipoTokenCss.ERROR, lexemaActual.toString(), "Css",filaActual,columnaActual));
                            }
                            lexemaActual.setLength(0);  // Limpiar el lexema
                            estadoActual = ESTADO.Q0;
                        }
                    } else{
                        lexemaActual.setLength(0);
                        estadoActual = ESTADO.Q0;
                    }
                    break;
                case CLASE_ID:
                    System.out.println("LEXEMA ACTUAL: " + lexemaActual.toString());
                    System.out.println("CARACTER: " + caracter);
                    if (esHexadecimal(caracter)) {
                        System.out.println("ENTRO A HEXADECIMAL");
                        lexemaActual.append(caracter);
                        estadoActual = ESTADO.COLOR; // Cambiar al estado de captura del color
                        break;
                    }
                    if(Character.isLetter(caracter) || Character.isDigit(caracter) || caracter == '-'){
                        lexemaActual.append(caracter);
                    } else if (caracter == ' ' || caracter == '\n') { // fin del lexema
                        if (automataClase.esClaseValida(lexemaActual.toString())){
                            if (lexemaActual.toString().startsWith("#")){
                                tokensCss.add(new Token(TipoTokenCss.DE_ID, lexemaActual.toString(), "Css",filaActual,columnaActual));
                            }else{
                                tokensCss.add(new Token(TipoTokenCss.DE_CLASE, lexemaActual.toString(), "Css",filaActual,columnaActual));
                            }
                        }else{
                            tokensCss.add(new Token(TipoTokenCss.ERROR, lexemaActual.toString(), "Css",filaActual,columnaActual));
                        }
                        lexemaActual.setLength(0);//limpiar el lexema actual
                        estadoActual = ESTADO.Q0;
                    }else{
                        tokensCss.add(new Token(TipoTokenCss.ERROR, lexemaActual.toString(), "Css",filaActual,columnaActual));
                        estadoActual = ESTADO.Q0;
                        lexemaActual.setLength(0);// limpiar
                    }
                    break;
                case CADENA:
                        if (Character.isLetterOrDigit(caracter) || esSignoSimbolo(caracter)){
                            lexemaActual.append(caracter);
                            //permanencer en este estado
                        } else if (caracter == '\''){
                            lexemaActual.append(caracter);
                            tokensCss.add(new Token(TipoTokenCss.CADENA, lexemaActual.toString(), "Css",filaActual,columnaActual));
                            lexemaActual.setLength(0);
                            estadoActual = ESTADO.Q0;
                        } else{
                            tokensCss.add(new Token(TipoTokenCss.ERROR, lexemaActual.toString(), "Css",filaActual,columnaActual));
                            lexemaActual.setLength(0);
                            estadoActual = ESTADO.Q0;
                        }
                break;
                case COLOR:
                    if (esHexadecimal(caracter)) {
                        System.out.println("ENTRO A HEXADECIMAL");
                        lexemaActual.append(caracter);
                    } else {
                        if (lexemaActual.length() == 4 || lexemaActual.length() == 7) {
                            tokensCss.add(new Token(TipoTokenCss.COLOR, lexemaActual.toString(), "css", filaActual, columnaActual));
                            if(caracter == ';'){
                                tokensCss.add(new Token(TipoTokenCss.CARACTERES_VALOR,";", "Css",filaActual,columnaActual));
                            }
                        }else {
                            lexemaActual.append(caracter);
                            tokensCss.add(new Token(TipoTokenCss.ERROR, lexemaActual.toString(), "Css",filaActual,columnaActual));
                        }
                        lexemaActual.setLength(0);
                        estadoActual = ESTADO.Q0;
                    }
                    break;
                case COLOR_RGBA:
                    if (caracter != ')'){
                        lexemaActual.append(caracter);
                    }else {
                        lexemaActual.append(caracter);
                        tokensCss.add(new Token(TipoTokenCss.COLOR_RGBA, lexemaActual.toString(), "Css",filaActual,columnaActual));
                        lexemaActual.setLength(0);
                        estadoActual = ESTADO.Q0;
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
        return tokensCss;
    }

    private boolean esLexemaIdentificador(String string) {
        if (string == null || string.isEmpty()) {
            return false;
        }
        if (!Character.isLowerCase(string.charAt(0))) {
            return false;
        }
        for (int i = 1; i < string.length(); i++) {
            char c = string.charAt(i);
            if (!Character.isLowerCase(c) && !Character.isDigit(c) && c != '-') {
                return false;
            }
        }
        return true;
    }

    private boolean esSignoSimbolo(char caracter) {
        return caracter == '!' || caracter == '@' || caracter == '#' || caracter == '$' ||
                caracter == '%' || caracter == '^' || caracter == '&' || caracter == '*' ||
                caracter == '(' || caracter == ')' || caracter == '-' || caracter == '_' ||
                caracter == '=' || caracter == '+' || caracter == '[' || caracter == ']' ||
                caracter == '{' || caracter == '}' || caracter == '|' || caracter == '\\' ||
                caracter == ':' || caracter == ';' || caracter == '"' ||
                caracter == '<' || caracter == '>' || caracter == ',' || caracter == '.' ||
                caracter == '?' || caracter == '/' || caracter == '~';
    }

    private boolean esCombinador(char caracter) {
        return caracter == '+' || caracter == '>' || caracter == '~' || caracter == ' ';
    }

    private boolean esHexadecimal(char caracter) {
        return (caracter >= '0' && caracter <= '9') ||
                (caracter >= 'a' && caracter <= 'f') ||
                (caracter >= 'A' && caracter <= 'F');
    }
}
