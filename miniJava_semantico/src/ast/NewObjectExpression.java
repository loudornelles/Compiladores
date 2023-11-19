package ast;

public class NewObjectExpression extends Expression {
    public String name;

    public NewObjectExpression(String name) {
        this.name = name;
    }

    public Type resolveType() {
        ClassDeclaration classType = this.contextMethod.contextClass.globalContext.get(name);
        if (classType == null) {
            throw new Error("Class '" + this.name + "' does not exist.");
        }

        return classType;
    }
}