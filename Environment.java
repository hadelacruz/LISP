import java.util.HashMap;
import java.util.Map;

public class Environment {
    private Map<String, Object> symbols = new HashMap<>();

    public void defineFunction(String name, Function function) {
        symbols.put(name, function);
    }

    public Function getFunction(String name) {
        return (Function) symbols.get(name);
    }

}