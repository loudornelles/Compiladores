package ast;

public class LengthExpression extends Expression {
    public Expression expression;

    public LengthExpression(Expression expression) {
        this.expression = expression;
    }

    public Type resolveType() {
        if (expression.resolveType() == Type.intArrayType || expression.resolveType() == Type.stringArrayType) {
            return Type.intType;
        } else {
            throw new Error("Invalid .length access");
        }
    }

    public void setContextMethod(Method contexMethod) {
        super.setContextMethod(contexMethod);

        expression.setContextMethod(contexMethod);
    }
}