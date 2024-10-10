package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.AutomatasCss;

public class AutReglas {

    public boolean esReglaCssValida(String string) {

        // Comprobar propiedades relacionadas con color
        if (string.equals("color")) {
            return true;
        } else if (string.equals("background-color")) {
            return true;
        } else if (string.equals("background")) {
            return true;
        }
        else if (string.equals("font-size")) {
            return true;
        } else if (string.equals("font-weight")) {
            return true;
        } else if (string.equals("font-family")) {
            return true;
        } else if (string.equals("font-align")) {
            return true;
        }
        else if (string.equals("width")) {
            return true;
        } else if (string.equals("height")) {
            return true;
        } else if (string.equals("min-width")) {
            return true;
        } else if (string.equals("min-height")) {
            return true;
        } else if (string.equals("max-width")) {
            return true;
        } else if (string.equals("max-height")) {
            return true;
        }
        else if (string.equals("display")) {
            return true;
        } else if (string.equals("inline")) {
            return true;
        } else if (string.equals("block")) {
            return true;
        } else if (string.equals("inline-block")) {
            return true;
        } else if (string.equals("flex")) {
            return true;
        } else if (string.equals("grid")) {
            return true;
        } else if (string.equals("none")) {
            return true;
        }
        else if (string.equals("margin")) {
            return true;
        } else if (string.equals("border")) {
            return true;
        } else if (string.equals("padding")) {
            return true;
        } else if (string.equals("border-color")) {
            return true;
        } else if (string.equals("border-style")) {
            return true;
        } else if (string.equals("border-width")) {
            return true;
        } else if (string.equals("border-top")) {
            return true;
        } else if (string.equals("border-bottom")) {
            return true;
        } else if (string.equals("border-left")) {
            return true;
        } else if (string.equals("border-right")) {
            return true;
        }
        else if (string.equals("box-sizing")) {
            return true;
        } else if (string.equals("border-box")) {
            return true;
        } else if (string.equals("content-box")) {
            return true;
        } else if (string.equals("position")) {
            return true;
        } else if (string.equals("relative")) {
            return true;
        } else if (string.equals("absolute")) {
            return true;
        } else if (string.equals("sticky")) {
            return true;
        } else if (string.equals("fixed")) {
            return true;
        } else if (string.equals("top")) {
            return true;
        } else if (string.equals("bottom")) {
            return true;
        } else if (string.equals("left")) {
            return true;
        } else if (string.equals("right")) {
            return true;
        } else if (string.equals("z-index")) {
            return true;
        } else if (string.equals("justify-content")) {
            return true;
        } else if (string.equals("align-items")) {
            return true;
        } else if (string.equals("border-radius")) {
            return true;
        } else if (string.equals("auto")) {
            return true;
        } else if (string.equals("list-style")) {
            return true;
        } else if (string.equals("text-align")) {
            return true;
        } else if (string.equals("box-shadow")) {
            return true;
        }
        else {
            return false;
        }
    }
}
