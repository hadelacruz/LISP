import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Predicados {

    public static void sintaxisequal(List<Token> tokens) {
        // Creamos una pila
        Stack<Token> pila = new Stack<>();
        ArrayList<Token>  validacion = new ArrayList<>();
        //Obtenemos las variables existentes
        List<Variable> variables = Instrucciones.listasDeVariables;
        //Verificador
        boolean variableUndefined = false;
        int cont =0;
        int cont2= 0;
        for(Token a : tokens){
            if(a.value.equals(")") || a.value.equals("(")){
                cont ++;
 

            }else{
                validacion.add(a);
            }

        }
        for( Token i : validacion){
            cont2++;
        }
        if(cont2==3){
            //Iteramos sobre los tokens
            for(Token elemento:tokens){
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
                            System.out.println("T (los elementos son iguales)");
                        } else {
                            System.out.println("NIL (los elementos no son iguales)");
                        }
                    }
                } else {
                    if (Variable1.value.equals(Variable2.value)) {
                        System.out.println("T (los elementos son iguales)");
                    } else {
                        System.out.println("NIL (los elementos no son iguales)");
                    }
                }
            }
        }

        // Si al menos una variable no está definida, imprimimos el mensaje de error correspondiente
        if (variableUndefined) {
            System.out.println("Defina la/s variable/s y pruebe de nuevo");
        }
    }else{
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
        //Creamos una pila
        Stack<Token> pila= new Stack<>();
            for (Token elemento : tokens) {
                //metemos los elementos del input a la pila exceptuando los parentesis de cierre
                if (!elemento.value.equals(")")) {
                    pila.push(elemento);
                } else {
                    //Obtenemos y almacenamos los valores de las variables que se quieren comparar
                    Token Variable1;
                    Token Variable2 = pila.pop();
                    Variable1 = pila.pop();

                    int numero1 = Integer.parseInt(Variable1.value);
                    int numero2 = Integer.parseInt(Variable2.value);
                    //Si detectamos el equal procedemos
                    if (pila.pop().value.equals("<")) {
                        if(numero1 < numero2) {
                            System.out.println("T");
                        }else{
                            System.out.println("NIL");
                        }
                    } else System.out.println("¡Sintaxis de Menor Inválida!");
                }
            }
    }

    public static <T> void sintaxisMayorQue(List<Token> tokens) {
        //Creamos una pila
        Stack<Token> pila= new Stack<>();
            for (Token elemento : tokens) {
                //metemos los elementos del input a la pila exceptuando los parentesis de cierre
                if (!elemento.value.equals(")")) {
                    pila.push(elemento);
                } else {
                    //Obtenemos y almacenamos los valores de las variables que se quieren comparar
                    Token Variable1;
                    Token Variable2 = pila.pop();
                    Variable1 = pila.pop();

                    int numero1 = Integer.parseInt(Variable1.value);
                    int numero2 = Integer.parseInt(Variable2.value);
                    //Si detectamos el equal procedemos
                    if (pila.pop().value.equals(">")) {
                        if(numero1 > numero2) {
                            System.out.println("T");
                        }else{
                            System.out.println("NIL");
                        }
                    } else System.out.println("¡Sintaxis de Menor Inválida!");
                }
            }
    }
}
