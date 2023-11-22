package ast;

public class NewArrayExpression extends Expression {
    public Type type;
    public Expression size;

    public NewArrayExpression(Type type, Expression size) {
        this.type = type;
        this.size = size;
    }

    public Type resolveType() {
        if (type == Type.stringType) {
            return Type.stringArrayType;
        } else if (type == Type.intType) {
            return Type.intArrayType;
        } else {
            throw new Error("Only int[] and string[] arrays are supported. string[] is only supported in main method though.");
        }
    }

    public void setContextMethod(Method contexMethod) {
        super.setContextMethod(contexMethod);

        size.setContextMethod(contexMethod);
    }
}