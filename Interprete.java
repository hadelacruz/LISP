import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class Interprete {
    
    // Validar parentesis
    public boolean validarParentesis(String input){
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
                    System.out.println("Error de sintaxis.");
                    return false;
                }
                 
            }
        }

        if(stack.isEmpty()) return true;
        else return false;
    }

    public void createTokenInput(String input){
       
    }
    
}
