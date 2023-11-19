package ast;

public class LengthExpression extends Expression {
    public Expression expression;

    public LengthExpression(Expression expression) {
        this.expression = expression;
    }

    public Type resolveType() {
        return Type.intType;
    }
}