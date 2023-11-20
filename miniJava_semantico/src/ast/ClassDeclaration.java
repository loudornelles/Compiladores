package ast;

import java.util.Map;
import java.util.HashMap;

public class ClassDeclaration extends Type {
    public String name;
    public Map<String, Method> methods = new HashMap<String, Method>();
    public Map<String, Var> fields = new HashMap<String, Var>();

    public Map<String, ClassDeclaration> globalContext;

    public ClassDeclaration(String name, Map<String, Var> fields, Map<String, Method> methods) {
        this.name = name;
        this.fields = fields;
        this.methods = methods;

        for (Method method : methods.values()) {
            method.contextClass = this;
        }
    }

    public void validate() {
        for(Method method : methods.values()) {
            method.validate();
        }

        for(Var field : fields.values()) {
            field.validate();
        }
    }
}