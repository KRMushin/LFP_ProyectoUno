package com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml;

import java.util.HashMap;
import java.util.Map;

public enum TIpoTokenHtml  {
    
    ETIQUETA_APERTURA,
    ETIQUETA_CIERRE,
    ETIQUETA_UNA_LINEA,
    TEXTO,
    ERROR;

    private static final Map<String, String> TRADUCCIONES = new HashMap<>();

    static {

        TRADUCCIONES.put("principal", "main");
        TRADUCCIONES.put("encabezado", "header");
        TRADUCCIONES.put("navegacion", "nav");
        TRADUCCIONES.put("apartado", "aside");
        TRADUCCIONES.put("listaordenada", "ul");
        TRADUCCIONES.put("listadesordenada", "ol");
        TRADUCCIONES.put("itemlista", "li");
        TRADUCCIONES.put("anclaje", "a");
        TRADUCCIONES.put("contenedor", "div");
        TRADUCCIONES.put("seccion", "section");
        TRADUCCIONES.put("titulo1", "h1");
        TRADUCCIONES.put("titulo2", "h2");
        TRADUCCIONES.put("titulo3", "h3");
        TRADUCCIONES.put("titulo4", "h4");
        TRADUCCIONES.put("titulo5", "h5");
        TRADUCCIONES.put("titulo6", "h6");
        TRADUCCIONES.put("parrafo", "p");
        TRADUCCIONES.put("entrada", "input");
        TRADUCCIONES.put("formulario", "form");
        TRADUCCIONES.put("area", "textarea");
        TRADUCCIONES.put("boton", "button");
        TRADUCCIONES.put("piepagina", "footer");
    }
    //traductor de contenido de etiquetas
    public static String traducirEtiqueta(String etiqueta) {
        return TRADUCCIONES.getOrDefault(etiqueta, etiqueta);  // Si no está en español, retorna la etiqueta original
    }
}
