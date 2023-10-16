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

%left '[' '{'
%right '='
%left '.'
%left '-' '+'
%left '*'
%left AND '<'
%right '!'

%%

// Goal:   MainClass ClassDeclarationarationListOptional
//         ;

// MainClass:  CLASS IDENTIFIER '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENTIFIER ')' '{' Statement '}' '}'


// ClassDeclarationarationList:    ClassDeclaration
//                                 | ClassDeclarationarationList ClassDeclaration
//                                 ;

// ClassDeclarationarationListOptional:    // empty
//                                         | ClassDeclarationarationList
//                                         ;

// ClassDeclaration:   CLASS IDENTIFIER ClassExtendsOptional '{' VarDeclarationList MethodDeclarationList '}'
//                     ;

// ClassExtendsOptional:   // empty
//                         | EXTENDS IDENTIFIER
//                         ;

// VarDeclarationList: // empty
//                     | VarDeclarationList VarDeclaration
//                     ;

// VarDeclaration: Type IDENTIFIER ';'

// Type:   INT '[' ']'
//         | BOOLEAN
//         | INT
//         | IDENTIFIER
//         ;

// MethodDeclarationList:  // empty
//                         | MethodDeclarationList MethodDeclaration
//                         ;

// MethodDeclaration:  PUBLIC Type IDENTIFIER '(' ArgsListOptional ')' '{' VarDeclarationList StatementListOptional RETURN Expression ';' '}'

// ArgsListOptional:   // empty
//                     | ArgsList
//                     ;

// ArgsList:   ArgsList ',' Arg 
//             | Arg
//             ;

// Arg: Type IDENTIFIER

// Statement:  '{' StatementListOptional '}'
//             | IF '(' Expression ')' Statement ELSE Statement
//             | WHILE '(' Expression ')' Statement
//             | PRINT '(' Expression ')' ';'
//             | IDENTIFIER '=' Expression ';'
//             | IDENTIFIER '[' Expression ']' '=' Expression ';'
//             ;

// StatementList:  StatementList Statement
//                 | Statement
//                 ;

// StatementListOptional:  // empty
//                         | StatementList
//                         ;

// Expression: Expression Operator Expression
//             | Expression '[' Expression ']'
//             | Expression '.' LENGTH
//             | Expression '.' IDENTIFIER '(' ExpressionList ')'
//             | INTEGER
//             | TRUE
//             | FALSE
//             | IDENTIFIER
//             | THIS
//             | NEW INT '[' Expression ']'
//             | NEW IDENTIFIER '('')'
//             | '!' Expression
//             | '(' Expression ')'
//             ;

// ExpressionList: // empty
//                 | Expression
//                 | ExpressionList ',' Expression
//                 ;

// Operator:   AND
//             | '<'
//             | '+'
//             | '-'
//             | '*'
//             ;

Goal: MainClass ClassDeclarations;

MainClass: CLASS IDENTIFIER '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENTIFIER ')' '{' Statement '}' '}';

ClassDeclarationList: ClassDeclaration
                     | ClassDeclarationList ClassDeclaration;

ClassDeclarations :
                  | ClassDeclarationList;

ClassDeclaration: CLASS IDENTIFIER ExtendOpt '{' VarDeclarations MethodDeclarations '}';

ExtendOpt :
          | EXTENDS IDENTIFIER;

VarDeclarations :
                | VarDeclarations VarDeclaration;

VarDeclaration: Type IDENTIFIER ';';

MethodDeclarations :
                   | MethodDeclarationList;

MethodDeclarationList: MethodDeclaration
                      | MethodDeclarationList MethodDeclaration;

MethodDeclaration: PUBLIC Type IDENTIFIER '(' TypeIdentifiers ')' '{' VarDeclarations Statements RETURN Expression ';' '}';

TypeIdentifiers :
                | TypeIdentifierList;

TypeIdentifierList: TypeIdentifier
                   | TypeIdentifierList ',' TypeIdentifier;

TypeIdentifier: Type IDENTIFIER;

Statements :
           | StatementList;

StatementList: Statement
              | StatementList Statement;

Type: INT
     | INT '[' ']'
     | BOOLEAN
     | IDENTIFIER;

Statement: '{' Statements '}'
          | IF '(' Expression ')' Statement ELSE Statement
          | WHILE '(' Expression ')' Statement
          | PRINT '(' Expression ')' ';'
          | IDENTIFIER '=' Expression ';'
          | IDENTIFIER '[' Expression ']' '=' Expression ';';

Expressions :
            | ExpressionList;

ExpressionList: Expression
               | ExpressionList ',' Expression;

Expression: Expression AND Expression
           | Expression '<' Expression
           | Expression '+' Expression
           | Expression '-' Expression
           | Expression '*' Expression
           | Expression '[' Expression ']'
           | Expression '.' LENGTH
           | Expression '.' IDENTIFIER '(' Expressions ')'
           | TRUE
           | FALSE
           | IDENTIFIER
           | THIS
           | NEW INT '[' Expression ']'
           | NEW IDENTIFIER '(' ')'
           | '!' Expression
           | '(' Expression ')'
           | INTEGER;

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
    System.err.println("Erro: " + error + " na linha " + lexer.getLine());
    System.err.println("Entrada rejeitada");
}
