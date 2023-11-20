package ast;

public class Var {
    public Type type;
    public String name;

    public Var(Type type, String name) {
        this.type = type;
        this.name = name;
    }

    void validate() {
        type.resolveType();
    }
}