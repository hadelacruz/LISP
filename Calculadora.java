import java.util.Stack;

public class Calculadora {

    /*
     * (COND (< 5 3) )
     */
    public static String calculadoraOperaciones (String input){
        Stack<String> stack = new Stack<>();

        // Iterar sobre cada carácter en la cadena
        for (char c : input.toCharArray()) {
            // Ignorar espacios en blanco
            if (c != ' ') {
                if (c != ')'){
                    stack.push(String.valueOf(c));
                }else{
                    try {
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

                        return stack.pop();
                        
                    } catch (Exception e) {
                        System.out.println("¡Sintaxis inválida!");
                        
                    }
                }
            }
        }
        return "";
        //System.out.println("El resultado es: " + stack.pop());
    }
}
