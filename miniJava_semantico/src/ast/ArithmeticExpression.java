package ast;

public class ArithmeticExpression extends Expression {
    public Expression left;
    public Expression right;
    public String operator;

    public ArithmeticExpression(Expression left, String operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    public Type resolveType() {
        Type leftType = left.resolveType();
        Type rightType = right.resolveType();

        if (leftType == Type.intType && rightType == Type.intType) {
            return Type.intType;
        } else {
            throw new Error("Operands of arithmetic expression must be of type int");
        }
    }
}