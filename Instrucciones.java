import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Instrucciones {

    static ArrayList<Variable> listasDeVariables = new ArrayList<>();

    public static <T> void sintaxisSetq(List<Token> tokens){
        Stack<Token> stack = new Stack<>();
        try {
            for (Token elemento : tokens) {
                if (!elemento.value.equals(")")) {
                    stack.push(elemento);
                } else {

                    Token nombreVariable;
                    Token valorVariable = stack.pop();

                    //Validar sintaxis de SETQ
                    nombreVariable = stack.pop();
                
                    if(nombreVariable.type == Token.guessTokenType("IDENTIFIER")){                                
                        if(stack.pop().type == Token.guessTokenType("SETQ")){

                            if(stack.pop().value.equals("(")){
                                //validar que la variable no exista
                                boolean verficador = false;
                                for (Variable i : listasDeVariables) {
                                    if(i.getName().equals(nombreVariable.value)) verficador = true; //El nombre de la variable ya existe
                                }
                                //Crear la variable y almacenarlo en la lista de variables
                                if(verficador == false){
                                    switch (valorVariable.type) { //Validar qué tipo de dato es la variable
                                        case NUMBER: 
                                            listasDeVariables.add(new Variable(nombreVariable.value, Integer.parseInt(valorVariable.value)));
                                            //System.out.println(listasDeVariables.toString());
                                            System.out.println("Variable guardado exitosamente");
                                            break;

                                        case BOOLEAN:
                                            listasDeVariables.add(new Variable(nombreVariable.value, valorVariable.value));
                                            //System.out.println(listasDeVariables.toString());
                                            System.out.println("Variable guardado exitosamente");
                                            break;
                                    
                                        case STRING:
                                            listasDeVariables.add(new Variable(nombreVariable.value, valorVariable.value));
                                            //System.out.println(listasDeVariables.toString());
                                            System.out.println("Variable guardado exitosamente");
                                            break;

                                        default:
                                            System.out.println("Tipo de dato no válido");
                                            break;
                                    }
                                          
                                }else System.out.println("¡La variable '" + nombreVariable.value + "' ya existe!");
                            }else System.out.println("¡Sintaxis de SETQ Inválida!");       
                        }else{
                            System.out.println("¡Sintaxis de SETQ Inválida!");
                        }
                    }else{
                        System.out.println("¡Sintaxis de SETQ Inválida!");
                    }
                }
                
            }
            
        } catch (Exception e) {
            System.out.println("¡Sintaxis inválida!");
        }


    }
    
}