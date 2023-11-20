package ast;

public class PrintStatement extends Statement {
    public Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }

    void validate() {
        expression.resolveType(); // make sure the type is valid
    }

    public void setContextMethod(Method contexMethod) {
        super.setContextMethod(contexMethod);

        expression.setContextMethod(contexMethod);
    }
}