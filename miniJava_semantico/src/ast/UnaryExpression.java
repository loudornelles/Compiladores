package ast;

public class UnaryExpression extends Expression {
    public String operator;
    public Expression expression;

    public UnaryExpression(String operator, Expression expression) {
        this.operator = operator;
        this.expression = expression;
    }
}