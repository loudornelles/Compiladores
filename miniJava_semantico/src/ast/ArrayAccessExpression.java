package ast;

public class ArrayAccessExpression extends Expression {
    public Expression expression;
    public Expression index;

    public ArrayAccessExpression(Expression expression, Expression index) {
        this.expression = expression;
        this.index = index;
    }

    public Type resolveType() {
        Type expressionType = expression.resolveType();
        if (expressionType instanceof ArrayType) {
            return ((ArrayType)expressionType).type;
        } else {
            throw new Error("Not an array.");
        }
    }

    public void setContextMethod(Method contexMethod) {
        super.setContextMethod(contexMethod);

        expression.setContextMethod(contexMethod);
        index.setContextMethod(contexMethod);
    }
}