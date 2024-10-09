/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounolfp.bakend.analizadores;

import com.mycompany.proyectounolfp.backend.Secciones.ExtractorSecciones;
import com.mycompany.proyectounolfp.backend.Secciones.Seccion;
import com.mycompany.proyectounolfp.backend.util.TipoEstadoToken;
import com.mycompany.proyectounolfp.backend.tokens.Token;
import com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.AnalizadorCss;
import com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorJavascript.AnalizadorJavascript;
import com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml.AnalizadorHtml;
import com.mycompany.proyectounolfp.frontend.Vistas.PanelReportes;


import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kevin-mushin
 */
public class AnalizadorCodigoFuente {
    
    private final ExtractorSecciones extractorSecciones;
    private final AnalizadorHtml analizadorHtml;
    private final AnalizadorCss analizadorCss;
    private final AnalizadorJavascript analizadorJavascript;
    private PanelReportes panelReportes;
    /* contructor del analizador que se ocupa de generar y redirigir toda la logica del analisis lexico*/
    public AnalizadorCodigoFuente() {
        this.extractorSecciones = new ExtractorSecciones();
        this.analizadorHtml = new AnalizadorHtml();
        this.analizadorCss = new AnalizadorCss();
        this.analizadorJavascript = new AnalizadorJavascript();
        this.panelReportes = new PanelReportes();
    }

    public void analizarCodigoFuente(String codigoFuente){
        /* fases del analizador lexico , el cual se comunica con diferentes componentes*/
        List<Seccion> seccionesEncontradas = extractorSecciones.extraerSeccionesFuente(codigoFuente);
        List<Token> tokensAnalisis = obtenerTokensAnalisis(seccionesEncontradas);
        List<Token> reporteTokens = obtenerTokensValidos(tokensAnalisis);

        if (!tokensAnalisis.isEmpty()) {
            panelReportes.setVisible(true);
            panelReportes.setReporteTokens(reporteTokens);
        }
//        List<Token> reporteTokens = obtenerTokensValidos(tokensAnalisis);
//        List<Token> reporteOptimizacion = optimizarCodigo(tokensAnalisis);
//        List<Token> reporteErrores = obtenerErrores(tokensAnalisis);

        for (Token token : tokensAnalisis) {
            System.out.println(token.getLexema() + "   TipoToken: " + token.getTipoToken());
        }
    }

    private List<Token> obtenerErrores(List<Token> tokensAnalisis) {
        return null;
    }

    private List<Token> optimizarCodigo(List<Token> tokensAnalisis) {
        


        return null;
    }

    private List<Token> obtenerTokensAnalisis(List<Seccion> seccionesEncontradas) {
        List<Token> tokensEncontrados = new ArrayList<>();

        for (Seccion seccion : seccionesEncontradas) {
            List<Token> tokensSeccion = null;

            switch (seccion.getTokenEstado().getTipoEstado()) {
                case TipoEstadoToken.HTML:
                    tokensSeccion = analizadorHtml.analizarSeccion(seccion);
                    break;
                case TipoEstadoToken.CSS:
                    tokensSeccion = analizadorCss.analizarSeccionCss(seccion);
                    break;
                case TipoEstadoToken.JAVASCRIPT:
                    tokensSeccion = analizadorJavascript.analizarSeccion(seccion);
                    break;
                default:
                    break;
            }
            if (tokensSeccion != null) {
                tokensEncontrados.addAll(tokensSeccion);
            }
        }
        return tokensEncontrados;
    }

    private List<Token> obtenerTokensValidos(List<Token> tokensAnalisis) {
        List<Token> tokens = new ArrayList<>();
        
         for (int i = 0; i < tokensAnalisis.size(); i++) {
               Token token = tokensAnalisis.get(i);
               if (token != null) {
               String tokenType = token.getTipoToken().toString();
               if (!tokenType.equalsIgnoreCase("comentario") || !tokenType.equalsIgnoreCase("error")) {
                   tokens.add(token);
               }
             }
        }
        return tokens;
    }
}
