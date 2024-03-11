import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * La clase {@code Instrucciones} contiene métodos estáticos que implementan la
 * lógica de ejecución
 * de instrucciones específicas en el lenguaje de programación.
 */
public class Instrucciones {

    // Lista de variables utilizada para almacenar variables definidas durante la
    // ejecución del programa.
    public static ArrayList<Variable> listasDeVariables = new ArrayList<>();

    /**
     * Implementación de la sintaxis SETQ para la asignación de valores a variables.
     *
     * @param tokens Lista de tokens que representan la expresión SETQ.
     * @param <T>    Tipo de elementos en la lista de tokens.
     */
    public static <T> void sintaxisSetq(List<Token> tokens) {
        Stack<Token> stack = new Stack<>();
        try {
            for (Token elemento : tokens) {
                if (!elemento.value.equals(")")) {
                    stack.push(elemento);
                } else {

                    Token nombreVariable;
                    Token valorVariable = stack.pop();

                    // Validar sintaxis de SETQ
                    // Validar sintaxis de SETQ
                    nombreVariable = stack.pop();

                    if (nombreVariable.type == Token.guessTokenType("IDENTIFIER")) {
                        if (stack.pop().type == Token.guessTokenType("SETQ")) {

                            if (stack.pop().value.equals("(")) {
                                // validar que la variable no exista
                                // validar que la variable no exista
                                boolean verficador = false;
                                for (Variable i : listasDeVariables) {
                                    if (i.getName().equals(nombreVariable.value))
                                        verficador = true; // El nombre de la variable ya existe
                                }
                                // Crear la variable y almacenarlo en la lista de variables
                                // Crear la variable y almacenarlo en la lista de variables
                                if (verficador == false) {
                                    switch (valorVariable.type) { // Validar qué tipo de dato es la variable
                                        case NUMBER:
                                            listasDeVariables.add(new Variable(nombreVariable.value,
                                                    Integer.parseInt(valorVariable.value)));
                                            // System.out.println(listasDeVariables.toString());
                                            System.out.println("Variable guardado exitosamente");
                                            break;

                                        case BOOLEAN:
                                            listasDeVariables
                                                    .add(new Variable(nombreVariable.value, valorVariable.value));
                                            // System.out.println(listasDeVariables.toString());
                                            System.out.println("Variable guardado exitosamente");
                                            break;

                                        case STRING:
                                            listasDeVariables
                                                    .add(new Variable(nombreVariable.value, valorVariable.value));
                                            // System.out.println(listasDeVariables.toString());
                                            System.out.println("Variable guardado exitosamente");
                                            break;

                                        default:
                                            System.out.println("Tipo de dato no válido");
                                            break;
                                    }
                                } else
                                    System.out.println("¡La variable '" + nombreVariable.value + "' ya existe!");
                            } else
                                System.out.println("¡Sintaxis de SETQ Inválida!");
                        } else {
                            System.out.println("¡Sintaxis de SETQ Inválida!");
                        }
                    } else {
                        System.out.println("¡Sintaxis de SETQ Inválida!");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("¡Sintaxis inválida!");
        }
    }

    /**
     * Implementación de la sintaxis QUOTE para la impresión de elementos sin
     * evaluar.
     *
     * @param tokens Lista de tokens que representan la expresión QUOTE.
     * @param <T>    Tipo de elementos en la lista de tokens.
     */
    public static <T> void sintaxisQuote(List<Token> tokens) {
        ArrayList<Token> lista = new ArrayList<>(tokens);

        if (lista.get(1).type == Token.guessTokenType("QUOTE")) {
            for (int i = 2; i < lista.size() - 1; i++) {

                // Imprimir el elemento en la posición i
                System.out.print(lista.get(i).value + " ");
            }
            System.out.println();
        } else {
            System.out.println("Sintaxis Quote invalida");
        }

    }
}
