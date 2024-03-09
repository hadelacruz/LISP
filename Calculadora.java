import java.util.ArrayList;
import java.util.Stack;

public class Calculadora {
    static ArrayList<Variable> listaVariables = Instrucciones.listasDeVariables;

    /*
     *
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
