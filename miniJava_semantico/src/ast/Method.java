package ast;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Method {
    public String name;
    public Type returnType;
    public Map<String, Var> parameters;
    public Map<String, Var> locals;
    public List<Statement> statements;
    public Expression returnExpression;

    public Method(
            String name,
            Type returnType,
            Map<String, Var> parameters,
            Map<String, Var> locals,
            List<Statement> statements,
            Expression returnExpression) {
        this.returnType = returnType;
        this.parameters = parameters;
        this.locals = locals;
        this.statements = statements;
        this.returnExpression = returnExpression;
    }
}