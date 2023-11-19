package ast;

public class BooleanLiteralExpression extends Expression {
    public boolean value;

    public BooleanLiteralExpression(boolean value) {
        this.value = value;
    }
}