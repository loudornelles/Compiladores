package ast;

public class UnaryExpression extends Expression {
    public String operator;
    public Expression expression;

    public UnaryExpression(String operator, Expression expression) {
        this.operator = operator;
        this.expression = expression;
    }

    public Type resolveType() {
        if (this.operator == "!") {
            return Type.booleanType;
        } else {
            throw new Error("Unsupported operator'" + operator + "'");
        }
    }

    public void setContextMethod(Method contexMethod) {
        super.setContextMethod(contexMethod);

        expression.setContextMethod(contexMethod);
    }
}