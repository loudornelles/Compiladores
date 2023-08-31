import java.io.InputStreamReader;
%%

%unicode
%integer
%line
%char

%{

private JSON yyparser;

public Yylex(java.io.Reader r, JSON yyparser) {
  this(r);
  this.yyparser = yyparser;
}

%}


WHITESPACE=	[ \t]
LINE_TERMINATOR = \r|\n|\r\n
%%

\"(\w\s*)*\"       {return JSON.STRING;}
[-+]?\d+(\.\d+)?   {return JSON.NUMBER;}

"{" |
"}" |
"[" |
"]" |
":" |
","                         {return yytext().charAt(0);}

{WHITESPACE}+               {}
{LINE_TERMINATOR}	        	{}
.          {System.out.println(yyline+1 + ": caracter invalido: "+yytext());}
