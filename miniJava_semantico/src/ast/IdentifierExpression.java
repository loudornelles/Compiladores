package ast;

public class IdentifierExpression extends Expression {
    public String name;

    public IdentifierExpression(String name) {
        this.name = name;
    }

    public Type resolveType() {
        return contextMethod.getVarByIdentifer(name).type;
    }
}