package ast;

import java.util.Map;

public class IdentifierType extends Type {
    public static Map<String, ClassDeclaration> contextGlobal;

    public String name;

    public IdentifierType(String name) {
        this.name = name;
    }

    Type resolveType() {
        ClassDeclaration classDeclaration = contextGlobal.get(name);
        if (classDeclaration != null) {
            return classDeclaration;
        } else {
            throw new Error("Class '" + name + "' doesn't exist.");
        }
    }
}