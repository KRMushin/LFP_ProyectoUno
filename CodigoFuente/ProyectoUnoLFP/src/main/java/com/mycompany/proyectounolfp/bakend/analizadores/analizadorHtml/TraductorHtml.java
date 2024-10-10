package com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml;

import java.util.HashMap;
import java.util.Map;

public class TraductorHtml {
    private final Map<String, String> traducciones = new HashMap<>();

    public TraductorHtml() {
        traducciones.put("principal", "main");
        traducciones.put("encabezado", "header");
        traducciones.put("navegacion", "nav");
        traducciones.put("apartado", "aside");
        traducciones.put("listaordenada", "ul");
        traducciones.put("listadesordenada", "ol");
        traducciones.put("itemlista", "li");
        traducciones.put("anclaje", "a");
        traducciones.put("contenedor", "div");
        traducciones.put("seccion", "section");
        traducciones.put("titulo1", "h1");
        traducciones.put("titulo2", "h2");
        traducciones.put("titulo3", "h3");
        traducciones.put("titulo4", "h4");
        traducciones.put("titulo5", "h5");
        traducciones.put("titulo6", "h6");
        traducciones.put("parrafo", "p");
        traducciones.put("entrada", "input");
        traducciones.put("formulario", "form");
        traducciones.put("area", "textarea");
        traducciones.put("boton", "button");
        traducciones.put("piepagina", "footer");
    }
    public String traducirEtiqueta(String etiqueta) {
        // Primero, intentamos traducir la etiqueta
        String traduccion = traducciones.get(etiqueta);

        // Si no está en las traducciones, verificamos si es una etiqueta HTML estándar en inglés usando switch
        if (traduccion == null) {
            switch (etiqueta) {
                case "main":
                case "header":
                case "nav":
                case "aside":
                case "ul":
                case "ol":
                case "li":
                case "a":
                case "div":
                case "section":
                case "h1":
                case "h2":
                case "h3":
                case "h4":
                case "h5":
                case "h6":
                case "p":
                case "input":
                case "form":
                case "textarea":
                case "button":
                case "footer":
                    return etiqueta;
                default:
                    return null;
            }
        }

        return traduccion;
    }
}
