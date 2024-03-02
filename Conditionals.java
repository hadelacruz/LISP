import java.util.List;
import java.util.Stack;

/*
 * Ejemplo de uso del metodo cond:
 * (cond ((< 5 0) "Negativo") ((<= 0 0) "Positivo") ((> 5 0) "Positivo"))
 * 
 * Ejemplo de uso con variables (declarar variables antes con setq): 
 * (cond ((< a b) "Incorrecto") ((> b a) "Negativo") ((> a b) "Correcto")) 
 *      suponiendo que a sea mayor que b.
 */

public class Conditionals {

    public static Token sintaxisCond(List<Token> tokens, List<Variable> variables) {
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

                            // Si a y b son números, entonces convertir los valores a enteros y comparar
                            if (a.type == Token.TokenType.NUMBER && b.type == Token.TokenType.NUMBER) {
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
                                    }
                                } else if (comparador.value.equals(">=")) {
                                    if (valorA >= valorB) {
                                        System.out.println(palabra);
                                        return palabra;
                                    }
                                } else {
                                    System.out.println("Comparador no válido: " + comparador.value);
                                }
                            } else { // Si a o b no son números, intentar buscarlos en la lista de variables
                                Variable variableA = null;
                                Variable variableB = null;

                                for (Variable var : variables) {
                                    if (a.value.equals(var.getName())) {
                                        variableA = var;
                                    }
                                    if (b.value.equals(var.getName())) {
                                        variableB = var;
                                    }
                                }

                                // Si se encontraron las variables en la lista, comparar sus valores
                                if (variableA.getValue() instanceof Integer && variableB.getValue() instanceof Integer) {
                                    int valorA = (int) variableA.getValue();
                                    int valorB = (int) variableB.getValue();
                                
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
                                        }
                                    } else if (comparador.value.equals(">=")) {
                                        if (valorA >= valorB) {
                                            System.out.println(palabra);
                                            return palabra;
                                        }
                                    } else {
                                        System.out.println("Comparador no válido: " + comparador.value);
                                    }
                                } else {
                                    System.out.println("Los valores de las variables no son numéricos o no están inicializados como enteros");
                                }
                            }
                        } else {
                            System.out.println("Variable inválida: " + a.value);
                            System.out.println("Variable inválida: " + b.value);
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