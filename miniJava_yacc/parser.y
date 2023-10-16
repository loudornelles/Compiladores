%{
  import java.io.*;
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


%left '-' '+'
%left '/' '*'

%%

goal:   mainclass
        | mainclass classdecllist
        ;


mainclass:  CLASS IDENTIFIER '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENTIFIER ')' '{' statement '}' '}'
            ;

classdecllist:  classdecllist classdecl
                | classdecl
                ;

classdecl:  CLASS IDENTIFIER classextends classbody
            | CLASS IDENTIFIER classbody
            ;

classbody:  '{' '}'
            | '{' classbodyinner '}'
            ;

classbodyinner: vardecllist
                | methoddecllist
                | vardecllist methoddecllist
                ;

classextends:   EXTENDS IDENTIFIER
                ;

vardecllist:    vardecllist vardecl
                | vardecl
                ;

vardecl:    type IDENTIFIER ';'
            ;

methoddecllist: methoddecllist methoddecl
                | methoddecl
                ;

methoddecl: PUBLIC type IDENTIFIER '(' ')' methoddeclbody
            ;

methoddeclbody: '{' methodreturn '}'
                | '{' methoddeclbodyinner methodreturn '}'
                ;

methoddeclbodyinner:    vardecllist
                        | statementlist
                        | vardecllist statementlist
                        ;

methodreturn:   RETURN Expression ';'
                ;

type:   INT '[' ']'
        | BOOLEAN
        | INT
        | IDENTIFIER
        ;

statement:  '{' '}'
            | '{' statementlist '}'
            | IF '(' Expression ')' statement ELSE statement
            | WHILE '(' Expression ')' statement
            | PRINT '(' Expression ')'
            | IDENTIFIER '=' Expression ';'
            | IDENTIFIER '[' Expression ']' '=' Expression ';'
            ;

statementlist:  statementlist statement
                | statement
                ;

// Expression: Expression Operator Expression
            // | Expression '[' Expression ']'
            // | Expression '.' LENGTH
            // | Expression '.' IDENTIFIER '(' ')'
            // | Expression '.' IDENTIFIER '(' ExpressionList ')'
            // | INTEGER
            // | TRUE
            // | FALSE
            // | IDENTIFIER
            // | THIS
            // | NEW INT '[' Expression ']'
            // | NEW IDENTIFIER '(' ')'
            // | '!' Expression
            // | '(' Expression ')'
            // ;

// ------------------------------------

// Expression: 
//             Expression AND Expression ''
//             | Expression '<' Expression ''
//             | Expression '+' Expression ''
//             | Expression '-' Expression ''
//             | Expression '*' Expression ''
//             | Expression '[' Expression ']'
//             | Expression '.' LENGTH
//             | Expression '.' IDENTIFIER '(' ')'
//             | Expression '.' IDENTIFIER '(' ExpressionList ')'
            
//             INTEGER
//             | TRUE
//             | FALSE
//             | IDENTIFIER
//             | THIS
//             | NEW INT '[' Expression ']'
//             | NEW IDENTIFIER '(' ')'
//             | '!' Expression ''
//             | '(' Expression ')'
//             ;

// ExpressionList: Expression
//                 | ExpressionList ',' Expression
//                 ;

// ------------------------------------




Expression: Expression AND PrimaryExpression
            | Expression '<' PrimaryExpression
            | Expression '+' PrimaryExpression
            | Expression '-' PrimaryExpression
            | Expression '*' PrimaryExpression
            | Expression '[' Expression ']'
            | Expression '.' LENGTH
            | Expression '.' IDENTIFIER '(' ')'
            | Expression '.' IDENTIFIER '(' ExpressionList ')'
            | PrimaryExpression
            ;

PrimaryExpression:  INTEGER
                    | TRUE
                    | FALSE
                    | IDENTIFIER
                    | THIS
                    | NEW INT '[' Expression ']'
                    | NEW IDENTIFIER '(' ')'
                    | '!' PrimaryExpression
                    | '(' Expression ')'
                    ;

ExpressionList: Expression
                | ExpressionList ',' Expression
                ;


%%

/* método de chamada do Parser via linha de comando */
public static void main (String [] args) throws IOException {
    Parser yyparser = new Parser(new FileReader(args[0]));
    yyparser.yyparse(); // dispara o processo de análise sintática e léxica
}

/* construtor da classe Parser gerada pleo BYACC */
public Parser (Reader r) {
    lexer = new Yylex (r, this);
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
    System.err.println("Erro : " + error + " na linha " + lexer.getLine());
    System.err.println("Entrada rejeitada");
}
