package ast;

public class AssignmentStatement extends Statement {
    public String name;
    public Expression expression;

    public AssignmentStatement(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }
}