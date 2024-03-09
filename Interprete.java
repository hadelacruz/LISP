import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Interprete {

    // Validar parentesis
    public static boolean validarParentesis(String input) {
        // Pila de paréntesis
        Deque<String> stack = new ArrayDeque<>();

        // Input en array
        ArrayList<String> listaInput = new ArrayList<>();

        // Iterar sobre cada carácter en la cadena
        for (char c : input.toCharArray()) {
            // Ignorar espacios en blanco
            if (c != ' ') {
                // Agregar el carácter como cadena a la lista
                listaInput.add(String.valueOf(c));
            }
        }

        // Se valida el parentesis
        for (String caracterInput : listaInput) {
            if (caracterInput.equals("(")) {
                stack.add(caracterInput);

            } else if (caracterInput.equals(")")) {
                try {
                    stack.pop();

                } catch (Exception e) {
                    // parentesis de mas
                    return false;
                }

            }
        }

        if (stack.isEmpty())
            return true;
        else
            return false;
    }

    static ArrayList<Variable> listasDeVariables = Instrucciones.listasDeVariables;

    public static void interpreteListTokens(List<Token> tokens) {
        for (Token token : tokens) {
            boolean caseEvaluated = false;
            // Validar que tipo de token es y en base a eso validar que hacer.
            switch (token.type) {
                case OPERADOR: // Case cuando por identifica que viene un operador aritmético.
                    ArrayList<String> valoresLista = new ArrayList<>();
                    for (Token elemento : tokens) {
                        if (elemento.type.equals(Token.TokenType.IDENTIFIER)) {
                            for (Variable variable : listasDeVariables) {
                                if (variable.getName().equals(elemento.value)) {
                                    valoresLista.add(String.valueOf(variable.getValue()));
                                }
                            }
                        } else {
                            valoresLista.add(String.valueOf(elemento.value));
                        }
                    }
                    caseEvaluated = true;
                    // Se imprime el resultado de la operación aritmética.
                    System.out.println(Calculadora.calculadoraOperaciones(valoresLista));
                    break;

                case SETQ:
                    caseEvaluated = true;
                    Instrucciones.sintaxisSetq(tokens);
                    break;

                case QUOTE:
                    caseEvaluated = true;
                    Instrucciones.sintaxisQuote(tokens);
                    break;

                case EQUAL:
                    caseEvaluated = true;
                    Predicados.sintaxisequal(tokens);
                    break;

                case COND:
                    caseEvaluated = true;
                    Conditionals.sintaxisCond(tokens);
                    break;

                case LIST:
                    caseEvaluated = true;
                    Predicados.sintaxisList(tokens);
                    break;

                case MAYORQUE:
                    caseEvaluated = true;
                    Predicados.sintaxisMayorQue(tokens);
                    break;

                case MENORQUE:
                    caseEvaluated = true;
                    Predicados.sintaxisMenorQue(tokens);
                    break;

                case DEFUN:
                    defun.saveDefun(tokens);
                    caseEvaluated = true;
                    break;

                case IDENTIFIER:
                    String resFunc = defun.executeFunction(tokens);
                    System.out.println("El resultado final de la funcion es: " + resFunc);
                    caseEvaluated = true;
                    break;

                case ATOM:
                    caseEvaluated = true;
                    Predicados.sintaxisAtom(tokens);
                    break;
            }

            // Si entro a un caso del switch salir del bucle FOR.
            if (caseEvaluated) {
                break;
            }
        }
    }
}