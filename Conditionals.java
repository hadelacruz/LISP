import java.util.List;
import java.util.Stack;

/*
 * Ejemplo de uso del metodo cond:
 * (cond ((< 5 0) "Negativo") ((<= 0 0) "Positivo") ((> 5 0) "Positivo"))
 */

public class Conditionals {

    public static Token sintaxisCond(List<Token> tokens) {
        Stack<Token> monton = new Stack<>();
        int countParentesisCierre = 0;

        try {
            for (Token elemento : tokens) {

                if (!elemento.value.equals(")")) {
                    monton.push(elemento);

                } else {
                    countParentesisCierre++;

                    if (countParentesisCierre == 2) {
                        countParentesisCierre = 0;

                        Token palabra = monton.pop();
                        Token b = monton.pop();
                        Token a = monton.pop();
                        Token comparador = monton.pop();

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

                            if (comparador.value.equals(">")) {
                                if (valorA > valorB) {
                                    System.out.println(palabra);
                                    return palabra;
                                }
                            } else if (comparador.value.equals("<")) {
                                if (valorA < valorB) {
                                    System.out.println(palabra);
                                    return palabra;
                                }
                            } else if (comparador.value.equals("=")) {
                                if (valorA == valorB) {
                                    System.out.println(palabra);
                                    return palabra;
                                }
                            } else if (comparador.value.equals("<=")) {
                                if (valorA <= valorB) {
                                    System.out.println(palabra);
                                    return palabra;
                                } else if (comparador.value.equals(">=")) {
                                    if (valorA >= valorB) {
                                        System.out.println(palabra);
                                        return palabra;
                                    } else {
                                        System.out.println("Comparador no valido:" + comparador.value);
                                    }

                                } else {
                                    System.out.println("Variable inválida: " + a.value);
                                    System.out.println("Variable inválida: " + b.value);
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("¡Sintaxis inválida!");
        }
        return null;
    }
}