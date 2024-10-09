package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.enumsCss;

public enum OtrosCss {
    px,
    rem,
    em,
    vw,
    vh,
    hover,
    active,
    odd,
    even;

}
//    public static boolean esOtroCss(String lexema){
//        if (lexema.equals("not()") || lexema.equals("nth-child()") || esLetra(lexema)){
//            return true;
//        } else {
//            for (OtrosCss valor : OtrosCss.values()) {
//                if (valor.name().equals(lexema)) {
//                    return true;
//                }
//            }
//            return false;
//        }
//    }
//
//    private static boolean esLetra(String lexema) {
//        for (int i = 0; i < lexema.length(); i++){
//            if (!Character.isLetter(lexema.charAt(i))){
//                return false;
//            }
//        }
//        return true;
//    }
