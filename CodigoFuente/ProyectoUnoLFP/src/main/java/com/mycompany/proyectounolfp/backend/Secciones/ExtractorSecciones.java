/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounolfp.backend.Secciones;

import com.mycompany.proyectounolfp.backend.tokens.EstadoToken;
import com.mycompany.proyectounolfp.backend.util.TipoEstadoToken;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class ExtractorSecciones {

    /* METODO QUE DIVIDE EN SECCIONES EL CODIGO FUENTE DEPENDIENDO DEL TOKEN DE ESTADO*/
    
    public List<Seccion> extraerSeccionesFuente(String codigoFuente) {
        
        List<Seccion> secciones = new ArrayList<>();
        Seccion seccionActual = null;
        StringBuilder contenidoSeccion = new StringBuilder();
        EstadoToken tokenActual = new EstadoToken(TipoEstadoToken.INVALID);  

        boolean seccionIniciada = false;

        // eecorrido de cada caracter del código fuente
        for (int i = 0; i < codigoFuente.length(); i++) {
            char caracter = codigoFuente.charAt(i);

            // detección de posible inicio de token estado
            if (caracter == '>' && i + 1 < codigoFuente.length() && codigoFuente.charAt(i + 1) == '>') {
                i += 2;

                // si ya hay una sección activa, guardamos el contenido actual
                if (seccionActual != null) {
                    seccionActual.setContenido(contenidoSeccion.toString());
                    secciones.add(seccionActual);
                    contenidoSeccion.setLength(0);
                }

                if (codigoFuente.startsWith("[html]", i)) {
                    tokenActual = new EstadoToken(TipoEstadoToken.HTML);
                    i += 5; 
                    seccionIniciada = true;
                } else if (codigoFuente.startsWith("[css]", i)) {
                    tokenActual = new EstadoToken(TipoEstadoToken.CSS);
                    i += 4;
                    seccionIniciada = true;
                } else if (codigoFuente.startsWith("[JS]", i)) {
                    tokenActual = new EstadoToken(TipoEstadoToken.JAVASCRIPT);
                    i += 3;
                    seccionIniciada = true;
                } else {
                    seccionIniciada = true;
                }

                seccionActual = new Seccion();
                seccionActual.setTokenEstado(tokenActual);

            } else {

                if (!seccionIniciada) {
                    tokenActual = new EstadoToken(TipoEstadoToken.INVALID);
                    seccionActual = new Seccion();
                    seccionActual.setTokenEstado(tokenActual);
                    seccionIniciada = true;
                }
                contenidoSeccion.append(caracter);  
            }
        } 

        if (seccionActual != null && contenidoSeccion.length() > 0) {
            seccionActual.setContenido(contenidoSeccion.toString());
            secciones.add(seccionActual);
        }

        return secciones;
    }
}
