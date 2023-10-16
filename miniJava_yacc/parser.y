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

goal:   mainclass { System.out.println("goal"); }
        | mainclass classdecllist { System.out.println("goal"); }
        ;


mainclass:  CLASS IDENTIFIER '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENTIFIER ')' '{' statement '}' '}' { System.out.println("mainclass"); }
            ;

classdecllist:  classdecllist classdecl { System.out.println("classdecllist"); }
                | classdecl { System.out.println("classdecllist"); }
                ;

classdecl:  CLASS IDENTIFIER classextends classbody { System.out.println("classdecl"); }
            | CLASS IDENTIFIER classbody    { System.out.println("classdecl"); }
            ;

classbody:  '{' '}' { System.out.println("classbody"); }
            | '{' classbodyinner '}' { System.out.println("classbody"); }
            ;

classbodyinner: vardecllist { System.out.println("classbodyinner"); }
                | methoddecllist { System.out.println("classbodyinner"); }
                | vardecllist methoddecllist { System.out.println("classbodyinner"); }
                ;

classextends:   EXTENDS IDENTIFIER { System.out.println("classextends"); }
                ;

vardecllist:    vardecllist vardecl { System.out.println("vardecllist");}
                | vardecl { System.out.println("vardecllist");}
                ;

vardecl:    type IDENTIFIER ';' { System.out.println("vardecl"); }
            ;

methoddecllist: methoddecllist methoddecl { System.out.println("methoddecllist"); }
                | methoddecl { System.out.println("methoddecllist"); }
                ;

methoddecl: PUBLIC type IDENTIFIER '(' argslist ')' methoddeclbody { System.out.println("methoddecl"); }
            | PUBLIC type IDENTIFIER '(' ')' methoddeclbody { System.out.println("methoddecl"); }
            ;

argslist:   argslist ',' arg
            | arg
            ;

arg:    type IDENTIFIER
        ;

methoddeclbody: '{' methodreturn '}' { System.out.println("methoddeclbody"); }
                | '{' methoddeclbodyinner methodreturn '}' { System.out.println("methoddeclbody"); }
                ;

methoddeclbodyinner:    vardecllist { System.out.println("methoddeclbodyinner"); }
                        | statementlist { System.out.println("methoddeclbodyinner"); }
                        | vardecllist statementlist { System.out.println("methoddeclbodyinner"); }
                        ;


methodreturn:   RETURN Expression ';' { System.out.println("methodreturn"); }
                ;

type:   INT '[' ']' { System.out.println("type"); }
        | BOOLEAN { System.out.println("type"); }
        | INT { System.out.println("type"); }
        | IDENTIFIER { System.out.println("type"); }
        ;

statement:  '{' '}' { System.out.println("statement"); }
            | '{' statementlist '}' { System.out.println("statement"); }
            | IF '(' Expression ')' statement ELSE statement { System.out.println("statement"); }
            | WHILE '(' Expression ')' statement { System.out.println("statement"); }
            | PRINT '(' Expression ')' ';' { System.out.println("statement"); }
            | IDENTIFIER '=' Expression ';' { System.out.println("statement"); }
            | IDENTIFIER '[' Expression ']' '=' Expression ';' { System.out.println("statement"); }
            ;

statementlist:  statementlist statement { System.out.println("statementlist"); }
                | statement { System.out.println("statementlist"); }
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
    System.err.println("Erro : " + error + " na linha " + lexer.getLine());
    System.err.println("Entrada rejeitada");
}
