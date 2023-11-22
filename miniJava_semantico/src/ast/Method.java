package ast;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class Method {
    public String name;
    public Type returnType;
    public List<Var> parameters;
    public Map<String, Var> locals;
    public List<Statement> statements;
    public Expression returnExpression;
    public ClassDeclaration contextClass;

    private Map<String, Var> parametersByName = new HashMap<String, Var>();


    public Method(
            String name,
            Type returnType,
            List<Var> parameters,
            Map<String, Var> locals,
            List<Statement> statements,
            Expression returnExpression) {
        this.name = name;
        this.returnType = returnType;
        this.parameters = parameters;
        this.locals = locals;
        this.statements = statements;
        this.returnExpression = returnExpression;

        for (Var parameter : parameters) {
            parametersByName.put(parameter.name, parameter);
        }

        for(Statement statement : statements) {
            statement.setContextMethod(this);
        }

        if (returnExpression != null) {
            returnExpression.setContextMethod(this);
        }
    }

    Type getVarTypeByIdentifer(String identifier) {
        if (identifier == "this") {
            return this.contextClass;
        }

        Var var = this.locals.get(identifier);
        
        if (var == null) {
            var = this.parametersByName.get(identifier);
        }

        if (var == null) {
            var = this.contextClass.fields.get(identifier);
        }

        if (var == null) {
            throw new Error("Identifier '" + identifier + "' does not exist.");
        }

        return var.type.resolveType();
    }

    void validate() {
        for(Statement statement : statements) {
            statement.validate();
        }

        if (returnExpression != null) {
            Type returnExpresssionType = returnExpression.resolveType();
            if (!Type.matches(returnType, returnExpresssionType)) {
                throw new Error("Return expression type mismatch in method: " + contextClass.name + "." + name);
            }
        }
       

        for(Var param : parameters) {
            param.validate();
        }

        for(Var local : locals.values()) {
            local.validate();
            for (Var param : parameters) {
                if (param.name.equals(local.name)) {
                    throw new Error("Local variable '" + local.name + "' has the same name as one of the method's parameters.");
                }
            }
        }
    }
}