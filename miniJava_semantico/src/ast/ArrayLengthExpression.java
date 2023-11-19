package ast;

public class ArrayLengthExpression extends Expression {
    public String name;

    public ArrayLengthExpression(String name) {
        this.name = name;
    }

    public Type resolveType() {
        return Type.intType;
    }
}