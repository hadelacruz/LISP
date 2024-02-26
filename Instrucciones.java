import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.Collections;

public class Instrucciones {

    static ArrayList<Variable> listasDeVariables = new ArrayList<>();

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
                    nombreVariable = stack.pop();

                    if (nombreVariable.type == Token.guessTokenType("IDENTIFIER")) {
                        if (stack.pop().type == Token.guessTokenType("SETQ")) {

                            if (stack.pop().value.equals("(")) {
                                // validar que la variable no exista
                                boolean verficador = false;
                                for (Variable i : listasDeVariables) {
                                    if (i.getName().equals(nombreVariable.value))
                                        verficador = true; // El nombre de la variable ya existe
                                }
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

    public static<T> void sintaxisQuote(List<Token> tokens) {
        ArrayList<Token> lista = new ArrayList<>(tokens);
        int cont = 0;
        for (Token pa : tokens) {
            if (pa.value.equals("(") || pa.value.equals(")")) {
                cont++;
            }
            if (cont % 2 == 0) {
                if (lista.get(1).getType() == Token.guessTokenType("QUOTE")) {
                    for (int i = 2; i < lista.size() - 1; i++) {
                        // Imprimir el elemento en la posición i
                        System.out.println(lista.get(i));
                    }
                } else {
                    System.out.println("El token en la posición 1 no es 'quote' o la lista no tiene suficientes elementos.");
                }
            } else {
                System.out.println("SINTAXIS INVALIDA ");
            }
        }
    }
}

/*
 * }
 * Collections.reverse(resultado);
 * if (parentesis.size() > 2) {
 * System.out.println("(");
 * for (Token elemento : resultado) {
 * System.out.println(elemento + " ");
 * 
 * }
 * System.out.println(")");
 * 
 * } else {
 * for (Token elemento : resultado) {
 * System.out.println(elemento + " ");
 * 
 * }
 * 
 * }
 * 
 * }
 * }
 * 
 * } else {
 * System.out.println("ERROR DE SINTAXIS");
 * 
 * }
 * 
 * }
 * 
 * }
 * 
 * /*
 * if(valida.get(1).type== Token.guessTokenType("QUOTE")){
 * 
 * }
 * for(Token caracter: tokens){
 * if(caracter.value.equals("(") || caracter.value.equals(")")){
 * parentesis.add(caracter);
 * }else{
 * pila.push(caracter);
 * }
 * if(parentesis.size()%2==0){
 * for(Token element:pila){
 * pila.pop();
 * if(pila.pop().type!= Token.guessTokenType("QUOTE")){
 * resultado.add(pila.pop());
 * }
 * }
 * Collections.reverse(resultado);
 * if(parentesis.size()>2){
 * System.out.println("(");
 * for(Token elemento: resultado){
 * System.out.println(elemento + " ");
 * 
 * }
 * System.out.println(")");
 * 
 * }else{
 * for(Token elemento: resultado){
 * System.out.println(elemento + " ");
 * 
 * }
 * 
 * }
 * 
 * 
 * 
 * }else{
 * System.out.println("ERROR DE SINTAXIS");
 * }
 * 
 * }
 * 
 * 
 * }
 * }
 */
