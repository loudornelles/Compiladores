%{
  import java.io.*;
  import java.util.Map;
  import java.util.HashMap;
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
            classes = $2;
            classes.put($1.name, $1);
        }
        ;

MainClass:  CLASS IDENTIFIER '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENTIFIER ')' '{' Statement '}' '}' {
                Map<String, Var> parameters = new HashMap<String, Var>();
                parameters.put($12, Type.stringArray);
                
                List<Statement> statements = new ArrayList<Statement>();
                statements.put($15);
            
                Method main = new Method(
                    "main",
                    Type.voidType,
                    parameters,
                    new HashMap<String, Var>(),
                    statements,
                    null
                );

                Map<String, Method> methods = new HashMap<String, Method>();
                methods.put(main);

                Map<String, Var> fields = new HashMap<String, Var>();
                
                
                $$ = new Class(
                    $2,
                    fields,
                    methods
                );     
            }


ClassDeclarationarationList:    ClassDeclaration {
                                    $$ = new HashMap<String, Class>();
                                    $$.put($1.name, $1);
                                }
                                | ClassDeclarationarationList ClassDeclaration {
                                    $1.put($2.name, $2);
                                    $$ = $1;
                                }
                                ; 

ClassDeclarationarationListOptional:    { $$ = new HashMap<String, Class>(); } // empty
                                        | ClassDeclarationarationList { $$ = $1; }
                                        ;

ClassDeclaration:   CLASS IDENTIFIER ClassExtendsOptional '{' VarDeclarationList MethodDeclarationList '}'  {
                        $$ = new Class($2, $5, $6);
                    }
                    ;

ClassExtendsOptional:   {} // empty
                        | EXTENDS IDENTIFIER { $$ = $2; }
                        ;

VarDeclarationList: {$$ = new HashMap<String, Var>();} // empty
                    | VarDeclarationList VarDeclaration {
                        $1.put($2.name, $2);
                        $$ = $1;
                    }
                    ;

VarDeclaration: Type IDENTIFIER ';' {
                    $$ = new Var($1, $2);
                }
                ;

Type:   INT '[' ']' { $$ = Type.intArrayType; }
        | BOOLEAN { $$ = Type.booleanType; }
        | INT { $$ = Type.intType; }
        | IDENTIFIER { $$ = new IndentifierType($1); }
        ;

MethodDeclarationList:  {$$ = new HashMap<String, MethodDeclaration>();} // empty
                        | MethodDeclarationList MethodDeclaration {
                            $1.put($2.name, $2);
                            $$ = $1;
                        }
                        ;

MethodDeclaration:  PUBLIC Type IDENTIFIER '(' ArgsListOptional ')' '{' VarDeclarationList StatementListOptional RETURN Expression ';' '}' {
                        $$ = new MethodDeclaration($3, $2, $5, $8, $9, $11);
                    }

ArgsListOptional:   { $$ = new HashMap<String, Var>; } // empty
                    | ArgsList { $$ = $1; }
                    ;

ArgsList:   ArgsList ',' Arg {
                $1.put($3.name, $3);
                $$ = $1;
            }
            | Arg {
                $$ = new HashMap<String, Var>;
                $$.put($1.name, $1)
            }
            ;

Arg: Type IDENTIFIER { $$ = new Var($1, $2); }

Statement:  '{' StatementListOptional '}' { $$ = new BlockStatement($2); }
            | IF '(' Expression ')' Statement ELSE Statement { $$ = new IfStatement($3, $5, $7); }
            | WHILE '(' Expression ')' Statement { $$ = new WhileStatement($3, $5); }
            | PRINT '(' Expression ')' ';' { $$ = new PrintStatement($3); }
            | IDENTIFIER '=' Expression ';' { $$ = new AssignStatement($1, $3); }
            | IDENTIFIER '[' Expression ']' '=' Expression ';' { $$ = new ArrayAssignStatement($1, $3, $6); }
            ;

StatementList:  StatementList Statement {
                    $1.add($2);
                    $$ = $1;
                }
                | Statement {
                    $$ = new ArrayList<Statement>();
                    $$.add($1);
                }
                ;

StatementListOptional:  { $$ = new ArrayList<Statement>(); } // empty
                        | StatementList { $$ = $1; }
                        ;

Expression: Expression AND Expression { $$ = new BinaryExpression($1, $2, $3); }
            | Expression '<' Expression { $$ = new BinaryExpression($1, $2, $3); }
            | Expression '+' Expression { $$ = new BinaryExpression($1, $2, $3); }
            | Expression '-' Expression { $$ = new BinaryExpression($1, $2, $3); }
            | Expression '*' Expression { $$ = new BinaryExpression($1, $2, $3); }
            | Expression '[' Expression ']' { $$ = new ArrayAccessExpression($1, $3); }
            | Expression '.' LENGTH { $$ = new LengthExpression($1); }
            | Expression '.' IDENTIFIER '(' ExpressionList ')' { $$ = new CallExpression($1, $3, $5); }
            | INTEGER { $$ = new IntegerLiteralExpression($1); }
            | TRUE { $$ = new BooleanLiteralExpression(true); }
            | FALSE { $$ = new BooleanLiteralExpression(false);}
            | IDENTIFIER { $$ = new IdentifierExpression($1); }
            | THIS { $$ = new IndentifierExpression($1); }
            | NEW INT '[' Expression ']' { $$ = new NewArrayExpression(Type.intType, $4); }
            | NEW IDENTIFIER '('')' { $$ = new NewObjectExpression($2); }
            | '!' Expression { $$ = new UnaryExpression($1, $2); }
            | '(' Expression ')' { $$ = $2; }
            ;

ExpressionList: { $$ = new List<Expression>(); } // empty
                | Expression {
                    $$ = new List<Expression>();
                    $$.add($1);
                }
                | ExpressionList ',' Expression {
                    $1.add($1);
                    $$ = $1;
                }
                ;

%%

Map<String, Class> classes = new HashMap<String, Class>();

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
