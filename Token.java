/**
 * La clase {@code Token} representa tokens utilizados en el análisis léxico de
 * expresiones Lisp.
 * Contiene una enumeración para los tipos de tokens y métodos para evaluar y
 * obtener el tipo de un token.
 */
public class Token {

    // Enumeración para tipos de tokens
    public enum TokenType {
        IDENTIFIER, NUMBER, PARENTESIS_APERTURA, PARENTESIS_CIERRE, UNKNOWN,
        ATOM, DEFUN, COND, LIST, EQUAL, SETQ, MAYORQUE, MENORQUE, SUMA, QUOTE,
        RESTA, MULTIPLICACION, DIVISION, OPERADOR, BOOLEAN, STRING, EQUAL_SIGN,
        MENOROIGUALQUE, MAYOROIGUALQUE
    }

    // Atributos de un token
    Token.TokenType type;
    String value;

    /**
     * Constructor de la clase Token.
     * 
     * @param type  Tipo del token.
     * @param value Valor del token.
     */
    Token(TokenType type, String value) {
        this.type = type;
        this.value = value;
    }

    /**
     * Método para evaluar el tipo de token basado en su valor.
     * 
     * @param token Valor del token a evaluar.
     * @return Tipo de token correspondiente.
     */
    public static TokenType guessTokenType(String token) {

        if (token.matches("-?\\d+(\\.\\d+)?")) {
            return TokenType.NUMBER;
        } else if (token.matches("(?i)quote")) {
            return TokenType.QUOTE;

        } else if (token.matches("(?i)atom")) {
            return TokenType.ATOM;

        } else if (token.matches("(?i)defun")) {
            return TokenType.DEFUN;

        } else if (token.matches("(?i)cond")) {
            return TokenType.COND;

        } else if (token.matches("(?i)list")) {
            return TokenType.LIST;

        } else if (token.matches("(?i)equal")) {
            return TokenType.EQUAL;

        } else if (token.matches("(?i)setq")) {
            return TokenType.SETQ;

        } else if (token.matches("(?i)>")) {
            return TokenType.MAYORQUE;

        } else if (token.matches("(?i)<")) {
            return TokenType.MENORQUE;

        } else if (token.matches("(?i)=")) {
            return TokenType.EQUAL_SIGN;

        } else if (token.matches("\\(")) {
            return TokenType.PARENTESIS_APERTURA;

        } else if (token.matches("\\)")) {
            return TokenType.PARENTESIS_CIERRE;

        } else if (token.matches("[-+*/]")) {
            return TokenType.OPERADOR;

        } else if (token.matches("^(t|nil)$")) {
            return TokenType.BOOLEAN;

        } else if (token.matches("[a-zA-Z][-a-zA-Z0-9_]*")) {
            return TokenType.IDENTIFIER;

        } else if (token.matches("^\"[^\"]*\"$")) {
            return TokenType.STRING;

        } else if (token.matches("^<=?$")) {
            return TokenType.MENOROIGUALQUE;

        } else if (token.matches("^>=?$")) {
            return TokenType.MAYOROIGUALQUE;

        } else {
            return TokenType.UNKNOWN;
        }
    }

    /**
     * Representación en cadena del token en formato (tipo, valor).
     * 
     * @return Cadena que representa el token.
     */
    @Override
    public String toString() {
        return String.format("(%s, %s)", type, value);
    }

    /**
     * Método para obtener el tipo de token.
     * 
     * @return Tipo de token.
     */
    public TokenType getType() {
        return type;
    }
}