import java.util.ArrayList;
import java.util.List;
import java.util.Stack;


public class Instrucciones {

    static ArrayList<Variable> listasDeVariables = new ArrayList<>();

    public static <T> void sintaxisSetq(List<Token> tokens){
        Stack<Token> stack = new Stack<>();
        try {
            for (Token elemento : tokens) {
                
                if (!elemento.value.equals(")")) {// (+ (- 2 3) 5)
                    stack.push(elemento);

                } else {
                    Token valorVariable = stack.pop();
                    switch (valorVariable.type) {
                        case IDENTIFIER: //String
                            
                            break;
                        case UNKNOWN:
                            
                            break;
                        case NUMBER:
                            //Validar sintaxis de SETQ
                            Token nombreVariable = stack.pop();
                            if(nombreVariable.type == Token.guessTokenType("IDENTIFIER")){                                
                                if(stack.pop().type == Token.guessTokenType("SETQ")){

                                    if(stack.pop().value.equals("(")){
                                        //Crear la variable y almacenarlo en la lista de variables
                                        listasDeVariables.add(new Variable(nombreVariable.value, Integer.parseInt(valorVariable.value)));
                                        System.out.println(listasDeVariables.toString());
                                        System.out.println("Variable guardado exitosamente");
                                        
                                    }else System.out.println("¡Sintaxis de SETQ Inválida!");       
                                }else{
                                    System.out.println("¡Sintaxis de SETQ Inválida!");
                                }
                            }else{
                                System.out.println("¡Sintaxis de SETQ Inválida!");
                            }
                            
                        break;
                    
                        default:
                            break;
                    }




                }
                
            }
            
        } catch (Exception e) {
            System.out.println("¡Sintaxis inválida!");
        }


    }
    
}