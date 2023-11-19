package ast;

import java.util.Map;
import java.util.HashMap;

public class ClassDeclaration {
    public String name;
    public Map<String, Method> methods = new HashMap<String, Method>();
    public Map<String, Var> fields = new HashMap<String, Var>();

    public ClassDeclaration(String name, Map<String, Var> fields, Map<String, Method> methods) {
        this.name = name;
        this.fields = fields;
        this.methods = methods;
    }
}