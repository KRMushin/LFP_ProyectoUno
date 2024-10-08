package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorJavascript.AutomatasJs;

public class AutPalabrasReservadas {


    enum ESTADO {
        Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15,
        Q16, Q17, Q18, Q19, Q20, Q21, Q22, Q23, Q24, Q25, Q26, Q27, Q28,
        Q29, Q30, Q31, Q32, Q33, Q34, Q35, Q36, Q37, Q38, Q39, Q40, Q41,
        Q42, Q43, Q44, Q45, Q46, Q47, Q48, Q49, Q50, Q51, Q52, Q53, Q54,
        Q55, Q56,
        ACEPTACION, ERROR
    }

    public boolean esPalabraReservada(String string) {
        ESTADO estadoActual = ESTADO.Q0;

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            switch (estadoActual) {
                case Q0:
                    estadoActual = siguienteEstadoInicial(c);
                    if (estadoActual == ESTADO.ERROR) return false;
                    break;

                case Q1: estadoActual = (c == 'u') ? ESTADO.Q2 : ESTADO.ERROR; break;
                case Q2: estadoActual = (c == 'n') ? ESTADO.Q3 : ESTADO.ERROR; break;
                case Q3: estadoActual = (c == 'c') ? ESTADO.Q4 : ESTADO.ERROR; break;
                case Q4: estadoActual = (c == 't') ? ESTADO.Q5 : ESTADO.ERROR; break;
                case Q5: estadoActual = (c == 'i') ? ESTADO.Q6 : ESTADO.ERROR; break;
                case Q6: estadoActual = (c == 'o') ? ESTADO.Q7 : ESTADO.ERROR; break;
                case Q7: estadoActual = (c == 'n') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q8: estadoActual = (c == 'o') ? ESTADO.Q9 : ESTADO.ERROR; break;
                case Q9: estadoActual = (c == 'n') ? ESTADO.Q10 : ESTADO.ERROR; break;
                case Q10: estadoActual = (c == 's') ? ESTADO.Q11 : ESTADO.ERROR; break;
                case Q11: estadoActual = (c == 't') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q12: estadoActual = (c == 'e') ? ESTADO.Q13 : ESTADO.ERROR; break;
                case Q13: estadoActual = (c == 't') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q14: estadoActual = (c == 'o') ? ESTADO.Q15 : ESTADO.ERROR; break;
                case Q15: estadoActual = (c == 'c') ? ESTADO.Q16 : ESTADO.ERROR; break;
                case Q16: estadoActual = (c == 'u') ? ESTADO.Q17 : ESTADO.ERROR; break;
                case Q17: estadoActual = (c == 'm') ? ESTADO.Q18 : ESTADO.ERROR; break;
                case Q18: estadoActual = (c == 'e') ? ESTADO.Q19 : ESTADO.ERROR; break;
                case Q19: estadoActual = (c == 'n') ? ESTADO.Q20 : ESTADO.ERROR; break;
                case Q20: estadoActual = (c == 't') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q21: estadoActual = (c == 'v') ? ESTADO.Q22 : ESTADO.ERROR; break;
                case Q22: estadoActual = (c == 'e') ? ESTADO.Q23 : ESTADO.ERROR; break;
                case Q23: estadoActual = (c == 'n') ? ESTADO.Q24 : ESTADO.ERROR; break;
                case Q24: estadoActual = (c == 't') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q25: estadoActual = (c == 'l') ? ESTADO.Q26 : ESTADO.ERROR; break;
                case Q26: estadoActual = (c == 'e') ? ESTADO.Q27 : ESTADO.ERROR; break;
                case Q27: estadoActual = (c == 'r') ? ESTADO.Q28 : ESTADO.ERROR; break;
                case Q28: estadoActual = (c == 't') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q29: estadoActual = (c == 'o') ? ESTADO.Q30 : ESTADO.ERROR; break;
                case Q30: estadoActual = (c == 'r') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q31: estadoActual = (c == 'h') ? ESTADO.Q32 : ESTADO.ERROR; break;
                case Q32: estadoActual = (c == 'i') ? ESTADO.Q33 : ESTADO.ERROR; break;
                case Q33: estadoActual = (c == 'l') ? ESTADO.Q34 : ESTADO.ERROR; break;
                case Q34: estadoActual = (c == 'e') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                // if xdd
                case Q35: estadoActual = (c == 'f') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q36: estadoActual = (c == 'l') ? ESTADO.Q37 : ESTADO.ERROR; break;
                case Q37: estadoActual = (c == 's') ? ESTADO.Q38 : ESTADO.ERROR; break;
                case Q38: estadoActual = (c == 'e') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q39: estadoActual = (c == 'e') ? ESTADO.Q40 : ESTADO.ERROR; break;
                case Q40: estadoActual = (c == 't') ? ESTADO.Q41 : ESTADO.ERROR; break;
                case Q41: estadoActual = (c == 'u') ? ESTADO.Q42 : ESTADO.ERROR; break;
                case Q42: estadoActual = (c == 'r') ? ESTADO.Q43 : ESTADO.ERROR; break;
                case Q43: estadoActual = (c == 'n') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q44: estadoActual = (c == 'o') ? ESTADO.Q45 : ESTADO.ERROR; break;
                case Q45: estadoActual = (c == 'n') ? ESTADO.Q46 : ESTADO.ERROR; break;
                case Q46: estadoActual = (c == 's') ? ESTADO.Q47 : ESTADO.ERROR; break;
                case Q47: estadoActual = (c == 'o') ? ESTADO.Q48 : ESTADO.ERROR; break;
                case Q48: estadoActual = (c == 'l') ? ESTADO.Q49 : ESTADO.ERROR; break;
                case Q49: estadoActual = (c == 'e') ? ESTADO.Q50 : ESTADO.ERROR; break;
                case Q50: estadoActual = (c == '.') ? ESTADO.Q51 : ESTADO.ERROR; break;
                case Q51: estadoActual = (c == 'l') ? ESTADO.Q52 : ESTADO.ERROR; break;
                case Q52: estadoActual = (c == 'o') ? ESTADO.Q53 : ESTADO.ERROR; break;
                case Q53: estadoActual = (c == 'g') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q54: estadoActual = (c == 'u') ? ESTADO.Q55 : ESTADO.ERROR; break;
                case Q55: estadoActual = (c == 'l') ? ESTADO.Q56 : ESTADO.ERROR; break;
                case Q56: estadoActual = (c == 'l') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;
                default: return false;
            }
            if (estadoActual == ESTADO.ERROR) return false;
        }
        return estadoActual == ESTADO.ACEPTACION;
    }

    // inicial con los valores validos de cada string
    private ESTADO siguienteEstadoInicial(char c) {
        switch (c) {
            case 'f': return ESTADO.Q1;  // funcion , for
            case 'c': return ESTADO.Q8;  // const, console.log
            case 'l': return ESTADO.Q12; // let
            case 'd': return ESTADO.Q14; // document
            case 'e': return ESTADO.Q21; // event, else
            case 'a': return ESTADO.Q25; // alert
            case 'r': return ESTADO.Q39; // return
            case 'w': return ESTADO.Q31; // while
            case 'i': return ESTADO.Q35; // if
            case 'n': return ESTADO.Q54; // null
            default:  return ESTADO.ERROR;
        }
    }
}
