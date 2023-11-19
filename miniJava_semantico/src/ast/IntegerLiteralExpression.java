package ast;

public class IntegerLiteralExpression extends Expression {
    public int value;

    public IntegerLiteralExpression(int value) {
        this.value = value;
    }

    public Type resolveType() {
        return Type.intType;
    }
}