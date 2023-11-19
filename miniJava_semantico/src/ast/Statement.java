package ast;

public abstract class Statement {
    Method contextMethod;

    abstract void validate();
}