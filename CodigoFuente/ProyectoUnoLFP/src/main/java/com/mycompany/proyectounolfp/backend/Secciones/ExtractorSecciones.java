/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounolfp.backend.Secciones;

import com.mycompany.proyectounolfp.backend.tokens.EstadoToken;
import com.mycompany.proyectounolfp.backend.tokens.Token;
import com.mycompany.proyectounolfp.backend.util.TipoEstadoToken;
import com.mycompany.proyectounolfp.bakend.analizadores.OptimizadorCodigo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author kevin-mushin
 */
public class ExtractorSecciones {

    private OptimizadorCodigo optimizador;

    public ExtractorSecciones() {
        this.optimizador = new OptimizadorCodigo();
    }


    /* METODO QUE DIVIDE EN SECCIONES EL CODIGO FUENTE DEPENDIENDO DEL TOKEN DE ESTADO*/
    
    public List<Seccion> extraerSeccionesFuente(String codigoFuente) {
        
        List<Seccion> secciones = new ArrayList<>();
        Seccion seccionActual = null;
        StringBuilder contenidoSeccion = new StringBuilder();
        EstadoToken tokenActual = new EstadoToken(TipoEstadoToken.INVALID);  

        boolean seccionIniciada = false;
        int lineaActual = 1;
        int lineaInicioSeccion = 1;

        // eecorrido de cada caracter del código fuente
        for (int i = 0; i < codigoFuente.length(); i++) {
            char caracter = codigoFuente.charAt(i);

            if (caracter == '\n') {
                lineaActual++; // incrementar cada vex que haya linea
            }
            // detección de posible inicio de token estado
            if (caracter == '>' && i + 1 < codigoFuente.length() && codigoFuente.charAt(i + 1) == '>') {
                i += 2;

                // si ya hay una sección activa, guardamos el contenido actual
                if (seccionActual != null) {
                    Optional<List<Token>> tokensOptimizacion = optimizador.obtenerOptimizacionesSeccion(contenidoSeccion.toString(),seccionActual.getLineaInicio());
                    if (tokensOptimizacion.isPresent()){
                        seccionActual.setOptimizaciones(tokensOptimizacion.get());
                    }
                    seccionActual.setContenido(contenidoSeccion.toString());
                    secciones.add(seccionActual);
                    contenidoSeccion.setLength(0);
                }
                lineaInicioSeccion = lineaActual; // donde comienza

                if (codigoFuente.startsWith("[html]", i)) {
                    tokenActual = new EstadoToken(TipoEstadoToken.HTML);
                    i += 5; 
                    seccionIniciada = true;
                } else if (codigoFuente.startsWith("[css]", i)) {
                    tokenActual = new EstadoToken(TipoEstadoToken.CSS);
                    i += 4;
                    seccionIniciada = true;
                } else if (codigoFuente.startsWith("[js]", i)) {
                    tokenActual = new EstadoToken(TipoEstadoToken.JAVASCRIPT);
                    i += 3;
                    seccionIniciada = true;
                } else {
                    seccionIniciada = true;
                }

                seccionActual = new Seccion();
                seccionActual.setTokenEstado(tokenActual);
                seccionActual.setLineaInicio(lineaInicioSeccion);

            } else {

                if (!seccionIniciada) {
                    tokenActual = new EstadoToken(TipoEstadoToken.INVALID);
                    seccionActual = new Seccion();
                    seccionActual.setTokenEstado(tokenActual);
                    seccionActual.setLineaInicio(lineaInicioSeccion);
                    seccionIniciada = true;
                }
                contenidoSeccion.append(caracter);  
            }
        } 

        if (seccionActual != null && !contenidoSeccion.isEmpty()) {

            Optional<List<Token>> tokensOptimizacion = optimizador.obtenerOptimizacionesSeccion(contenidoSeccion.toString(),seccionActual.getLineaInicio());
            if (tokensOptimizacion.isPresent()){
                seccionActual.setOptimizaciones(tokensOptimizacion.get());
            }
            seccionActual.setContenido(contenidoSeccion.toString());
            secciones.add(seccionActual);
        }

        return secciones;
    }
}
