import java.io.*;
import java.util.HashMap;
import java.util.Map;

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

    public static int EQUALS = 321;
    public static int AND = 322;

    // TODO: to be implemented in project's next phase
    public static int STATEMENT = 323;
    public static int EXPRESSION = 324;

    public static final Map<Integer, String> tokenNames = new HashMap<>() {
        {
            put(CLASS, "CLASS");
            put(PUBLIC, "PUBLIC");
            put(STATIC, "STATIC");
            put(VOID, "VOID");
            put(MAIN, "MAIN");
            put(RETURN, "RETURN");
            put(STRING, "STRING");
            put(EXTENDS, "EXTENDS");
            put(NEW, "NEW");
            put(INT, "INT");
            put(BOOLEAN, "BOOLEAN");
            put(IF, "IF");
            put(ELSE, "ELSE");
            put(WHILE, "WHILE");
            put(THIS, "THIS");
            put(PRINT, "PRINT");
            put(LENGTH, "LENGTH");
            put(TRUE, "TRUE");
            put(FALSE, "FALSE");
            put(IDENTIFIER, "IDENTIFIER");
            put(INTEGER_LITERAL, "INTEGER_LITERAL");
            put(EQUALS, "EQUALS");
            put(AND, "AND");
            put(STATEMENT, "STATEMENT");
            put(EXPRESSION, "EXPRESSION");
        }
    };


    private Yylex lexer;
//    public ParserVal yylval;
    private static int laToken;

    private boolean debug;

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

    private void goal() {
        // MainClass ( ClassDeclaration )* <EOF>
        printDebug("Entering goal");
        mainClass();
        while (laToken == CLASS) {
            classDeclaration();
        }
    }

    private void mainClass() {
        // "class" Identifier "{" "public" "static" "void" "main" "(" "String" "[" "]"
        // Identifier ")" "{" Statement "}" "}"
        printDebug("Entering main class");
        verify(CLASS);
        verify(IDENTIFIER);
        verify('{');
        verify(PUBLIC);
        verify(STATIC);
        verify(VOID);
        verify(MAIN);
        verify('(');
        verify(STRING);
        verify('[');
        verify(']');
        verify(IDENTIFIER);
        verify(')');
        verify('{');
        statement();
        verify('}');
        verify('}');
    }

    private void classDeclaration() {
        // "class" Identifier ( "extends" Identifier )? "{" ( VarDeclaration )* (
        // MethodDeclaration )* "}"
        printDebug("Entering class declaration");
        verify(CLASS);
        verify(IDENTIFIER);
        if (laToken == EXTENDS) {
            verify(EXTENDS);
            verify(IDENTIFIER);
        }
        verify('{');
        while (laToken == INT || laToken == BOOLEAN || laToken == IDENTIFIER) {
            varDeclaration();
        }
        while (laToken == PUBLIC) {
            methodDeclaration();
        }
        verify('}');
    }

    private void varDeclaration() {
        // Type Identifier ";"
        printDebug("Entering variable declaration");
        type();
        verify(IDENTIFIER);
        verify(';');
    }

    private void methodDeclaration() {
        // "public" Type Identifier "(" ( Type Identifier ( "," Type Identifier )* )?
        // ")" "{" ( VarDeclaration )* ( Statement )* "return" Expression ";" "}"
        printDebug("Entering method declaration");
        verify(PUBLIC);
        type();
        verify(IDENTIFIER);
        verify('(');
        if (laToken == INT || laToken == BOOLEAN || laToken == IDENTIFIER) {
            type();
            verify(IDENTIFIER);
            while (laToken == ',') {
                verify(',');
                type();
                verify(IDENTIFIER);
            }
        }
        verify(')');
        verify('{');
        while (laToken == INT || laToken == BOOLEAN || laToken == IDENTIFIER) {
            varDeclaration();
        }
        while (laToken == IF || laToken == WHILE || laToken == PRINT || laToken == '{' || laToken == IDENTIFIER || laToken == STATEMENT) {
            statement();
        }
        verify(RETURN);
        expression();
        verify(';');
        verify('}');
    }

    private void type() {
        // "int" "[" "]" | "boolean" | "int" | Identifier
        printDebug("Entering type");
        if (laToken == INT) {
            verify(INT);
            if (laToken == '[') {
                verify('[');
                verify(']');
            }
        } else if (laToken == BOOLEAN) {
            verify(BOOLEAN);
        } else {
            verify(IDENTIFIER);
        }
    }

    private void statement() {
        verify(STATEMENT);
    }

    private void expression() {
        verify(EXPRESSION);
    }

    private void verify(int expected) {
        printDebug("Verify " + ((laToken < BASE_TOKEN_NUM)
                ? Character.toString(laToken)
                : tokenNames.get(laToken)));

        if (laToken == expected) {
            laToken = this.readToken();
        } else {
            String expStr, laStr;

            expStr = ((expected < BASE_TOKEN_NUM)
                    ? "" + (char) expected
                    : tokenNames.get(expected));

            laStr = ((laToken < BASE_TOKEN_NUM)
                    ? Character.toString(laToken)
                    : tokenNames.get(laToken));

            error("expected token " + expStr + ", got " + laStr + " instead");
        }
    }

    private void printDebug(String str) {
        if (debug) {
            System.out.println(str);
        }
    }

    private int readToken() {
        int retVal = -1;
        try {
//            yylval = new ParserVal(0);
            retVal = lexer.yylex();
        } catch (IOException e) {
            System.err.println("IO Error:" + e);
        }

        return retVal;
    }

    public void error(String error) {
        System.err.println("Error: " + error);
        System.err.println("Input rejected");
        System.out.println("\n\nFailure!");
        System.exit(1);

    }

    public void setDebug(boolean trace) {
        debug = trace;
    }

    public static void main(String[] args) {
        Parser parser = null;
        try {
            if (args.length == 0) {
                parser = new Parser(new InputStreamReader(System.in));
            } else {
                parser = new Parser(new java.io.FileReader(args[0]));
            }

            parser.setDebug(true);
            laToken = parser.readToken();

            parser.goal();

            if (laToken == Yylex.YYEOF) {
                System.out.println("Success!");
            } else {
                System.out.println("Failed - expected EOF.");
            }
        } catch (java.io.FileNotFoundException e) {
            System.out.println("File not found : \"" + args[0] + "\"");
        }
    }

}
