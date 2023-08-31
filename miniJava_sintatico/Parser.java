import java.io.*;

public class Parser {

    private static final int BASE_TOKEN_NUM = 300;

    public static int CLASS = 300;
    public static int PUBLIC = 301;
    public static int STATIC = 302;
    public static int VOID = 303;
    public static int MAIN = 304;
    public static int RETURN = 305;
    public static int STRING = 306;
    public static int EXTENDS = 307;
    public static int NEW = 308;
    public static int INT = 309;
    public static int BOOLEAN = 310;
    public static int IF = 311;
    public static int ELSE = 312;
    public static int WHILE = 313;
    public static int THIS = 314;
    public static int PRINT = 315;
    public static int LENGTH = 316;
    public static int TRUE = 317;
    public static int FALSE = 318;

    public static int IDENTIFIER = 319;
    public static int INTEGER_LITERAL = 320;
    public static int EOF = 321;

    public static int EQUALS = 322;
    public static int AND = 323;

    // TODO: implementar nas próximas etapas do trabalho
    public static int STATEMENT = 324;
    public static int EXPRESSION = 325;

    public static final String tokenList[] = {
            "CLASS", "PUBLIC", "STATIC", "VOID", "MAIN", "RETURN", "STRING",
            "EXTENDS", "NEW", "INT", "BOOLEAN", "IF", "ELSE", "WHILE", "THIS",
            "PRINT", "LENGTH", "TRUE", "FALSE", "IDENTIFIER", "INTEGER_LITERAL",
            "EOF", "EQUALS", "AND"
    };

    /* referencia ao objeto Scanner gerado pelo JFLEX */
    private Yylex lexer;

    public ParserVal yylval;

    private static int laToken;
    private boolean debug;

    /* construtor da classe */
    public Parser(Reader r) {
        lexer = new Yylex(r, this);
    }

    /*
     * 
     * Goal ::= MainClass ( ClassDeclaration )* <EOF>
     * MainClass ::= "class" Identifier "{" "public" "static" "void" "main" "("
     * "String" "[" "]" Identifier ")" "{" Statement "}" "}"
     * ClassDeclaration ::= "class" Identifier ( "extends" Identifier )? "{" (
     * VarDeclaration )* ( MethodDeclaration )* "}"
     * VarDeclaration ::= Type Identifier ";"
     * MethodDeclaration ::= "public" Type Identifier "(" ( Type Identifier ( ","
     * Type Identifier )* )? ")" "{" ( VarDeclaration )* ( Statement )* "return"
     * Expression ";" "}"
     * Type ::= "int" "[" "]"
     * | "boolean"
     * | "int"
     * | Identifier
     */

    private void Goal() {
        // MainClass ( ClassDeclaration )* <EOF>
        MainClass();
        while (laToken == CLASS) {
            ClassDeclaration();
        }
        Verifica(EOF);
    }

    private void MainClass() {
        // "class" Identifier "{" "public" "static" "void" "main" "(" "String" "[" "]"
        // Identifier ")" "{" Statement "}" "}"
        Verifica(CLASS);
        Verifica(IDENTIFIER);
        Verifica('{');
        Verifica(PUBLIC);
        Verifica(STATIC);
        Verifica(VOID);
        Verifica(MAIN);
        Verifica('(');
        Verifica(STRING);
        Verifica('[');
        Verifica(']');
        Verifica(IDENTIFIER);
        Verifica(')');
        Verifica('{');
        Statement();
        Verifica('}');
        Verifica('}');
    }

    private void ClassDeclaration() {
        // "class" Identifier ( "extends" Identifier )? "{" ( VarDeclaration )* (
        // MethodDeclaration )* "}"
        Verifica(CLASS);
        Verifica(IDENTIFIER);
        if (laToken == EXTENDS) {
            Verifica(EXTENDS);
            Verifica(IDENTIFIER);
        }
        Verifica('{');
        while (laToken == INT || laToken == BOOLEAN || laToken == IDENTIFIER) {
            VarDeclaration();
        }
        while (laToken == PUBLIC) {
            MethodDeclaration();
        }
        Verifica('}');
    }

    private void VarDeclaration() {
        // Type Identifier ";"
        Type();
        Verifica(IDENTIFIER);
        Verifica(';');
    }

    private void MethodDeclaration() {
        // "public" Type Identifier "(" ( Type Identifier ( "," Type Identifier )* )?
        // ")" "{" ( VarDeclaration )* ( Statement )* "return" Expression ";" "}"
        Verifica(PUBLIC);
        Type();
        Verifica(IDENTIFIER);
        Verifica('(');
        if (laToken == INT || laToken == BOOLEAN || laToken == IDENTIFIER) {
            Type();
            Verifica(IDENTIFIER);
            while (laToken == ',') {
                Verifica(',');
                Type();
                Verifica(IDENTIFIER);
            }
        }
        Verifica(')');
        Verifica('{');
        while (laToken == INT || laToken == BOOLEAN || laToken == IDENTIFIER) {
            VarDeclaration();
        }
        while (laToken == IF || laToken == WHILE || laToken == PRINT || laToken == '{' || laToken == IDENTIFIER) {
            Statement();
        }
        Verifica(RETURN);
        Expression();
        Verifica(';');
        Verifica('}');
    }

    private void Type() {
        // "int" "[" "]" | "boolean" | "int" | Identifier
        if (laToken == INT) {
            Verifica(INT);
            if (laToken == '[') {
                Verifica('[');
                Verifica(']');
            }
        } else if (laToken == BOOLEAN) {
            Verifica(BOOLEAN);
        } else {
            Verifica(IDENTIFIER);
        }
    }

    private void Statement() {
        Verifica(STATEMENT);
    }

    private void Expression() {
        Verifica(EXPRESSION);
    }

    private void Verifica(int expected) {
        if (laToken == expected)
            laToken = this.yylex();
        else {
            String expStr, laStr;

            expStr = ((expected < BASE_TOKEN_NUM)
                    ? "" + (char) expected
                    : tokenList[expected - BASE_TOKEN_NUM]);

            laStr = ((laToken < BASE_TOKEN_NUM)
                    ? Character.toString(laToken)
                    : tokenList[laToken - BASE_TOKEN_NUM]);

            yyerror("esperado token: " + expStr +
                    " na entrada: " + laStr);
        }
    }

    /* metodo de acesso ao Scanner gerado pelo JFLEX */
    private int yylex() {
        int retVal = -1;
        try {
            yylval = new ParserVal(0); // zera o valor do token
            retVal = lexer.yylex(); // le a entrada do arquivo e retorna um token
        } catch (IOException e) {
            System.err.println("IO Error:" + e);
        }
        return retVal; // retorna o token para o Parser
    }

    /* metodo de manipulacao de erros de sintaxe */
    public void yyerror(String error) {
        System.err.println("Erro: " + error);
        System.err.println("Entrada rejeitada");
        System.out.println("\n\nFalhou!!!");
        System.exit(1);

    }

    public void setDebug(boolean trace) {
        debug = trace;
    }

    /**
     * Runs the scanner on input files.
     *
     * This main method is the debugging routine for the scanner.
     * It prints debugging information about each returned token to
     * System.out until the end of file is reached, or an error occured.
     *
     * @param args the command line, contains the filenames to run
     *             the scanner on.
     */
    public static void main(String[] args) {
        Parser parser = null;
        try {
            if (args.length == 0)
                parser = new Parser(new InputStreamReader(System.in));
            else
                parser = new Parser(new java.io.FileReader(args[0]));

            parser.setDebug(false);
            laToken = parser.yylex();

            parser.Goal();

            if (laToken == Yylex.YYEOF)
                System.out.println("Sucesso!");
            else
                System.out.println("Falhou - esperado EOF.");

        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found : \"" + args[0] + "\"");
        }
        // catch (java.io.IOException e) {
        // System.out.println("IO error scanning file \""+args[0]+"\"");
        // System.out.println(e);
        // }
        // catch (Exception e) {
        // System.out.println("Unexpected exception:");
        // e.printStackTrace();
        // }

    }

}
