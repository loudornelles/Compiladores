package ast;

public class LengthExpression {
    public Expression expression;

    public LengthExpression(Expression expression) {
        this.expression = expression;
    }

    public Type resolveType() {
        return Type.intType;
    }
}