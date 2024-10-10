/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounolfp.backend.Secciones;

import com.mycompany.proyectounolfp.backend.tokens.EstadoToken;
import com.mycompany.proyectounolfp.backend.tokens.Token;

import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class Seccion {
    
    private EstadoToken tokenEstado;
    private String contenido;
    private int lineaInicio;
    private List<Token> optimizaciones;

    public Seccion() {
    }

    public EstadoToken getTokenEstado() {
        return tokenEstado;
    }

    public void setTokenEstado(EstadoToken tokenEstado) {
        this.tokenEstado = tokenEstado;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getLineaInicio() {
        return lineaInicio;
    }

    public void setLineaInicio(int lineaInicio) {
        this.lineaInicio = lineaInicio;
    }

    public List<Token> getOptimizaciones() {
        return optimizaciones;
    }

    public void setOptimizaciones(List<Token> optimizaciones) {
        this.optimizaciones = optimizaciones;
    }
}
