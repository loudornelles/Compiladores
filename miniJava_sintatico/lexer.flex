import java.io.InputStreamReader;
%%

%unicode
%integer
%line
%char

%{

private Parser yyparser;

public Yylex(java.io.Reader r, Parser yyparser) {
  this(r);
  this.yyparser = yyparser;
}

%}

DIGIT =	[0-9]
LETTER = [a-zA-Z_]
WHITESPACE = [ \t]
LINE_TERMINATOR = \r|\n|\r\n
COMMENT = \/\/.*

%%


class              { return Parser.CLASS; }
public             { return Parser.PUBLIC; }
static             { return Parser.STATIC; }
void               { return Parser.VOID; }
main               { return Parser.MAIN; }
return             { return Parser.RETURN; }
String             { return Parser.STRING; }
extends            { return Parser.EXTENDS; }
new                { return Parser.NEW; }
int                { return Parser.INT; }
boolean            { return Parser.BOOLEAN; }
if                 { return Parser.IF; }
else               { return Parser.ELSE; }
while              { return Parser.WHILE; }
this               { return Parser.THIS; }
System.out.println { return Parser.PRINT; }
length             { return Parser.LENGTH; }
true               { return Parser.TRUE; }
false              { return Parser.FALSE; }

{LETTER}({LETTER}|{DIGIT})* {return Parser.IDENTIFIER;}
{DIGIT}+                    {return Parser.INTEGER_LITERAL;}

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

"=="                        {return Parser.EQUALS;}
"&&"                        {return Parser.AND;}



{WHITESPACE}+               {}
{LINE_TERMINATOR}		        {}
{COMMENT}                   {}

.          {System.out.println(yyline+1 + ": caracter invalido: "+yytext());}
