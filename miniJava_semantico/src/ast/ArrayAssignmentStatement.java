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

    public void validate() {
        Type containerType = contextMethod.getVarByIdentifer(name).type;
        if (!(containerType instanceof ArrayType)) {
            throw new Error("Tried using subscript operator on non-array type.");
        }

        Type indexType = index.resolveType();
        if (indexType != Type.intType) {
            throw new Error("Subscript operator only accepts integers as arguments.");
        }

        Type rhsType = expression.resolveType();
        if (rhsType != ((ArrayType)containerType).type) {
            throw new Error("Incompatible types in array assignment.");
        }
    }
}