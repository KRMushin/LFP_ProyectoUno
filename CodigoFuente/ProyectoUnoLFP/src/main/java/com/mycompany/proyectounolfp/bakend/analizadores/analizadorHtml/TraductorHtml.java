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
        return traducciones.getOrDefault(etiqueta, etiqueta);
    }
}
