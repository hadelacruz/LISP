import java.util.List;

public class Function {
    private List<Node> parameters;
    private List<Node> body; 
    public int estado = 0;

    public Function(List<Node> parameters, List<Node> body) {
        this.parameters = parameters;
        this.body = body;
    }

    public Function(){}

    // Getters
    public List<Node> getParameters() {
        return parameters;
    }

    public List<Node> getBody() {
        return body;
    }

    public void setParameters(List<Node> parameters) {
        this.parameters = parameters;
    }
    public void setBody(List<Node> body) {
        this.body = body;
    }




   
}