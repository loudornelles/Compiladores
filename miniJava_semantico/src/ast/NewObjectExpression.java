package ast;

public class NewObjectExpression extends Expression {
    public String name;

    public NewObjectExpression(String name) {
        this.name = name;
    }
}