import java.util.ArrayList;
import java.util.Stack;

/**
 * La clase Calculadora proporciona un método estático para realizar operaciones matemáticas
 * utilizando notación de prefijo y una pila para evaluar expresiones aritméticas.
 */
public class Calculadora {
    /**
     * Lista de variables globales utilizadas en las operaciones.
     */
    static ArrayList<Variable> listaVariables = Instrucciones.listasDeVariables;

    /**
     * Realiza operaciones matemáticas utilizando notación de prefijo.
     *
     * @param valoresLista Lista de valores y operadores en notación de prefijo.
     * @return Resultado de la evaluación de la expresión matemática.
     */
    public static String calculadoraOperaciones(ArrayList<String> valoresLista) {
        Stack<String> stack = new Stack<>();

        // Iterar sobre cada carácter en la cadena
        try {
            for (String valorLista : valoresLista) {
                // Ignorar espacios en blanco
                if (!valorLista.equals(")")) {// (+ (- 2 3) 5)
                    stack.push(String.valueOf(valorLista));
                } else {

                    int valor1 = Integer.parseInt(stack.pop());
                    int valor2 = Integer.parseInt(stack.pop());
                    String operador = stack.pop();
                    // Sacando de la pila paréntesis de apertura
                    stack.pop();
                    switch (operador) {
                        case "+":
                            stack.push(String.valueOf(valor2 + valor1));
                            break;

                        case "-":
                            stack.push(String.valueOf(valor2 - valor1));
                            break;

                        case "/":
                            stack.push(String.valueOf(valor2 / valor1));
                            break;

                        case "*":
                            stack.push(String.valueOf(valor2 * valor1));
                            break;
                    }

                }

            }
            return stack.pop();

        } catch (Exception e) {
            System.out.println("¡Sintaxis inválida operador!");
        }
        return "";
    }
}
