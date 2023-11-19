package ast;

public class AssignmentStatement extends Statement {
    public String name;
    public Expression expression;

    public AssignmentStatement(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }

    void validate() {
        Type lhsType = contextMethod.getVarByIdentifer(name).type;
        Type rhsType = expression.resolveType();

        if (!Type.matches(lhsType, rhsType)) {
            throw new Error("Incompatible types at assignment.");
        }
    }
}