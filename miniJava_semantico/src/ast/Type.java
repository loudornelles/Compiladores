package ast;

public class Type {
    public static Type intType = new Type();
    public static Type booleanType = new Type();
    public static Type stringType = new Type();
    public static Type stringArrayType = new ArrayType(stringType);
    public static Type intArrayType = new ArrayType(intType);
    public static Type voidType = new Type();
}