import java.util.HashMap;
import java.util.Map;

/**
 * La clase {@code Environment} representa un entorno que almacena funciones
 * definidas.
 * Se utiliza para mantener un mapeo entre nombres de funciones y sus
 * definiciones correspondientes.
 */

public class Environment {
    // Un mapa que asocia nombres de funciones con sus definiciones.
    private Map<String, Object> symbols = new HashMap<>();

    /**
     * Define una función en el entorno, asociándola con su nombre.
     *
     * @param name     El nombre de la función.
     * @param function La definición de la función.
     */
    public void defineFunction(String name, Function function) {
        symbols.put(name, function);
    }

    /**
     * Obtiene la definición de una función basada en su nombre.
     *
     * @param name El nombre de la función.
     * @return La definición de la función o {@code null} si no se encuentra en el
     *         entorno.
     */
    public Function getFunction(String name) {
        return (Function) symbols.get(name);
    }

}