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

        if (leftType == Type.booleanType && rightType == Type.booleanType) {
            return Type.booleanType;
        } else {
            throw new Error("Operands of boolean expression must be of type boolean");
        }
    }
}