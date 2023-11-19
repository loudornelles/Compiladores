import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

class Type {
    public static Type intType = new Type();
    public static Type booleanType = new Type();
    public static Type stringType = new Type();
    public static Type stringArrayType = new ArrayType(stringType);
    public static Type intArrayType = new ArrayType(intType);
    public static Type voidType = new Type();
}

class IdentifierType extends Type {
    public String name;

    public IdentifierType(String name) {
        this.name = name;
    }
}

class ArrayType extends Type {
    public Type type;

    public ArrayType(Type type) {
        this.type = type;
    }
}

class Class {
    public String name;
    public Map<String, Method> methods = new HashMap<String, Method>();
    public Map<String, Var> fields = new HashMap<String, Var>();

    public Class(String name, Map<String, Var> fields, Map<String, Method> methods) {
        this.name = name;
        this.fields = fields;
        this.methods = methods;
    }
}

class Var {
    public Type type;
    public String name;

    public Var(Type type, String name) {
        this.type = type;
        this.name = name;
    }
}

class Method {
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

class Statement {
}

class BlockStatement extends Statement {
    public List<Statement> statements = new ArrayList<Statement>();

    public BlockStatement(List<Statement> statements) {
        this.statements = statements;
    }
}

class IfStatement extends Statement {
    public Expression condition;
    public Statement thenStatement;
    public Statement elseStatement;

    public IfStatement(Expression condition, Statement thenStatement, Statement elseStatement) {
        this.condition = condition;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }
}

class WhileStatement extends Statement {
    public Expression condition;
    public Statement body;

    public WhileStatement(Expression condition, Statement body) {
        this.condition = condition;
        this.body = body;
    }
}

class PrintStatement extends Statement {
    public Expression expression;

    public PrintStatement(Expression expression) {
        this.expression = expression;
    }
}

class AssignmentStatement extends Statement {
    public String name;
    public Expression expression;

    public AssignmentStatement(String name, Expression expression) {
        this.name = name;
        this.expression = expression;
    }
}

class ArrayAssignmentStatement extends Statement {
    public String name;
    public Expression index;
    public Expression expression;

    public ArrayAssignmentStatement(String name, Expression index, Expression expression) {
        this.name = name;
        this.index = index;
        this.expression = expression;
    }
}

class Expression {

}

class UnaryExpression extends Expression {
    public String operator;
    public Expression expression;

    public UnaryExpression(String operator, Expression expression) {
        this.operator = operator;
        this.expression = expression;
    }
}

class BinaryExpression extends Expression {
    public Expression left;
    public Expression right;
    public String operator;

    public BinaryExpression(Expression left, String operator, Expression right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
}

class ArrayAccessExpression extends Expression {
    public String name;
    public Expression index;

    public ArrayAccessExpression(String name, Expression index) {
        this.name = name;
        this.index = index;
    }
}

class ArrayLengthExpression extends Expression {
    public String name;

    public ArrayLengthExpression(String name) {
        this.name = name;
    }
}

class MethodCallExpression extends Expression {
    public Expression expression;
    public String name;
    public List<Expression> arguments = new ArrayList<Expression>();

    public MethodCallExpression(Expression expression, String name) {
        this.expression = expression;
        this.name = name;
    }
}

class IntegerLiteralExpression extends Expression {
    public int value;

    public IntegerLiteralExpression(int value) {
        this.value = value;
    }
}

class BooleanLiteralExpression extends Expression {
    public boolean value;

    public BooleanLiteralExpression(boolean value) {
        this.value = value;
    }
}

class IdentifierExpression extends Expression {
    public String name;

    public IdentifierExpression(String name) {
        this.name = name;
    }
}

class NewArrayExpression extends Expression {
    public Type type;
    public Expression size;

    public NewArrayExpression(Type type, Expression size) {
        this.type = type;
        this.size = size;
    }
}

class NewObjectExpression extends Expression {
    public String name;

    public NewObjectExpression(String name) {
        this.name = name;
    }
}
