import java.util.List;
import java.util.Stack;

public class Conditionals {

    public static void sintaxisCond(List<Token> tokens) {
        Stack<Token> monton = new Stack<>();

        try {
            for (Token elemento : tokens) {

                if (!elemento.value.equals(")")) {
                    monton.push(elemento);
                    
                } else {
                    // Dos paréntesis de cierre seguidos indican el final de una expresión
                    // condicional
                    // Si el stack tiene mas de dos elementos y el penultimo elemento es un
                    // parentesis de cierre entonces ejecuta la logica
                    if (monton.size() >= 2 && monton.elementAt(monton.size() - 2).value.equals(")")) {
                        break;
                    }

                        Token b = monton.pop();
                        Token a = monton.pop();
                        Token comparador = monton.pop();
                        /* 
                        System.out.println("a:" + a);
                        System.out.println("b:" + b);
                        System.out.println("comparador:" + comparador);
                        */

                        // Verificar que a y b no sean igual a paréntesis ni la palabra "cond"
                        if (a.type != Token.TokenType.PARENTESIS_APERTURA &&
                                a.type != Token.TokenType.PARENTESIS_CIERRE &&
                                !a.value.equalsIgnoreCase("cond") &&
                                b.type != Token.TokenType.PARENTESIS_APERTURA &&
                                b.type != Token.TokenType.PARENTESIS_CIERRE &&
                                !b.value.equalsIgnoreCase("cond")) {

                            // Convertir los valores de a y b a enteros
                            int valorA = Integer.parseInt(a.value);
                            int valorB = Integer.parseInt(b.value);

                            if (monton.pop().value.equals("(")) {
                                if (monton.pop().type == Token.guessTokenType("COND")) {
                                    if (monton.pop().value.equals("(")) {

                                        // Evaluar la comparación
                                        boolean resultado = false;
                                        if (comparador.value.equals(">")) {
                                            resultado = valorA > valorB;
                                        } else if (comparador.value.equals("<")) {
                                            resultado = valorA < valorB;
                                        } else if (comparador.value.equals("=")) {
                                            resultado = valorA == valorB;
                                        } else {
                                            System.out.println("Comparador no valido:" + comparador.value);
                                        }

                                        // Imprimir el resultado
                                        System.out.println(resultado);

                                    } else {
                                        System.out.println("¡Sintaxis de COND inválida!");
                                    }
                                } else {
                                    System.out.println("¡Sintaxis de COND inválida!");
                                }
                            } else {
                                System.out.println("¡Sintaxis de COND inválida!");
                            }
                        } else {
                            System.out.println("Variable inválida: " + a.value);
                            System.out.println("Variable inválida: " + b.value);
                        }
                    } 
                }
        } catch (Exception e) {
            //System.out.println("¡Sintaxis inválida!");
        }
    }
}
