package ast;

public class IdentifierExpression extends Expression {
    public String name;

    public IdentifierExpression(String name) {
        this.name = name;

        this.validate();
    }

    void validate() {}
}