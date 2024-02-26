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
                    if (monton.size() >= 2 && monton.elementAt(monton.size() - 2).value.equals(")")) {
                        // Dos paréntesis de cierre seguidos indican el final de una expresión
                        // condicional
                        System.out.println("Fin de la expresión condicional");
                        break;
                    }

                    Token b = monton.pop();
                    Token a = monton.pop();
                    Token comparador = monton.pop();

                    // Verificar que a y b no sean paréntesis ni la palabra "cond"
                    if (a.type != Token.TokenType.PARENTESIS_APERTURA &&
                            a.type != Token.TokenType.PARENTESIS_CIERRE &&
                            !a.value.equalsIgnoreCase("cond") &&
                            b.type != Token.TokenType.PARENTESIS_APERTURA &&
                            b.type != Token.TokenType.PARENTESIS_CIERRE &&
                            !b.value.equalsIgnoreCase("cond")) {
                        /* 
                        System.out.println("a:" + a);
                        System.out.println("b:" + b);
                        System.out.println("comparador:" + comparador);
                        */

                        // Convertir los valores de a y b a enteros
                        int valorA = Integer.parseInt(a.value);
                        int valorB = Integer.parseInt(b.value);

                        // Evaluar la comparación
                        boolean resultado = false;
                        if (comparador.value.equals(">")) {
                            resultado = valorA > valorB;
                        } else if (comparador.value.equals("<")) {
                            resultado = valorA < valorB;
                        } else if (comparador.value.equals("=")) {
                            resultado = valorA == valorB;
                        }

                        // Imprimir el resultado
                        System.out.println("Resultado de la comparación: " + resultado);
                    } else {
                        System.out.println("Variable inválida: " + a.value);
                        System.out.println("Variable inválida: " + b.value);
                    }

                    // Al llegar al cierre de paréntesis, evaluamos la condición
                    // El formato esperado es (cond (condición resultado) (condición resultado) ...)
                    if (monton.pop().type == Token.guessTokenType("COND")) {
                        if (!monton.isEmpty() && monton.peek().value.equals("(")) {
                            // Sintaxis de COND correcta
                            System.out.println("Sintaxis de COND correcta");
                        } else {
                            System.out.println("¡Sintaxis de COND inválida!");
                        }
                    }
                }                    
            }
        } catch (Exception e) {
            //System.out.println("¡Sintaxis inválida!");
        }
    }
}