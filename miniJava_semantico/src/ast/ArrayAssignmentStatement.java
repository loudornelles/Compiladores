package ast;

public class ArrayAssignmentStatement extends Statement {
    public String name;
    public Expression index;
    public Expression expression;

    public ArrayAssignmentStatement(String name, Expression index, Expression expression) {
        this.name = name;
        this.index = index;
        this.expression = expression;
    }
}