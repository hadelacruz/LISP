/**
 * La clase {@code Variable<T>} representa una variable en el contexto de un
 * intérprete Lisp.
 * Puede contener un nombre y un valor asociado de cualquier tipo.
 *
 * @param <T> Tipo del valor almacenado en la variable.
 */
public class Variable<T> {
    private String name;
    private T value;

    /**
     * Constructor de la clase Variable.
     *
     * @param name  Nombre de la variable.
     * @param value Valor asociado a la variable.
     */
    public Variable(String name, T value) {
        this.name = name;
        this.value = value;
    }

    /**
     * Método para obtener el nombre de la variable.
     *
     * @return Nombre de la variable.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Método para establecer el nombre de la variable.
     *
     * @param name Nuevo nombre de la variable.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Método para obtener el valor almacenado en la variable.
     *
     * @return Valor de la variable.
     */
    public T getValue() {
        return this.value;
    }

    /**
     * Método para establecer el valor de la variable.
     *
     * @param value Nuevo valor de la variable.
     */
    public void setValue(T value) {
        this.value = value;
    }

    /**
     * Representación en cadena de la variable en formato {name='nombre',
     * value='valor'}.
     *
     * @return Cadena que representa la variable.
     */
    @Override
    public String toString() {
        return "{" +
                " name='" + getName() + "'" +
                ", value='" + getValue() + "'" +
                "}";
    }
}