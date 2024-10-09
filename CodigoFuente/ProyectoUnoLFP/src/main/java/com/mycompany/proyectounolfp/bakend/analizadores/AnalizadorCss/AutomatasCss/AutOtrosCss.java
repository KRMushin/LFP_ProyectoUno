package com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.AutomatasCss;

public class AutOtrosCss {
    enum ESTADO {
        Q0, Q1, Q2, Q3, Q4, Q5, Q6, Q7, Q8, Q9, Q10, Q11, Q12, Q13, Q14, Q15,
        Q16, Q17, Q18, Q19, Q20, Q21, Q22, Q23, Q24, Q25, Q26, Q27, Q28, Q29,
        Q30, Q31, Q32, Q33, Q34, Q35, Q36, Q37, Q38, Q39, Q40, Q41, Q42, Q43,
        ACEPTACION, ERROR
    }

    public boolean esValorCssValido(String string) {
        ESTADO estadoActual = ESTADO.Q0;

        if (string.equalsIgnoreCase("even")){
            return true;
        }

        for (int i = 0; i < string.length(); i++) {
            char c = string.charAt(i);

            switch (estadoActual) {
                case Q0:
                    estadoActual = siguienteEstadoInicial(c);
                    if (estadoActual == ESTADO.ERROR) return false;
                    break;

                case Q1: estadoActual = (c == 'x') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q2: estadoActual = (c == 'e') ? ESTADO.Q3 : ESTADO.ERROR; break;
                case Q3: estadoActual = (c == 'm') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q4: estadoActual = (c == 'm') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q5: estadoActual = (c == 'w') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q6: estadoActual = (c == 'h') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q7: estadoActual = (c == 'o') ? ESTADO.Q8 : ESTADO.ERROR; break;
                case Q8: estadoActual = (c == 'v') ? ESTADO.Q9 : ESTADO.ERROR; break;
                case Q9: estadoActual = (c == 'e') ? ESTADO.Q10 : ESTADO.ERROR; break;
                case Q10: estadoActual = (c == 'r') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q11: estadoActual = (c == 'c') ? ESTADO.Q12 : ESTADO.ERROR; break;
                case Q12: estadoActual = (c == 't') ? ESTADO.Q13 : ESTADO.ERROR; break;
                case Q13: estadoActual = (c == 'i') ? ESTADO.Q14 : ESTADO.ERROR; break;
                case Q14: estadoActual = (c == 'v') ? ESTADO.Q15 : ESTADO.ERROR; break;
                case Q15: estadoActual = (c == 'e') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q16: estadoActual = (c == 'd') ? ESTADO.Q17 : ESTADO.ERROR; break;
                case Q17: estadoActual = (c == 'd') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                case Q18: estadoActual = (c == 'v') ? ESTADO.Q19 : ESTADO.ERROR; break;
                case Q19: estadoActual = (c == 'e') ? ESTADO.Q20 : ESTADO.ERROR; break;
                case Q20: estadoActual = (c == 'n') ? ESTADO.ACEPTACION : ESTADO.ERROR; break;

                default:
                    return false;
            }

            if (estadoActual == ESTADO.ERROR) return false;
        }
        return estadoActual == ESTADO.ACEPTACION;
    }

    private ESTADO siguienteEstadoInicial(char c) {
        switch (c) {
            case 'p': return ESTADO.Q1;  // px
            case 'r': return ESTADO.Q2;  // rem
            case 'e': return ESTADO.Q4;  // em,
            case 'v': return ESTADO.Q5;  // vw, vh
            case 'h': return ESTADO.Q7;  // hover
            case 'a': return ESTADO.Q11; // active
            case 'o': return ESTADO.Q16; // odd
            // even
            default:  return ESTADO.ERROR;
        }
    }

    public boolean esLetra(String lexema) {
        for (int i = 0; i < lexema.length(); i++){
            if (!Character.isLetter(lexema.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
