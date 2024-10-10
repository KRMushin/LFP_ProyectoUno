package com.mycompany.proyectounolfp.backend.util;

import com.mycompany.proyectounolfp.backend.tokens.Token;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class GeneradorHtml {
    public void generarHtmlConTokens(List<Token> tokensAnalizados, String nombreArchivo) {
        StringBuilder html = new StringBuilder();

        html.append("<!DOCTYPE html>\n");
        html.append("<html lang=\"en\">\n");
        html.append("<head>\n");
        html.append("    <meta charset=\"UTF-8\">\n");
        html.append("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
        html.append("    <title>Document</title>\n");
        html.append("    <style>\n");
        /*          INTRODUCCION DE TOKENS CSS          */
        int lineaAnalisisCss = 0;
        for (int i = 0; i < tokensAnalizados.size(); i++) {
            Token t = tokensAnalizados.get(i);
            if (t.getContexto().equalsIgnoreCase("css")){
                lineaAnalisisCss = t.getFila();
                break;
            }
        }
        for (int i = 0; i < tokensAnalizados.size(); i++) {
            Token token = tokensAnalizados.get(i);
            if (token.getContexto().equalsIgnoreCase("css")){
                if (token.getFila() != lineaAnalisisCss){
                    html.append("\n");
                    lineaAnalisisCss = token.getFila();
                }
                System.out.println("Token: " + token.getLexema());
                    html.append(token.getLexema());
            }

        }
        html.append("\n");
        html.append("    </style>\n");
        html.append("    <script>\n");
        /*          INTRODUCCION DE TOKENS Javascripts          */
        int lineaAnalisisJs = 0;
        for (int i = 0; i < tokensAnalizados.size(); i++) {
            Token tj = tokensAnalizados.get(i);
            if (tj.getContexto().equalsIgnoreCase("javascript")){
                lineaAnalisisJs = tj.getFila(); // inciar la primera fila de la seccion
                break;
            }
        }

        for (int i = 0; i < tokensAnalizados.size(); i++) {
            Token tjs = tokensAnalizados.get(i);
            if (tjs.getContexto().equalsIgnoreCase("javascript")){ // encontrar al primer JS
                if(tjs.getFila() != lineaAnalisisJs){
                   html.append("\n");
                   lineaAnalisisJs = tjs.getFila();
                   break;
                }
                html.append(tjs.getLexema());
            }
        }
        html.append("\n");
        html.append("    </script>\n");
        html.append("</head>\n"); // fin header
        html.append("<body>\n");
        /*          ESTRUCTURA HTML             */
        int lineaInicioHtml = 0;
        for (int i = 0; i < tokensAnalizados.size(); i++) {
            Token tht = tokensAnalizados.get(i);
            if (tht.getContexto().equalsIgnoreCase("html")){
                lineaInicioHtml = tht.getFila();
                break;
            }
        }

        for (int i = 0; i < tokensAnalizados.size(); i++) {
            Token th = tokensAnalizados.get(i);
            if (th.getContexto().equalsIgnoreCase("html")){
                if (th.getFila() != lineaInicioHtml){
                    lineaInicioHtml = th.getFila();
                }
                html.append(th.getLexema());
            }
        }
        html.append("\n");
        html.append("</body>\n");

        System.out.println("HTML completo generado:");
        System.out.println(html.toString());

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write(html.toString());
            System.out.println("Archivo HTML guardado en: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo: " + e.getMessage());
        }

    }


}
