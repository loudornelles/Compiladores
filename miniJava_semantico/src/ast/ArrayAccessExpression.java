package ast;

public class ArrayAccessExpression extends Expression {
    public Expression expression;
    public Expression index;

    public ArrayAccessExpression(Expression expression, Expression index) {
        this.expression = expression;
        this.index = index;
    }
}