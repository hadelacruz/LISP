import java.util.List;

public class Node {
    String tipo;
    String operador;
    String operando1;
    String operando2;
    String value;
    String value2;
    String value3;
    String var;// Nombre del parámetro
    String resultadoCond;
    // Variables para llevar el control de una condición compuesta
    List<Node> condicionesHijas;
    Node expresion1;
    Node expresion2;
    Node resultadoExpre; //
    
    String nombreFuncion;
    
}
