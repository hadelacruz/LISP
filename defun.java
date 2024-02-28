import java.util.List;
import java.util.Stack;

public class defun {
    public static <T> void sintaxisDefun(List<Token> tokens) {
        Stack<Token> stack = new Stack<>();
        try {
            for (Token elemento : tokens) {
                if (!elemento.value.equals(")")) {
                    stack.push(elemento);
                } else {
                    Token valorParametro = stack.pop();
                    //Validar el parametro de apertura del parametro
                    if (stack.pop().value.equals("(")) {
                        Token nombreFuncion = stack.pop();
                        //Validar si el nombre de la función es IDENTIFIER
                        if (nombreFuncion.type == Token.guessTokenType("IDENTIFIER")) {
                            //Validar que antes del nombre de la función venga la palabra "defun"
                            if (stack.pop().type == Token.guessTokenType("DEFUN")) {
                                //Validar que la funcion empiece con el parentesis de apertura
                                if (stack.pop().value.equals("(")) {
                                    //EJECUTAR LA LÓGICA DE LA FUNCIÓN
                                    System.out.println("Función creada");
                                }else{
                                    System.out.println("Sintaxis de defun Inválida");
                                }
                            }else{
                                System.out.println("Sintaxis de defun Inválida");
                            }
                        }else{
                            System.out.println("Sintaxis de defun Inválida");
                        }

                    }else{
                        System.out.println("Sintaxis de defun Inválida");
                    }

                }
            }
        } catch (Exception e) {
            System.out.println("¡Sintaxis inválida!");
            }


    }

    
}
