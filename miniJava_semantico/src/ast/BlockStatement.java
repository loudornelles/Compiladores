package ast;
import java.util.List;
import java.util.ArrayList;


public class BlockStatement extends Statement {
    public List<Statement> statements = new ArrayList<Statement>();

    public BlockStatement(List<Statement> statements) {
        this.statements = statements;
    }

    void validate() {
        // no validation needed
    }

    public void setContextMethod(Method contexMethod) {
        super.setContextMethod(contexMethod);

        for(Statement statement : statements) {
            statement.setContextMethod(contexMethod);
        }
    }
}