package visualization;

import java.util.*;

import ast.*;

public class GraphGenerator {

    int idCounter = 0;
    Map<Object, Integer> idMap = new HashMap<Object, Integer>();

    public String output = "";
    
    public void generateForClasses(Map<String, ClassDeclaration> classes) {
        for(ClassDeclaration classDecl : classes.values()) {
            processClass(classDecl);
        }
    }

    void processClass(ClassDeclaration classDecl) {
        annotateNode(classDecl, classDecl.name);
        processVarList(classDecl, classDecl.fields.values(), "fields");
        processMethods(classDecl);
    }

    void processMethods(ClassDeclaration classDecl) {
        annotateNode(classDecl.methods.values(), "methods");
        for(Method method : classDecl.methods.values()) {
            connect(classDecl, method, null);
            processMethod(method);
        }
    }

    void processMethod(Method method) {
        annotateNode(method, method.name);
        processVarList(method, method.parameters, "parameters");
        processVarList(method, method.locals.values(), "locals");

        annotateNode(method.statements, "statements list");
        connect(method, method.statements, "statements");
        for(Statement statement : method.statements) {
            connect(method, statement, null);
            // TODO: process statement
        }

        processType(method.returnType);
        connect(method, method.returnType, "return type");
        
        processExpression(method.returnExpression);
        connect(method, method.returnExpression, "return expression");
    }

    void processType(Type type) {
        String annotation = "unknown";
        if (type == Type.intType) {
            annotation = "int";
        } else if (type == Type.booleanType) {
            annotation = "bool";
        } else if (type == Type.stringType) {
            annotation = "string";
        } else if (type == Type.intArrayType) {
            annotation = "int[]";
        } else if (type == Type.stringArrayType) {
            annotation = "string[]";
        } else if (type == Type.voidType) {
            annotation = "void";
        } else if (type instanceof IdentifierType) {
            annotation = ((IdentifierType)type).name;
        }

        annotateNode(type, annotation);
    }

    void processExpression(Expression expression) {
        annotateNode(expression, "expr");
    }

    int getObjectId(Object obj) {
        Integer id = idMap.get(obj);
        if (id == null) {
            idMap.put(obj, ++idCounter);
            return idCounter;
        } else {
            return id;
        }
    }

    void connect(Object parent, Object child, String relationName) {
        output += getObjectId(parent) + " -> " + getObjectId(child);

        if (relationName != null) {
            output += "[label=\"" + relationName + "\"]";
        }

        output += ";\n";
    }

    void annotateNode(Object obj, String annotation) {
        output += getObjectId(obj) + "[label=\"" + annotation + "\"];\n";
    }

    void processVarList(Object parent, Collection<Var> vars, String relationName) {
        annotateNode(vars, relationName + " list");
        connect(parent, vars, relationName);
        for(Var var : vars) {
            connect(vars, var, null);
        }
    }
}
