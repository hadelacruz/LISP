import java.util.ArrayList;
import java.util.List;

/**
 * La clase {@code LispTokenizer} proporciona un método para tokenizar una
 * cadena de entrada en el contexto de un lenguaje Lisp simplificado.
 */
public class LispTokenizer {
    /**
     * Tokeniza una cadena de entrada en una lista de tokens.
     *
     * @param input Cadena de entrada que se va a tokenizar.
     * @return Lista de tokens generada a partir de la cadena de entrada.
     */
    public static List<Token> tokenize(String input) {
        // Creación de lista de tipo token
        List<Token> tokens = new ArrayList<>();
        StringBuilder tokenBuilder = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);

            // Separar por espacio o inicio/fin de token
            if (c == ' ' || c == '(' || c == ')') {
                if (tokenBuilder.length() > 0) {
                    // Agregar el token acumulado
                    tokens.add(new Token(Token.guessTokenType(tokenBuilder.toString()), tokenBuilder.toString()));
                    tokenBuilder = new StringBuilder();
                }
                if (c == '(') { // Apertura
                    // Agregar paréntesis como token
                    tokens.add(new Token(Token.TokenType.PARENTESIS_APERTURA, String.valueOf(c)));
                }
                if (c == ')') { // Cierre
                    // Agregar paréntesis como token
                    tokens.add(new Token(Token.TokenType.PARENTESIS_CIERRE, String.valueOf(c)));
                }

            } else {
                // Acumular caracteres para el próximo token
                tokenBuilder.append(c);
            }
        }

        // Agregar el último token si existe
        if (tokenBuilder.length() > 0) {
            tokens.add(new Token(Token.guessTokenType(tokenBuilder.toString()), tokenBuilder.toString()));
        }

        return tokens;
    }
}