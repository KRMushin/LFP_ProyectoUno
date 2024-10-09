import com.mycompany.proyectounolfp.backend.Secciones.Seccion;
import com.mycompany.proyectounolfp.backend.tokens.Token;
import com.mycompany.proyectounolfp.bakend.analizadores.AnalizadorCss.AnalizadorCss;
import com.mycompany.proyectounolfp.bakend.analizadores.analizadorHtml.AnalizadorHtml;

import java.util.List;

public class CSSTESTAUTOMATAS {

    public static void main(String[] args) {
        AnalizadorHtml a = new AnalizadorHtml();
        Seccion seccion = new Seccion();

        // Simulación de contenido CSS en la sección
        seccion.setContenido("<img src=\"image.jpg\" alt=\"example\" />\n" +
                "<div>\n" +
                "    <p>Texto de prueba</p>\n" +
                "</div>\n");

        List<Token> tokensCss = a.analizarSeccion(seccion);

        // Imprimir los resultados del análisis
        for (Token token : tokensCss) {
            System.out.println("Token Tipo: " + token.getTipoToken().toString() + ", Lexema: " + token.getLexema());
        }
    }
}
