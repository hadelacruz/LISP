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

    public static void interpreteListTokens(List<Token> tokens) {
        for (Token token : tokens) {
            boolean caseEvaluated = false;
            // Validar que tipo de token es y en base a eso validar que hacer.
            switch (token.type) {
                case OPERADOR: // Case cuando por identifica que viene un operador aritmético.
                    caseEvaluated = true;
                    StringBuilder cadenaResultante = new StringBuilder();
                    for (Token elemento : tokens) {
                        cadenaResultante.append(elemento.value);
                    }
                    
                    // Se imprime el resultado de la operación aritmética.
                    System.out.println(Calculadora.calculadoraOperaciones(String.valueOf(cadenaResultante)));
                    break;
                    
            }

            // Si entro a un caso del switch salir del bucle FOR.
            if (caseEvaluated){
                break;
            }
            
        }
    }

}