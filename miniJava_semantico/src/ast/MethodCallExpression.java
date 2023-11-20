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
    }

    public Type resolveType() {
        Type expressionReturn = expression.resolveType();

        if (expressionReturn instanceof ClassDeclaration) {
            ClassDeclaration classDecl = (ClassDeclaration)expressionReturn;
            Method className = classDecl.methods.get(name);

            if (className == null) {
                System.out.println("Method " + name + " not found in class " + classDecl.name);
                throw new Error();
            }
            
            if (className.parameters.size() != arguments.size()) {
                System.out.println("Method " + name + " in class " + classDecl.name + " has " + className.parameters.size() + " parameters, but " + arguments.size() + " arguments were given");
                throw new Error();
            }
            
            for (int i = 0; i < arguments.size(); i++) {
                Type argumentType = arguments.get(i).resolveType();
                Type parameterType = className.parameters.get(i).type.resolveType();
                
                if (!Type.matches(argumentType, parameterType)) {
                    System.out.println("Argument " + i + " of method " + name + " in class " + classDecl.name + " has type " + parameterType + ", but " + argumentType + " was given");
                    throw new Error();
                }
            }
            return expression.resolveType();
        } else {
            System.out.println("Method " + name + " called on non-class type " + expressionReturn);
            throw new Error();
        } 
    }

    public void setContextMethod(Method contexMethod) {
        super.setContextMethod(contexMethod);

        expression.setContextMethod(contexMethod);
        for(Expression arg : arguments) {
            arg.setContextMethod(contexMethod);
        }
    }
}