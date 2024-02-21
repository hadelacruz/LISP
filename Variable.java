public class Variable<T> {
    private String name;
    private T value;


    public Variable(String name, T value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public T getValue() {
        return this.value;
    }

    public void setValue(T value) {
        this.value = value;
    }


    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }


    

}