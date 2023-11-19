package ast;

public abstract class Expression {
    public Method contextMethod;

    public abstract Type resolveType();
}