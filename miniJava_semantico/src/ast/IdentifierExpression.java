package ast;

public class IdentifierExpression extends Expression {
    public String name;

    public IdentifierExpression(String name) {
        System.out.println("Identifier expr name: " + name);
        this.name = name;
    }

    public Type resolveType() {
        return contextMethod.getVarTypeByIdentifer(name);
    }
}