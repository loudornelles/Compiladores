package ast;
import java.util.List;
import java.util.Map;

public class MethodCallExpression extends Expression {
    public Expression expression;
    public String name;
    public List<Expression> arguments;

    public MethodCallExpression(Expression expression, String name, List<Expression> arguments) {
        this.expression = expression;
        this.name = name;
        this.arguments = arguments;

        System.out.println("Method call expr args: " + arguments);
    }

    public Type resolveType() {
        Type expressionReturn = expression.resolveType();

        if (expressionReturn instanceof ClassDeclaration) {
            ClassDeclaration classDecl = (ClassDeclaration)expressionReturn;
            Method method = classDecl.methods.get(name);

            if (method == null) {
                throw new Error("Arguments mismatch in call to " + method.name);
            }
            
            if (method.parameters.size() != arguments.size()) {
                throw new Error("Arguments mismatch in call to " + method.name);
            }
            
            for (int i = 0; i < arguments.size(); i++) {
                Type argumentType = arguments.get(i).resolveType();
                Type parameterType = method.parameters.get(i).type.resolveType();
                
                if (!Type.matches(argumentType, parameterType)) {
                    throw new Error("Arguments mismatch in call to " + method.name);
                }
            }

            return method.returnType.resolveType();
        } else {
            throw new Error("Method " + name + " called on non-class type " + expressionReturn);
        } 
    }

    public void setContextMethod(Method contexMethod) {
        super.setContextMethod(contexMethod);

        expression.setContextMethod(contexMethod);
        for(Expression arg : arguments) {
            System.out.println("Arg expr: " + arg);
            arg.setContextMethod(contexMethod);
        }
    }
}