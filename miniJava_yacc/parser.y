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
%token STATEMENT
%token EXPRESSION
%token IDENTIFIER
%token INTEGER
%token EQUALS
%token AND


%left '-' '+'
%left '/' '*'

%%

goal:
        | CLASS mainclass
        ;

mainclass:
            | IDENTIFIER
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
