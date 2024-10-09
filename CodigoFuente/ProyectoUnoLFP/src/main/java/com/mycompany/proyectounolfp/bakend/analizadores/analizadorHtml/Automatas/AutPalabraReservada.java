package com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml.Automatas;

public class AutPalabraReservada {

    enum ESTADO {
        Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15,
        Q16, Q17, Q18, Q19, Q20, Q21, Q22, Q23, Q24, Q25, Q26, Q27, Q28,
        Q29, Q30, Q31, Q32, Q33, Q34, Q35, Q36, Q37, Q38, Q39, Q40, Q41,
        Q42, Q43, Q44, Q45,
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

                case Q1: estadoActual = (c == 'l') ? ESTADO.Q2 : ESTADO.ERROR; break;
                case Q2: estadoActual = (c == 'a') ? ESTADO.Q3 : ESTADO.ERROR; break;
                case Q3: estadoActual = (c == 's') ? ESTADO.Q4 : ESTADO.ERROR; break;
                case Q4: estadoActual = (c == 's') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q5: estadoActual = (c == 'd') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q6: estadoActual = (c == 'y') ? ESTADO.Q7 : ESTADO.ERROR; break;
                case Q7: estadoActual = (c == 'p') ? ESTADO.Q8 : ESTADO.ERROR; break;
                case Q8: estadoActual = (c == 'e') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q9: estadoActual = (c == 'a') ? ESTADO.Q10 : ESTADO.ERROR; break;
                case Q10: estadoActual = (c == 'm') ? ESTADO.Q11 : ESTADO.ERROR; break;
                case Q11: estadoActual = (c == 'e') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q12: estadoActual = (c == 'r') ? ESTADO.Q13 : ESTADO.ERROR; break;
                case Q13: estadoActual = (c == 'e') ? ESTADO.Q14 : ESTADO.ERROR; break;
                case Q14: estadoActual = (c == 'f') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q15: estadoActual = (c == 'n') ? ESTADO.Q16 : ESTADO.ERROR; break;
                case Q16: estadoActual = (c == 'c') ? ESTADO.Q17 : ESTADO.ERROR; break;
                case Q17: estadoActual = (c == 'l') ? ESTADO.Q18 : ESTADO.ERROR; break;
                case Q18: estadoActual = (c == 'i') ? ESTADO.Q19 : ESTADO.ERROR; break;
                case Q19: estadoActual = (c == 'c') ? ESTADO.Q20 : ESTADO.ERROR; break;
                case Q20: estadoActual = (c == 'k') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q21: estadoActual = (c == 't') ? ESTADO.Q22 : ESTADO.ERROR; break;
                case Q22: estadoActual = (c == 'y') ? ESTADO.Q23 : ESTADO.ERROR; break;
                case Q23: estadoActual = (c == 'l') ? ESTADO.Q24 : ESTADO.ERROR; break;
                case Q24: estadoActual = (c == 'e') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q25: estadoActual = (c == 'l') ? ESTADO.Q26 : ESTADO.ERROR; break;
                case Q26: estadoActual = (c == 'a') ? ESTADO.Q27 : ESTADO.ERROR; break;
                case Q27: estadoActual = (c == 'c') ? ESTADO.Q28 : ESTADO.ERROR; break;
                case Q28: estadoActual = (c == 'e') ? ESTADO.Q29 : ESTADO.ERROR; break;
                case Q29: estadoActual = (c == 'h') ? ESTADO.Q30 : ESTADO.ERROR; break;
                case Q30: estadoActual = (c == 'o') ? ESTADO.Q31 : ESTADO.ERROR; break;
                case Q31: estadoActual = (c == 'l') ? ESTADO.Q32 : ESTADO.ERROR; break;
                case Q32: estadoActual = (c == 'd') ? ESTADO.Q33 : ESTADO.ERROR; break;
                case Q33: estadoActual = (c == 'e') ? ESTADO.Q34 : ESTADO.ERROR; break;
                case Q34: estadoActual = (c == 'r') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q35: estadoActual = (c == 'e') ? ESTADO.Q36 : ESTADO.ERROR; break;
                case Q36: estadoActual = (c == 'q') ? ESTADO.Q37 : ESTADO.ERROR; break;
                case Q37: estadoActual = (c == 'u') ? ESTADO.Q38 : ESTADO.ERROR; break;
                case Q38: estadoActual = (c == 'i') ? ESTADO.Q39 : ESTADO.ERROR; break;
                case Q39: estadoActual = (c == 'r') ? ESTADO.Q40 : ESTADO.ERROR; break;
                case Q40: estadoActual = (c == 'e') ? ESTADO.Q41 : ESTADO.ERROR; break;
                case Q41: estadoActual = (c == 'd') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q42: estadoActual = (c == 'q') ? ESTADO.Q43 : ESTADO.ERROR; break;
                case Q43: estadoActual = (c == 'u') ? ESTADO.Q44 : ESTADO.ERROR; break;
                case Q44: estadoActual = (c == 'a') ? ESTADO.Q45 : ESTADO.ERROR; break;
                case Q45: estadoActual = (c == 'l') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;
                default: return false;
            }
            if (estadoActual == ESTADO.ERROR) return false;
        }
        return estadoActual == ESTADO.ACEPTACION;
    }

    // inicial con los valores validos de cada string
    private ESTADO siguienteEstadoInicial(char c) {
        switch (c) {
            case 'c': return ESTADO.Q1;  //class
            case 'i': return ESTADO.Q5;  // id
            case 't': return ESTADO.Q6; // type
            case 'n': return ESTADO.Q9; // name
            case 'h': return ESTADO.Q12; // href
            case 'o': return ESTADO.Q15; // onclick
            case 's': return ESTADO.Q21; // style
            case 'p': return ESTADO.Q25; // placeholder
            case 'r': return ESTADO.Q35; // required
            case 'e': return ESTADO.Q42; // equal
            default:  return ESTADO.ERROR;
        }
    }
}
