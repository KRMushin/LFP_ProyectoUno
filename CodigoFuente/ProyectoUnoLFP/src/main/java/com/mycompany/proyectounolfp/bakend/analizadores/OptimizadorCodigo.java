package com.mycompany.proyectounolfp.bakend.analizadores;

import com.mycompany.proyectounolfp.backend.tokens.Token;
import com.mycompany.proyectounolfp.backend.util.TipoEstadoToken;

import java.util.ArrayList;
import java.util.IllegalFormatCodePointException;
import java.util.List;
import java.util.Optional;

public class OptimizadorCodigo {

    enum ESTADO{
        Q0,
        Q1,
        Q2,
        EVALUAR_SLASH,
        Q3,
        CAPTURAR_LINEA, CONTRUIR_COMENTARIO
    }

    public Optional<List<Token>> obtenerOptimizacionesSeccion(String codigo, int numeroFila) {
        List<Token> tokensOptimizacion = new ArrayList<>();

        ESTADO estadoActual = ESTADO.Q0;
        StringBuilder lexActual = new StringBuilder();

        int nF = numeroFila;
        int nC = 1; // columna por defecto
        for (int i = 0; i < codigo.length(); i++) {
            char c = codigo.charAt(i);

            switch (estadoActual){
                case Q0:
                    if (c == '/'){
                        lexActual.append(c);
                        estadoActual = ESTADO.EVALUAR_SLASH;
                    }else { // Para capturar líneas de código
                    }
                    break;
                case EVALUAR_SLASH:
                    if (c == '/'){ // comentario
                        lexActual.append(c);
                        estadoActual = ESTADO.CONTRUIR_COMENTARIO; // comentario confirmado
                    }else {
                        lexActual.setLength(0);
                        estadoActual = ESTADO.Q0; // ignorar xq pueda que sea un slash normal
                    }
                    break;
                case CONTRUIR_COMENTARIO:
                    if (c != '\n'){ // mientras no haya un salot de linea se contruye
                        lexActual.append(c);
                    }else {
                        tokensOptimizacion.add(new Token(TipoEstadoToken.OPTIMIZACION, lexActual.toString(), "COMENTARIO", nF, nC));
                        lexActual.setLength(0);
                        estadoActual = ESTADO.Q0;
                    }
                    break;
                case CAPTURAR_LINEA:
                    if (c == '\n') { // fin de línea
                        tokensOptimizacion.add(new Token(TipoEstadoToken.OPTIMIZACION_LINEA, lexActual.toString(), "COMENTARIO", nF, nC));
                        lexActual.setLength(0);
                        estadoActual = ESTADO.Q0; // reiniciar estado
                    } else if (c == '/' && lexActual.isEmpty()) { // posible comentario al inicio
                        lexActual.append(c);
                        estadoActual = ESTADO.EVALUAR_SLASH;
                    } else {
                        lexActual.append(c); // continuar construyendo la línea de código
                    }
                    break;
            }
            if (c == '\n') {
                nF++;
                nC = 1;
            } else {
                nC++;
            }

        }
        return Optional.of(tokensOptimizacion);
    }
}
