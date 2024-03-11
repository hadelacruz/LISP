import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
 * Ejemplo de uso del metodo cond:
 * (cond ((< 5 0) "Negativo") ((<= 0 0) "Positivo") ((> 5 0) "Positivo"))
 * 
 * Ejemplo de uso con variables (declarar variables antes con setq): 
 * (cond ((< a b) "Incorrecto") ((> b a) "Negativo") ((> a b) "Correcto")) 
 *      suponiendo que a sea mayor que b.
 */


/**
 * La clase Conditionals proporciona un método para evaluar expresiones condicionales en lenguaje Lisp.
 * Se utiliza en conjunto con la instrucción (cond) para realizar comparaciones y retornar un resultado
 * basado en las condiciones especificadas.
 */
public class Conditionals {
    /**
     * Lista de variables globales utilizadas en las operaciones.
     */
    static ArrayList<Variable> listasDeVariables = Instrucciones.listasDeVariables;
    
    /**
     * Evalúa expresiones condicionales en notación Lisp y devuelve el resultado correspondiente.
     *
     * @param tokens Lista de tokens que representa la expresión condicional en notación Lisp.
     * @return Token resultante de la evaluación condicional.
     */
    public static Token sintaxisCond(List<Token> tokens) {
        Stack<Token> monton = new Stack<>();
        int countParentesisCierre = 0;
        StringBuilder conversion = new StringBuilder();

        try {
            for (Token elemento : tokens) {
                if (elemento.getType() == Token.guessTokenType("IDENTIFIER")) {
                    for (Variable variable : listasDeVariables) {
                        if (variable.getName().equals(elemento.value)) {
                            // Obtener el valor de la variable y realizar el casting a int
                            Object valorVariableObjeto = variable.getValue();
                            int valorVariableInt = 0; // Valor por defecto en caso de fallo en el casting
                            try {
                                valorVariableInt = Integer.parseInt(String.valueOf(valorVariableObjeto));
                            } catch (NumberFormatException e) {
                                // Manejar una excepción en caso de que el valor no sea un número válido
                                e.printStackTrace();
                            }
                            // Crear un nuevo token con el valor convertido a int
                            Token tokenValorVariable = new Token(Token.TokenType.NUMBER, String.valueOf(valorVariableInt));
                            monton.push(tokenValorVariable);
                        }
                    }        
                } else if (!elemento.value.equals(")")) {
                    monton.push(elemento);

                } else {
                    countParentesisCierre++;

                    if (countParentesisCierre == 2) {
                        countParentesisCierre = 0;

                        Token palabra = monton.pop();
                        Token b = monton.pop();
                        Token a = monton.pop();
                        Token comparador = monton.pop();

                        // Verificar que a y b no sean igual a paréntesis ni la palabra "cond"
                        if (a.type != Token.TokenType.PARENTESIS_APERTURA &&
                                a.type != Token.TokenType.PARENTESIS_CIERRE &&
                                !a.value.equalsIgnoreCase("cond") &&
                                b.type != Token.TokenType.PARENTESIS_APERTURA &&
                                b.type != Token.TokenType.PARENTESIS_CIERRE &&
                                !b.value.equalsIgnoreCase("cond")) {

                            // Si a y b son números, entonces convertir los valores a enteros y comparar
                            if (a.type == Token.TokenType.NUMBER && b.type == Token.TokenType.NUMBER) {
                                int valorA = Integer.parseInt(a.value);
                                int valorB = Integer.parseInt(b.value);

                                if (comparador.value.equals(">")) {
                                    if (valorA > valorB) {
                                        System.out.println(palabra);
                                        return palabra;
                                    }
                                } else if (comparador.value.equals("<")) {
                                    if (valorA < valorB) {
                                        System.out.println(palabra);
                                        return palabra;
                                    }
                                } else if (comparador.value.equals("=")) {
                                    if (valorA == valorB) {
                                        System.out.println(palabra);
                                        return palabra;
                                    }
                                } else if (comparador.value.equals("<=")) {
                                    if (valorA <= valorB) {
                                        System.out.println(palabra);
                                        return palabra;
                                    }
                                } else if (comparador.value.equals(">=")) {
                                    if (valorA >= valorB) {
                                        System.out.println(palabra);
                                        return palabra;
                                    }
                                } else {
                                    System.out.println("Comparador no válido: " + comparador.value);
                                }
                            } else {
                                    System.out.println("Los valores de las variables no son numéricos o no están inicializados como enteros");
                                }
                            }
                        } 
                    }
                }
        } catch (Exception e) {
            System.out.println("¡Sintaxis inválida!");
        }
        return null;
    }
}