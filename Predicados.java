import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Predicados {

    public static void sintaxisequal(List<Token> tokens) {
        // Creamos una pila
        Stack<Token> pila = new Stack<>();
        ArrayList<Token> validacion = new ArrayList<>();
        // Obtenemos las variables existentes
        List<Variable> variables = Instrucciones.listasDeVariables;
        // Verificador
        boolean variableUndefined = false;
        int cont = 0;
        int cont2 = 0;
        for (Token a : tokens) {
            if (a.value.equals(")") || a.value.equals("(")) {
                cont++;

            } else {
                validacion.add(a);
            }

        }
        for (Token tipo : validacion) {
            cont2++;
        }
        if (cont2 == 3) {
            // Iteramos sobre los tokens
            for (Token elemento : tokens) {
                if (!elemento.value.equals(")")) {
                    pila.push(elemento);
                } else {
                    // Obtenemos y almacenamos los valores de las variables que se quieren comparar
                    Token Variable1 = pila.pop();
                    Token Variable2 = pila.pop();
                    if (Variable1.type == Token.TokenType.IDENTIFIER && Variable2.type == Token.TokenType.IDENTIFIER) {
                        // Verificamos si hay variables definidas
                        if (variables.isEmpty()) {
                            System.out.println("¡No hay variables definidas para comparar!");
                            return;
                        }
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
                                System.out.println("T ");
                            } else {
                                System.out.println("NIL ");
                            }
                        }

                    //Casos en que haya una variable y un numero por comparar 
                    } else if (Variable1.type == Token.TokenType.IDENTIFIER && Variable2.type == Token.TokenType.NUMBER){
                        //Obtenemos el valor de la variable 
                        Object value1 = getValueFromVariable(Variable1.value, variables);
                        if (value1 == null) {
                            variableUndefined = true;
                        }
                        if(value1!=null){
                            //Comparamos los valores 
                            if(value1.equals(Integer.parseInt(Variable2.value))){
                                System.out.println("T ");
                            }else{
                                System.out.println("NIL ");

                            }
                        }

//Mismo caso solo que esta vez la variable 2 es la que es un identifier 
                    }else if(Variable1.type == Token.TokenType.NUMBER && Variable2.type == Token.TokenType.IDENTIFIER){
                        Object value2 = getValueFromVariable(Variable2.value, variables);
                        if(value2==null){
                            variableUndefined=true;
                        }
                        if(value2!=null){
                            if(value2.equals(Integer.parseInt(Variable1.value))){
                                System.out.println("T ");
                            }else{
                                System.out.println("NIL ");

                            }
                        }
                        //Misma logica pero para Strings 
                    } else if (Variable1.type == Token.TokenType.IDENTIFIER && Variable2.type == Token.TokenType.STRING) {
                        // Si Variable1 es un identificador y Variable2 es una cadena de caracteres
                        // Obtenemos el valor asociado al identificador Variable1
                        Object value1 = getValueFromVariable(Variable1.value, variables);
                    
                        if (value1 == null) {
                            variableUndefined = true;
                        } else {
                            // Convertimos Variable2 a cadena de caracteres eliminando las comillas si es necesario;
                    
                            // Comparamos el valor de Variable1 con stringValue2
                            if (value1.toString().equals(Variable2.value)) {
                                System.out.println("T ");
                            } else {
                                System.out.println("NIL ");
                            }
                        }
                    } else if (Variable1.type == Token.TokenType.STRING && Variable2.type == Token.TokenType.IDENTIFIER) {
                        // Si Variable1 es una cadena de caracteres y Variable2 es un identificador
                        // Obtenemos el valor asociado al identificador Variable2
                        Object value2 = getValueFromVariable(Variable2.value, variables);
                    
                        if (value2 == null) {
                            variableUndefined = true;
                        } else {
                    
                            // Comparamos el valor de Variable2 con stringValue1
                            if (value2.toString().equals(Variable1.value)) {
                                System.out.println("T ");
                            } else {
                                System.out.println("NIL ");
                            }
                        }

                    }
                    else {
                        if (Variable1.value.equals(Variable2.value)) {
                            System.out.println("T ");
                        } else {
                            System.out.println("NIL ");
                        }
                    }
                }
            }

            // Si al menos una variable no está definida, imprimimos el mensaje de error
            // correspondiente
            if (variableUndefined) {
                System.out.println("Defina la/s variable/s y pruebe de nuevo");
            }
        } else {
            System.out.println("¡SINTAXIS EQUAL INVALIDA ! ");
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

    public static <T> void sintaxisList(List<Token> tokens) {
        // ArrayList para almacenar las variables
        ArrayList<String> variables = new ArrayList<>();
        // Validar si estamos dentro de una lista
        boolean dentroLista = false;

        for (Token token : tokens) {
            if (token.value.equals("(")) {
                dentroLista = true;
            } else if (token.value.equals(")")) {
                dentroLista = false;
            } else if (dentroLista && token.type != Token.guessTokenType("LIST")) {
                variables.add(token.value);
            }
        }
        // Imprimir las variables encontradas
        System.out.println(variables);
    }

    public static <T> void sintaxisMenorQue(List<Token> tokens) {
        // Creamos una pila
        Stack<Token> pila = new Stack<>();
        for (Token elemento : tokens) {
            // metemos los elementos del input a la pila exceptuando los parentesis de
            // cierre
            if (!elemento.value.equals(")")) {
                pila.push(elemento);
            } else {
                // Obtenemos y almacenamos los valores de las variables que se quieren comparar
                Token Variable1;
                Token Variable2 = pila.pop();
                Variable1 = pila.pop();

                int numero1 = Integer.parseInt(Variable1.value);
                int numero2 = Integer.parseInt(Variable2.value);
                // Si detectamos el equal procedemos
                if (pila.pop().value.equals("<")) {
                    if (numero1 < numero2) {
                        System.out.println("T");
                    } else {
                        System.out.println("NIL");
                    }
                } else
                    System.out.println("¡Sintaxis de Menor Inválida!");
            }
        }
    }

    public static <T> void sintaxisMayorQue(List<Token> tokens) {
        // Creamos una pila
        Stack<Token> pila = new Stack<>();
        for (Token elemento : tokens) {
            // metemos los elementos del input a la pila exceptuando los parentesis de
            // cierre
            if (!elemento.value.equals(")")) {
                pila.push(elemento);
            } else {
                // Obtenemos y almacenamos los valores de las variables que se quieren comparar
                Token Variable1;
                Token Variable2 = pila.pop();
                Variable1 = pila.pop();

                int numero1 = Integer.parseInt(Variable1.value);
                int numero2 = Integer.parseInt(Variable2.value);
                // Si detectamos el equal procedemos
                if (pila.pop().value.equals(">")) {
                    if (numero1 > numero2) {
                        System.out.println("T");
                    } else {
                        System.out.println("NIL");
                    }
                } else
                    System.out.println("¡Sintaxis de Menor Inválida!");
            }
        }
    }

    public static <T> void sintaxisAtom(List<Token> tokens) {
        // Creamos una pila para almacenar los tokens
        Stack<Token> stack = new Stack<>();

        try {
            
            List<String> cadenaElementoTokens = new ArrayList<>();
            String cadenaResultante = "";
            for (Token elemento : tokens) {
                cadenaElementoTokens.add(elemento.value);
            }
            cadenaResultante = String.join(" ", cadenaElementoTokens);
        
            // Iteramos sobre cada token en la lista de tokens
            for (Token elemento : tokens) {
                if (tokens.size() > 4) { // Condicional cuando no es un atomo (atom (1 2 3)) 
                    
                    // Definir la expresión regular
                    String patron = "\\( atom \\( (\\w+ ?)+ \\) \\)";

                    // Usar Pattern y Matcher para verificar si el string coincide con el patrón
                    Pattern pattern = Pattern.compile(patron);
                    Matcher matcher = pattern.matcher(cadenaResultante);

                    if (matcher.matches()){
                        System.out.println("Nil");
                        break;
                    }else{
                        System.out.println("Sintaxis inválida");
                        break;
                    }
                    
                    
                } else { // Condicional cuando si es un átomo (atom 1)
                    if (!elemento.value.equals(")")) { 
                        stack.push(elemento);

                    } else {
                        // Obtenemos el token anterior al paréntesis de cierre
                        T variable = (T) stack.pop().value;
                        
                        boolean state = variable instanceof List<?>; 

                        if (!state){
                            System.out.println("T");
                        }
                    }
                }
            }
            

        } catch (Exception e) {
            // Si ocurre una excepción, emitimos un mensaje de error de sintaxis
            System.out.println("¡Sintaxis inválida!");
        }
    }

}
