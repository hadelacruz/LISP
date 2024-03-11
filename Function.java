import java.util.List;

/**
 * La clase {@code Function} representa una función en el lenguaje de
 * programación.
 * Contiene una lista de parámetros y una lista de nodos que forman el cuerpo de
 * la función.
 */
public class Function {
    private List<Node> parameters;
    private List<Node> body;
    public int estado = 0;

    /**
     * Construye una instancia de {@code Function} con listas de parámetros y cuerpo
     * especificadas.
     * 
     * @param parameters Lista de nodos que representan los parámetros de la
     *                   función.
     * @param body       Lista de nodos que forman el cuerpo de la función.
     */
    public Function(List<Node> parameters, List<Node> body) {
        this.parameters = parameters;
        this.body = body;
    }

    public Function() {
    }

    /**
     * Obtiene la lista de parámetros de la función.
     *
     * @return Lista de nodos que representan los parámetros.
     */
    public List<Node> getParameters() {
        return parameters;
    }

    /**
     * Obtiene la lista de nodos que forman el cuerpo de la función.
     *
     * @return Lista de nodos que forman el cuerpo de la función.
     */
    public List<Node> getBody() {
        return body;
    }

    /**
     * Establece la lista de parámetros de la función.
     *
     * @param parameters Lista de nodos que representan los parámetros de la
     *                   función.
     */
    public void setParameters(List<Node> parameters) {
        this.parameters = parameters;
    }

    /**
     * Establece la lista de nodos que forman el cuerpo de la función.
     *
     * @param body Lista de nodos que forman el cuerpo de la función.
     */
    public void setBody(List<Node> body) {
        this.body = body;
    }

}