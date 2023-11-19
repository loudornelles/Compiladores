package ast;

public class WhileStatement extends Statement {
    public Expression condition;
    public Statement body;

    public WhileStatement(Expression condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }

    void validate() {
        if (condition.resolveType() != Type.booleanType) {
            throw new Error("While statements only allow boolean conditions.");
        }
    }
}