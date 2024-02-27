import java.util.List;
import java.util.Stack;

public class Predicados {

    public static void sintaxisequal(List<Token> tokens) {
        // Creamos una pila
        Stack<Token> pila = new Stack<>();

        // Obtenemos la lista de variables
        List<Variable> variables = Instrucciones.listasDeVariables;

        // Verificamos si hay variables definidas
        if (variables.isEmpty()) {
            System.out.println("¡No hay variables definidas para comparar!");
            return;
        }

        // Bandera para rastrear si alguna variable no está definida
        boolean variableUndefined = false;

        // Iteramos sobre los tokens
        for (Token elemento : tokens) {
            if (!elemento.value.equals(")")) {
                pila.push(elemento);
            } else {
                // Obtenemos y almacenamos los valores de las variables que se quieren comparar
                Token Variable1 = pila.pop();
                Token Variable2 = pila.pop();

                // Verificamos si las variables son IDENTIFIER
                if (Variable1.type == Token.TokenType.IDENTIFIER && Variable2.type == Token.TokenType.IDENTIFIER) {
                    // Obtenemos los valores de las variables
                    Object value1 = getValueFromVariable(Variable1.value, variables);
                    Object value2 = getValueFromVariable(Variable2.value, variables);

                    // Si alguna de las variables no está definida, establecemos la bandera en true
                    if (value1 == null || value2 == null) {
                        variableUndefined = true;
                    }

                    // Comparamos los valores de las variables solo si ambas están definidas
                    if (value1 != null && value2 != null) {
                        if (value1.equals(value2)) {
                            System.out.println("T (los elementos son iguales)");
                        } else {
                            System.out.println("NIL (los elementos no son iguales)");
                        }
                    }
                } else {
                    System.out.println("¡Sintaxis de EQUAL inválida!");
                }
            }
        }

        // Si al menos una variable no está definida, imprimimos el mensaje de error correspondiente
        if (variableUndefined) {
            System.out.println("Defina la/s variable/s y pruebe de nuevo");
        }
    }

    // Método para obtener el valor de una variable dada su nombre
    private static Object getValueFromVariable(String variableName, List<Variable> variables) {
        for (Variable variable : variables) {
            if (variable.getName().equals(variableName)) {
                return variable.getValue();
            }
        }
        System.out.println("ERROR: La variable '" + variableName + "' no está definida.");
        return null; // La variable no está definida
    }
}
