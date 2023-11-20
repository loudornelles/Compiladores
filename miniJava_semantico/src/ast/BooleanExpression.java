package ast;

public class BooleanExpression extends Expression {
    public Expression left;
    public Expression right;
    public String operator;

    public BooleanExpression(Expression left, String operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Type resolveType() {
        Type leftType = left.resolveType();
        Type rightType = right.resolveType();

        Type operandTypes;

        if (operator == "&&") {
            operandTypes = Type.booleanType;
        } else if (operator == "<") {
            operandTypes = Type.intType;
        } else {
            throw new Error("Operator '" + operator + "' not supported");
        }

        if (leftType == operandTypes && rightType == operandTypes) {
            return Type.booleanType;
        } else {
            throw new Error("Operands of boolean expression must be of type boolean");
        }
    }

    public void setContextMethod(Method contexMethod) {
        super.setContextMethod(contexMethod);

        left.setContextMethod(contexMethod);
        right.setContextMethod(contexMethod);
    }
}