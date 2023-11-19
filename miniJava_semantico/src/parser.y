%{
  import java.io.*;
  import java.util.Map;
  import java.util.HashMap;
  import java.util.List;
  import java.util.ArrayList;
  import ast.*;
%}

%token CLASS
%token PUBLIC
%token STATIC
%token VOID
%token MAIN
%token RETURN
%token STRING
%token EXTENDS
%token NEW
%token INT
%token BOOLEAN
%token IF
%token ELSE
%token WHILE
%token THIS
%token PRINT
%token LENGTH
%token TRUE
%token FALSE
%token IDENTIFIER
%token INTEGER
%token EQUALS
%token AND

%left '[' '{'
%right '='
%left '.'
%left '-' '+'
%left '*'
%left AND '<'
%right '!'

%%

Goal:   MainClass ClassDeclarationarationListOptional {
            classes = (Map<String, ClassDeclaration>)$2.obj;
            ClassDeclaration mainClass = (ClassDeclaration)$1.obj;
            classes.put(mainClass.name, mainClass);
        }
        ;

MainClass:  CLASS IDENTIFIER '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENTIFIER ')' '{' Statement '}' '}' {
                Map<String, Var> parameters = new HashMap<String, Var>();
                Var var = new Var(Type.stringArrayType, $12.sval);
                parameters.put(var.name, var);
                
                List<Statement> statements = new ArrayList<Statement>();
                statements.add((Statement)$15.obj);
            
                Method main = new Method(
                    "main",
                    Type.voidType,
                    parameters,
                    new HashMap<String, Var>(),
                    statements,
                    null
                );

                Map<String, Method> methods = new HashMap<String, Method>();
                methods.put(main.name, main);

                Map<String, Var> fields = new HashMap<String, Var>();
                
                $$.obj = new ClassDeclaration(
                    $2.sval,
                    fields,
                    methods
                );
            }


ClassDeclarationarationList:    ClassDeclaration {
                                    HashMap<String, ClassDeclaration> classDeclList = new HashMap<String, ClassDeclaration>();
                                    
                                    ClassDeclaration classDecl = (ClassDeclaration)$1.obj;
                                    classDeclList.put(classDecl.name, classDecl);
                                    
                                    $$.obj = classDeclList;
                                }
                                | ClassDeclarationarationList ClassDeclaration {
                                    HashMap<String, ClassDeclaration> classDeclList = (HashMap<String, ClassDeclaration>)$1.obj;

                                    ClassDeclaration classDecl = (ClassDeclaration)$2.obj;
                                    classDeclList.put(classDecl.name, classDecl);

                                    $$.obj = classDeclList;
                                }
                                ; 

ClassDeclarationarationListOptional:    { $$.obj = new HashMap<String, ClassDeclaration>(); } // empty
                                        | ClassDeclarationarationList { $$ = $1; }
                                        ;

ClassDeclaration:   CLASS IDENTIFIER ClassExtendsOptional '{' VarDeclarationList MethodDeclarationList '}'  {
                        $$.obj = new ClassDeclaration($2.sval, (Map<String, Var>)$5, (Map<String, Method>)$6);
                    }
                    ;

ClassExtendsOptional:   {} // empty
                        | EXTENDS IDENTIFIER { $$ = $2; }
                        ;

VarDeclarationList: { $$.obj = new HashMap<String, Var>(); } // empty
                    | VarDeclarationList VarDeclaration {
                        HashMap<String, Var> varDeclList = (HashMap<String, Var>)$1.obj;
                        Var varDecl = (Var)$2.obj;

                        varDeclList.put(varDecl.name, varDecl);

                        $$.obj = varDeclList;
                    }
                    ;

VarDeclaration: Type IDENTIFIER ';' {
                    $$.obj = new Var((Type)$1.obj, $2.sval);
                }
                ;

Type:   INT '[' ']' { $$.obj = Type.intArrayType; }
        | BOOLEAN { $$.obj = Type.booleanType; }
        | INT { $$.obj = Type.intType; }
        | IDENTIFIER { $$.obj = new IdentifierType($1.sval); }
        ;

MethodDeclarationList:  { $$.obj = new HashMap<String, Method>(); } // empty
                        | MethodDeclarationList MethodDeclaration {
                            Map<String, Method> methodDeclList = (Map<String, Method>)$1;
                            Method method = (Method)$2.obj;

                            methodDeclList.put(method.name, method);

                            $$.obj = methodDeclList;
                        }
                        ;

MethodDeclaration:  PUBLIC Type IDENTIFIER '(' ArgsListOptional ')' '{' VarDeclarationList StatementListOptional RETURN Expression ';' '}' {
                        $$.obj = new Method(
                            $3.sval,
                            (Type)$2.obj,
                            (Map<String, Var>)$5.obj,
                            (Map<String, Var>)$8.obj,
                            (List<Statement>)$9.obj,
                            (Expression)$11.obj
                        );
                    }

ArgsListOptional:   { $$.obj = new HashMap<String, Var>(); } // empty
                    | ArgsList { $$ = $1; }
                    ;

ArgsList:   ArgsList ',' Arg {
                Map<String, Var> argsList = (Map<String, Var>)$1;
                Var var = (Var)$3.obj;

                argsList.put(var.name, var);

                $$.obj = argsList;
            }
            | Arg {
                Map<String, Var> argsList = new HashMap<String, Var>();
                Var var = (Var)$1.obj;

                argsList.put(var.name, var);

                $$.obj = argsList;
            }
            ;

Arg: Type IDENTIFIER { $$.obj = new Var((Type)$1.obj, $2.sval); }

Statement:  '{' StatementListOptional '}' { $$.obj = new BlockStatement((List<Statement>)$2.obj); }
            | IF '(' Expression ')' Statement ELSE Statement { $$.obj = new IfStatement((Expression)$3.obj, (Statement)$5.obj, (Statement)$7.obj); }
            | WHILE '(' Expression ')' Statement { $$.obj = new WhileStatement((Expression)$3.obj, (Statement)$5.obj); }
            | PRINT '(' Expression ')' ';' { $$.obj = new PrintStatement((Expression)$3.obj); }
            | IDENTIFIER '=' Expression ';' { $$.obj = new AssignmentStatement($1.sval, (Expression)$3.obj); }
            | IDENTIFIER '[' Expression ']' '=' Expression ';' { $$.obj = new ArrayAssignmentStatement($1.sval, (Expression)$3.obj, (Expression)$6.obj); }
            ;

StatementList:  StatementList Statement {
                    List<Statement> statementList = (List<Statement>)$1;
                    statementList.add((Statement)$2.obj);
                    $$.obj = statementList;
                }
                | Statement {
                    List<Statement> statementList = new ArrayList<Statement>();
                    statementList.add((Statement)$1.obj);
                    $$.obj = statementList;
                }
                ;

StatementListOptional:  { $$.obj = new ArrayList<Statement>(); } // empty
                        | StatementList { $$ = $1; }
                        ;

Expression: Expression AND Expression { $$.obj = new BinaryExpression((Expression)$1.obj, $2.sval, (Expression)$3.obj); }
            | Expression '<' Expression { $$.obj = new BinaryExpression((Expression)$1.obj, $2.sval, (Expression)$3.obj); }
            | Expression '+' Expression { $$.obj = new BinaryExpression((Expression)$1.obj, $2.sval, (Expression)$3.obj); }
            | Expression '-' Expression { $$.obj = new BinaryExpression((Expression)$1.obj, $2.sval, (Expression)$3.obj); }
            | Expression '*' Expression { $$.obj = new BinaryExpression((Expression)$1.obj, $2.sval, (Expression)$3.obj); }
            | Expression '[' Expression ']' { $$.obj = new ArrayAccessExpression((Expression)$1.obj, (Expression)$3.obj); }
            | Expression '.' LENGTH { $$.obj = new LengthExpression((Expression)$1.obj); }
            | Expression '.' IDENTIFIER '(' ExpressionList ')' { $$.obj = new MethodCallExpression((Expression)$1.obj, $3.sval, (List<Expression>)$5.obj); }
            | INTEGER { $$.obj = new IntegerLiteralExpression((int)$1.ival); }
            | TRUE { $$.obj = new BooleanLiteralExpression(true); }
            | FALSE { $$.obj = new BooleanLiteralExpression(false); }
            | IDENTIFIER { $$.obj = new IdentifierExpression($1.sval); }
            | THIS { $$.obj = new IdentifierExpression($1.sval); }
            | NEW INT '[' Expression ']' { $$.obj = new NewArrayExpression(Type.intType, (Expression)$4.obj); }
            | NEW IDENTIFIER '('')' { $$.obj = new NewObjectExpression($2.sval); }
            | '!' Expression { $$.obj = new UnaryExpression($1.sval, (Expression)$2.obj); }
            | '(' Expression ')' { $$ = $2; }
            ;

ExpressionList: { $$.obj = new ArrayList<Expression>(); } // empty
                | Expression {
                    List<Expression> expressionList = new ArrayList<Expression>();
                    expressionList.add((Expression)$1.obj);
                    $$.obj = expressionList;
                }
                | ExpressionList ',' Expression {
                    List<Expression> expressions = (List<Expression>)$1.obj;
                    expressions.add((Expression)$1.obj);
                    $$ = $1;
                }
                ;

%%

Map<String, ClassDeclaration> classes = new HashMap<String, ClassDeclaration>();

/* método de chamada do Parser via linha de comando */
public static void main (String [] args) throws IOException {
    Parser yyparser = new Parser(new FileReader(args[0]));
    yyparser.yyparse(); // dispara o processo de análise sintática e léxica
}

/* construtor da classe Parser gerada pleo BYACC */
public Parser (Reader r) {
    lexer = new Yylex (r, this);
    // yydebug = true;
}

/* referência ao objeto Yylex gerado pelo JFLEX */
private Yylex lexer;

/* método de acesso ao Yylex gerado pelo JFLEX */
private int yylex() {
    int retVal = -1;
    try {
        yylval = new ParserVal(0); //zera o valor do token
        retVal = lexer.yylex(); //lê a entrada do arquivo e retorna um token
    } catch (IOException e) {
        System.err.println("IO Error:" + e);
    }
    return retVal; //retorna o token para o Parser
}

/* método de manipulação de erros de sintaxe */
public void yyerror (String error) {
    System.err.println("Erro: " + error + " na linha " + lexer.getLine());
    System.err.println("Entrada rejeitada");
}
