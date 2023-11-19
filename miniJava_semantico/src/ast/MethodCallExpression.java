package ast;
import java.util.List;
import java.util.ArrayList;

public class MethodCallExpression extends Expression {
    public Expression expression;
    public String name;
    public List<Expression> arguments;

    public MethodCallExpression(Expression expression, String name, List<Expression> arguments) {
        this.expression = expression;
        this.name = name;
        this.arguments = arguments;
    }
}