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
        this.returnType = returnType;
        this.parameters = parameters;
        this.locals = locals;
        this.statements = statements;
        this.returnExpression = returnExpression;

        for (Var parameter : parameters) {
            parametersByName.put(parameter.name, parameter);
        }
    }

    Var getVarByIdentifer(String identifier) {
        Var var = this.locals.get(this.name);
        
        if (var == null) {
            var = this.parametersByName.get(this.name);
        }

        if (var == null) {
            var = this.contextClass.fields.get(this.name);
        }

        if (var == null) {
            throw new Error("Identifier '" + this.name + "' does not exist.");
        }

        return var;
    }
}