import java.io.InputStreamReader;
%%


%public
%class Lexer
%integer
%unicode
%line


%{

public static int CLASS		   = 300;
public static int PUBLIC	   = 301;
public static int STATIC	   = 302;
public static int VOID		   = 303;
public static int MAIN		   = 304;
public static int RETURN	   = 305;
public static int STRING	   = 306;
public static int EXTENDS	   = 307;
public static int NEW		     = 308;
public static int INT		     = 309;
public static int BOOLEAN	   = 310;
public static int IF	       = 311;
public static int ELSE		   = 312;
public static int WHILE		   = 313;
public static int THIS		   = 314;
public static int PRINT		   = 315;
public static int LENGTH	   = 316;
public static int TRUE		   = 317;
public static int FALSE		   = 318;

public static int IDENTIFIER = 319;
public static int INTEGER_LITERAL = 320;
public static int EOF = 321;

public static int EQUALS = 322;
public static int AND = 323;


/**
   * Runs the scanner on input files.
   *
   * This is a standalone scanner, it will print any unmatched
   * text to System.out unchanged.
   *
   * @param argv   the command line, contains the filenames to run
   *               the scanner on.
   */
  public static void main(String argv[]) {
    Lexer scanner;
    if (argv.length == 0) {
      try {        
          // scanner = new Lexer( System.in );
          scanner = new Lexer( new InputStreamReader(System.in) );
          while ( !scanner.zzAtEOF ) 
	        System.out.println("token: "+scanner.yylex()+"\t<"+scanner.yytext()+">");
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
        
    }
    else {
      for (int i = 0; i < argv.length; i++) {
        scanner = null;
        try {
          scanner = new Lexer( new java.io.FileReader(argv[i]) );
          while ( !scanner.zzAtEOF ) 	
                System.out.println("token: "+scanner.yylex()+"\t<"+scanner.yytext()+">");
        }
        catch (java.io.FileNotFoundException e) {
          System.out.println("File not found : \""+argv[i]+"\"");
        }
        catch (java.io.IOException e) {
          System.out.println("IO error scanning file \""+argv[i]+"\"");
          System.out.println(e);
        }
        catch (Exception e) {
          System.out.println("Unexpected exception:");
          e.printStackTrace();
        }
      }
    }
  }


%}

DIGIT =	[0-9]
LETTER = [a-zA-Z_]
WHITESPACE = [ \t]
LINE_TERMINATOR = \r|\n|\r\n
COMMENT = \/\/.*

%%


class              { return CLASS; }
public             { return PUBLIC; }
static             { return STATIC; }
void               { return VOID; }
main               { return MAIN; }
return             { return RETURN; }
String             { return STRING; }
extends            { return EXTENDS; }
new                { return NEW; }
int                { return INT; }
boolean            { return BOOLEAN; }
if                 { return IF; }
else               { return ELSE; }
while              { return WHILE; }
this               { return THIS; }
System.out.println { return PRINT; }
length             { return LENGTH; }
true               { return TRUE; }
false              { return FALSE; }


{LETTER}({LETTER}|{DIGIT})* {return IDENTIFIER;}
{DIGIT}+                    {return INTEGER_LITERAL;}

"{" |
"}" |
"<" |
"+" |
"-" |
"*" |
"[" |
"]" |
"." |
"(" |
")" |
"," |
";" |
"=" |
"!"                         {return yytext().charAt(0);}

"=="                        {return EQUALS;}
"&&"                        {return AND;}



{WHITESPACE}+               {}
{LINE_TERMINATOR}		        {}
{COMMENT}                   {}

.          {System.out.println(yyline+1 + ": caracter invalido: "+yytext());}
