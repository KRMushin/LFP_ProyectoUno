/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounolfp.backend.tokens;

import com.mycompany.proyectounolfp.backend.util.TipoEstadoToken;

/**
 *
 * @author kevin-mushin
 */
public class EstadoToken {
    
    private final TipoEstadoToken tipoEstado;

    public EstadoToken(TipoEstadoToken tipoEstado) {
        this.tipoEstado = tipoEstado;
    }

    public TipoEstadoToken getTipoEstado() {
        return tipoEstado;
    }
    
    
    
}
