package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.AutomatasCss;

public class AutReglas {

    public boolean esReglaCssValida(String string) {

        // Comprobar propiedades relacionadas con color
        if (string.equals("color")) {
            return true;
        } else if (string.equals("background_color")) {
            return true;
        } else if (string.equals("background")) {
            return true;
        }
        else if (string.equals("font_size")) {
            return true;
        } else if (string.equals("font_weight")) {
            return true;
        } else if (string.equals("font_family")) {
            return true;
        } else if (string.equals("font_align")) {
            return true;
        }
        else if (string.equals("width")) {
            return true;
        } else if (string.equals("height")) {
            return true;
        } else if (string.equals("min_width")) {
            return true;
        } else if (string.equals("min_height")) {
            return true;
        } else if (string.equals("max_width")) {
            return true;
        } else if (string.equals("max_height")) {
            return true;
        }
        else if (string.equals("display")) {
            return true;
        } else if (string.equals("inline")) {
            return true;
        } else if (string.equals("block")) {
            return true;
        } else if (string.equals("inline_block")) {
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
        } else if (string.equals("border_color")) {
            return true;
        } else if (string.equals("border_style")) {
            return true;
        } else if (string.equals("border_width")) {
            return true;
        } else if (string.equals("border_top")) {
            return true;
        } else if (string.equals("border_bottom")) {
            return true;
        } else if (string.equals("border_left")) {
            return true;
        } else if (string.equals("border_right")) {
            return true;
        }
        else if (string.equals("box_sizing")) {
            return true;
        } else if (string.equals("border_box")) {
            return true;
        } else if (string.equals("content_box")) {
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
        } else if (string.equals("z_index")) {
            return true;
        } else if (string.equals("justify_content")) {
            return true;
        } else if (string.equals("align_items")) {
            return true;
        } else if (string.equals("border_radius")) {
            return true;
        } else if (string.equals("auto")) {
            return true;
        } else if (string.equals("list_style")) {
            return true;
        } else if (string.equals("text_align")) {
            return true;
        } else if (string.equals("box_shadow")) {
            return true;
        }
        else {
            return false;
        }
    }
}
