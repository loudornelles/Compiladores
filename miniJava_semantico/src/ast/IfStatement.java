package ast;

public class IfStatement extends Statement {
    public Expression condition;
    public Statement thenStatement;
    public Statement elseStatement;

    public IfStatement(Expression condition, Statement thenStatement, Statement elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    void validate() {
        if (condition.resolveType() != Type.booleanType) {
            throw new Error("If statements only allow boolean conditions.");
        }
    }
}