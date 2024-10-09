package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.enumsCss;

public enum EtiquetasCss {

    BODY,
    HEADER,
    MAIN,
    NAV,
    ASIDE,
    DIV,
    UL,
    OL,
    LI,
    A,
    H1,
    H2,
    H3,
    H4,
    H5,
    H6,
    P,
    SPAN,
    LABEL,
    TEXTAREA,
    Button,
    SECTION,
    ARTICLE,
    FOOTER;


    public static boolean esEtiquetaCss(String string) {
         try {
            EtiquetasCss.valueOf(string.toUpperCase());
            return true;
         } catch(IllegalArgumentException e){
             return false;
         }
    }
}
