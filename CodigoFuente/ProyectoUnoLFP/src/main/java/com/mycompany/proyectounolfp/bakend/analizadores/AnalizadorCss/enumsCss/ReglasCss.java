package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.enumsCss;

public enum ReglasCss {
    color,
    background_color,
    background,
    font_size,
    font_weight,
    font_family,
    font_align,
    width,
    height,
    min_width,
    min_height,
    max_width,
    max_height,
    display,
    inline,
    block,
    inline_block,
    flex,
    grid,
    none,
    margin,
    border,
    padding,
    content,
    border_color,
    border_style,
    border_width,
    border_top,
    border_bottom,
    border_left,
    border_right,
    box_sizing,
    border_box,
    content_box,
    position,
    relative,
    absolute,
    sticky,
    fixed,
    top,
    bottom,
    left,
    right,
    z_index,
    justify_content,
    align_items,
    border_radius,
    auto,
    list_style,
    text_align,
    box_shadow;

}
//    public static boolean esReglaValida(String lexema){
//        if (lexema.equals("static") || lexema.equals("float")){
//            return true;
//        }
//        System.out.println("LEXEMA ANALIZAR " + lexema);
//        for (ReglasCss regla : ReglasCss.values()) {
//            if (regla.name().replace("_", "-").equals(lexema)){
//                return true;
//            }
//        }
//        return false;
//    }
