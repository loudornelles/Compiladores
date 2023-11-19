package ast;

public class NewArrayExpression extends Expression {
    public Type type;
    public Expression size;

    public NewArrayExpression(Type type, Expression size) {
        this.type = type;
        this.size = size;
    }

    public Type resolveType() {
        return new ArrayType(type);
    }
}