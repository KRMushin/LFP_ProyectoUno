import com.mycompany.proyectounolfp.backend.Secciones.Seccion;
import com.mycompany.proyectounolfp.backend.tokens.Token;
import com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.AnalizadorCss;

import java.util.List;

public class CSSTESTAUTOMATAS {

    public static void main(String[] args) {
        AnalizadorCss analizador = new AnalizadorCss();
        Seccion seccion = new Seccion();

        // Simulación de contenido CSS en la sección
        seccion.setContenido("div > p nav + li header > * ~ span\n" +
                "header > * ~ span+\n" +
                "a  > 123\n" +
                "main > @button\n" +
                "ul > h7\n");

        List<Token> tokensCss = analizador.analizarSeccionCss(seccion);

        // Imprimir los resultados del análisis
        for (Token token : tokensCss) {
            System.out.println("Token Tipo: " + token.getTipoToken().toString() + ", Lexema: " + token.getLexema());
        }
    }
}
