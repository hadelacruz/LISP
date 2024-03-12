import java.util.List;
/**
 * La clase {@code Node} representa un nodo en la estructura utilizado por el intérprete Lisp.
 * Cada nodo puede tener diferentes propiedades dependiendo de su tipo, como operadores, operandos, valores, variables y más.
 */
public class Node {
    String tipo;
    String operador;
    String operando1;
    String operando2;
    String value;
    String value2;
    String value3;
    String var; // Nombre del parámetro
    String resultadoCond;

    // Variables para llevar el control de una condición compuesta
    List<Node> condicionesHijas;
    Node expresion1;
    Node expresion2;
    Node resultadoExpre;
    String nombreFuncion;    
}
