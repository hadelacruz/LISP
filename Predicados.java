

import java.util.List;
import java.util.Stack;

public class Predicados {

    public static <T> void sintaxisequal(List<Token> tokens) {
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

//Si detectamos el equal procedemos
                    if (pila.pop().type == Token.guessTokenType("EQUAL")) {
                        //Sea cual sea el tipo de de dato debe ser igual al valor de la otra variable
                        if(Variable1.value.equals(Variable2.value)) {
                            System.out.println("T (los elementos son iguales)");

                        }else{
                            System.out.println("NIL(los elementos no son iguales)");
                        }


                        //
                    } else System.out.println("¡Sintaxis de EQUAl Inválida!");

                    ;
                }
                }





    }}

