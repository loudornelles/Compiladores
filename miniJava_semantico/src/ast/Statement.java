package ast;

public abstract class Statement {
    Method contextMethod;

    abstract void validate();

    public void setContextMethod(Method contexMethod) {
        this.contextMethod = contexMethod;
    }
}