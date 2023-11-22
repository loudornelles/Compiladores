//### This file created by BYACC 1.8(/Java extension  1.15)
//### Java capabilities added 7 Jan 97, Bob Jamison
//### Updated : 27 Nov 97  -- Bob Jamison, Joe Nieten
//###           01 Jan 98  -- Bob Jamison -- fixed generic semantic constructor
//###           01 Jun 99  -- Bob Jamison -- added Runnable support
//###           06 Aug 00  -- Bob Jamison -- made state variables class-global
//###           03 Jan 01  -- Bob Jamison -- improved flags, tracing
//###           16 May 01  -- Bob Jamison -- added custom stack sizing
//###           04 Mar 02  -- Yuval Oren  -- improved java performance, added options
//###           14 Mar 02  -- Tomas Hurka -- -d support, static initializer workaround
//### Please send bug reports to tom@hukatronic.cz
//### static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";






//#line 2 "src/parser.y"
  import java.io.*;
  import java.util.Map;
  import java.util.HashMap;
  import java.util.List;
  import java.util.ArrayList;
  import ast.*;
//#line 24 "Parser.java"




public class Parser
{

boolean yydebug;        //do I want debug output?
int yynerrs;            //number of errors so far
int yyerrflag;          //was there an error?
int yychar;             //the current working character

//########## MESSAGES ##########
//###############################################################
// method: debug
//###############################################################
void debug(String msg)
{
  if (yydebug)
    System.out.println(msg);
}

//########## STATE STACK ##########
final static int YYSTACKSIZE = 500;  //maximum stack size
int statestk[] = new int[YYSTACKSIZE]; //state stack
int stateptr;
int stateptrmax;                     //highest index of stackptr
int statemax;                        //state when highest index reached
//###############################################################
// methods: state stack push,pop,drop,peek
//###############################################################
final void state_push(int state)
{
  try {
		stateptr++;
		statestk[stateptr]=state;
	 }
	 catch (ArrayIndexOutOfBoundsException e) {
     int oldsize = statestk.length;
     int newsize = oldsize * 2;
     int[] newstack = new int[newsize];
     System.arraycopy(statestk,0,newstack,0,oldsize);
     statestk = newstack;
     statestk[stateptr]=state;
  }
}
final int state_pop()
{
  return statestk[stateptr--];
}
final void state_drop(int cnt)
{
  stateptr -= cnt; 
}
final int state_peek(int relative)
{
  return statestk[stateptr-relative];
}
//###############################################################
// method: init_stacks : allocate and prepare stacks
//###############################################################
final boolean init_stacks()
{
  stateptr = -1;
  val_init();
  return true;
}
//###############################################################
// method: dump_stacks : show n levels of the stacks
//###############################################################
void dump_stacks(int count)
{
int i;
  System.out.println("=index==state====value=     s:"+stateptr+"  v:"+valptr);
  for (i=0;i<count;i++)
    System.out.println(" "+i+"    "+statestk[i]+"      "+valstk[i]);
  System.out.println("======================");
}


//########## SEMANTIC VALUES ##########
//public class ParserVal is defined in ParserVal.java


String   yytext;//user variable to return contextual strings
ParserVal yyval; //used to return semantic vals from action routines
ParserVal yylval;//the 'lval' (result) I got from yylex()
ParserVal valstk[];
int valptr;
//###############################################################
// methods: value stack push,pop,drop,peek.
//###############################################################
void val_init()
{
  valstk=new ParserVal[YYSTACKSIZE];
  yyval=new ParserVal();
  yylval=new ParserVal();
  valptr=-1;
}
void val_push(ParserVal val)
{
  if (valptr>=YYSTACKSIZE)
    return;
  valstk[++valptr]=val;
}
ParserVal val_pop()
{
  if (valptr<0)
    return new ParserVal();
  return valstk[valptr--];
}
void val_drop(int cnt)
{
int ptr;
  ptr=valptr-cnt;
  if (ptr<0)
    return;
  valptr = ptr;
}
ParserVal val_peek(int relative)
{
int ptr;
  ptr=valptr-relative;
  if (ptr<0)
    return new ParserVal();
  return valstk[ptr];
}
final ParserVal dup_yyval(ParserVal val)
{
  ParserVal dup = new ParserVal();
  dup.ival = val.ival;
  dup.dval = val.dval;
  dup.sval = val.sval;
  dup.obj = val.obj;
  return dup;
}
//#### end semantic value section ####
public final static short CLASS=257;
public final static short PUBLIC=258;
public final static short STATIC=259;
public final static short VOID=260;
public final static short MAIN=261;
public final static short RETURN=262;
public final static short STRING=263;
public final static short EXTENDS=264;
public final static short NEW=265;
public final static short INT=266;
public final static short BOOLEAN=267;
public final static short IF=268;
public final static short ELSE=269;
public final static short WHILE=270;
public final static short THIS=271;
public final static short PRINT=272;
public final static short LENGTH=273;
public final static short TRUE=274;
public final static short FALSE=275;
public final static short IDENTIFIER=276;
public final static short INTEGER=277;
public final static short EQUALS=278;
public final static short AND=279;
public final static short YYERRCODE=256;
final static short yylhs[] = {                           -1,
    0,    1,    4,    4,    2,    2,    5,    6,    6,    7,
    7,    9,   10,   10,   10,   10,    8,    8,   11,   12,
   12,   15,   15,   16,    3,    3,    3,    3,    3,    3,
   17,   17,   13,   13,   14,   14,   14,   14,   14,   14,
   14,   14,   14,   14,   14,   14,   14,   14,   14,   14,
   14,   18,   18,   18,
};
final static short yylen[] = {                            2,
    2,   17,    1,    2,    0,    1,    7,    0,    2,    0,
    2,    3,    3,    1,    1,    1,    0,    2,   13,    0,
    1,    3,    1,    2,    3,    7,    5,    5,    4,    7,
    2,    1,    0,    1,    3,    3,    3,    3,    3,    4,
    3,    6,    1,    1,    1,    1,    1,    5,    4,    2,
    3,    0,    1,    3,
};
final static short yydefred[] = {                         0,
    0,    0,    0,    0,    0,    1,    0,    3,    0,    0,
    4,    0,    0,    0,    0,    9,   10,    0,    0,    0,
    0,   14,   16,    0,   11,    0,    0,    0,    0,    7,
   18,    0,    0,   13,    0,   12,    0,    0,    0,    0,
    0,    0,    0,    0,   23,    0,   24,    0,    0,    0,
   10,   22,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,   32,    0,    0,    0,    0,    0,
    0,   47,   44,   45,   46,   43,    0,    0,    0,    0,
    0,    0,    0,   25,   31,    2,    0,    0,    0,   50,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,   29,    0,    0,    0,   51,   35,    0,   41,
    0,    0,    0,    0,   36,    0,   27,   28,    0,    0,
    0,   49,   40,    0,    0,    0,   19,   48,    0,    0,
   26,   30,   42,    0,    0,
};
final static short yydgoto[] = {                          2,
    3,    6,   65,    7,    8,   14,   19,   24,   25,   26,
   31,   43,   66,   79,   44,   45,   67,  130,
};
final static short yysindex[] = {                      -233,
 -237,    0, -207,  -71, -220,    0, -207,    0, -200, -202,
    0, -187, -191,  -47, -173,    0,    0, -162, -219,   75,
   26,    0,    0,  -83,    0, -158, -143,   30, -219,    0,
    0,   63,   33,    0, -151,    0,   34,   86, -146, -219,
   90, -137,   99,   97,    0,   19,    0,   21, -219, -100,
    0,    0,  105,  106,  107,  -45, -100,   31, -115,  -33,
  -33,  -33,  -33,  -33,    0,   35, -100,   37,  -45, -114,
 -223,    0,    0,    0,    0,    0,  -33,  -33,  -40,  -32,
  -24,  -11,   -5,    0,    0,    0,  -33,   67,  119,    0,
  -16,  -33,  -33, -192,  -33,  -33,  -33,  -33, -100, -100,
  108,  110,    0,   18,  -33,  128,    0,    0,   23,    0,
  133,  -27,  -27,  -56,    0,  -95,    0,    0,  -33,   56,
   28,    0,    0,  -33, -100,   47,    0,    0,   52,   59,
    0,    0,    0,  -33,   52,
};
final static short yyrindex[] = {                         0,
    0,    0,  177,    0,    0,    0,  190,    0,    0,   68,
    0,    0,    0,    0,    0,    0,    0,    0,  -80,    0,
  -84,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,  152,
    0,    0,    0,  155,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   77,    0,  -59,    0,
    0,    0,    0,    0,    0,    0, -113,    0, -191,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,  142,  165,   91,    0,    0,    0,    0,    0,    0,
    0,    0,    0,   60,    0,    0,    0,    0,   64,    0,
    0,    0,    0,    0,   69,
};
final static short yygindex[] = {                         0,
    0,    0,   29,    0,  197,    0,  154,    0,    0,   62,
    0,    0,  153,  102,    0,  164,    0,    0,
};
final static int YYTABLESIZE=331;
static short yytable[];
static { yytable();}
static void yytable(){
yytable = new short[]{                         77,
   99,   97,   96,   98,   95,   94,   78,   57,  100,   97,
   96,   34,   95,   94,   97,   64,  101,   97,   96,   98,
   95,   94,   57,    1,  107,   97,   96,   98,   95,   94,
   97,   96,   98,   95,   94,   98,   97,   96,    4,   95,
   94,   30,   88,   98,   17,   63,   21,   22,   98,    5,
   93,    9,   89,  103,   98,   10,   23,   12,   93,   97,
   96,   13,   95,   94,   97,   96,   93,   95,   94,   97,
   96,   15,   95,   94,   93,   17,  120,   98,   58,   93,
  110,  102,   98,  111,   16,   93,   18,   98,   97,   96,
   35,   95,   94,   97,   96,   85,   95,   94,   20,  133,
   52,   42,  134,   52,   53,  132,   98,   53,   93,   54,
   42,   98,   54,   93,   27,  123,   28,   32,   93,   33,
  128,   36,   34,   37,   38,   40,   39,  116,  117,   41,
   46,   39,   39,   39,   39,   39,   39,   93,   47,   48,
   49,   50,   93,   51,   60,   61,   62,   87,   34,   39,
   21,   22,   53,  131,   54,   68,   55,  105,  106,   84,
   69,   86,   80,   81,   82,   83,  118,   53,  122,   54,
  119,   55,  124,  125,   29,   56,    5,   17,   90,   91,
  127,   39,   38,   39,   38,   38,   38,   38,  104,    6,
    8,   15,   20,  108,  109,   21,  112,  113,  114,  115,
   38,   33,   33,   11,   59,   37,  121,   37,   37,   37,
   37,   70,   52,    0,    0,    0,    0,    0,    0,    0,
  126,    0,   92,   37,    0,  129,    0,    0,    0,    0,
    0,   71,   38,    0,   38,  135,    0,   72,   92,    0,
   73,   74,   75,   76,    0,    0,   92,    0,    0,    0,
    0,   92,    0,    0,   92,   37,    0,   37,    0,    0,
    0,    0,   92,    0,    0,    0,    0,   92,    0,    0,
    0,    0,    0,   92,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   92,    0,    0,    0,
    0,   92,    0,    0,    0,    0,   92,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   92,    0,    0,    0,    0,
   92,
};
}
static short yycheck[];
static { yycheck(); }
static void yycheck() {
yycheck = new short[] {                         33,
   41,   42,   43,   60,   45,   46,   40,  123,   41,   42,
   43,  125,   45,   46,   42,   61,   41,   42,   43,   60,
   45,   46,  123,  257,   41,   42,   43,   60,   45,   46,
   42,   43,   60,   45,   46,   60,   42,   43,  276,   45,
   46,  125,  266,   60,  125,   91,  266,  267,   60,  257,
   91,  123,  276,   59,   60,  276,  276,  258,   91,   42,
   43,  264,   45,   46,   42,   43,   91,   45,   46,   42,
   43,  259,   45,   46,   91,  123,   59,   60,   50,   91,
  273,   93,   60,  276,  276,   91,  260,   60,   42,   43,
   29,   45,   46,   42,   43,   67,   45,   46,  261,   41,
   41,   40,   44,   44,   41,   59,   60,   44,   91,   41,
   49,   60,   44,   91,   40,   93,   91,  276,   91,  263,
   93,   59,   93,   91,  276,   40,   93,   99,  100,  276,
   41,   41,   42,   43,   44,   45,   46,   91,  276,   41,
   44,  123,   91,  123,   40,   40,   40,  262,  262,   59,
  266,  267,  268,  125,  270,  125,  272,   91,   40,  125,
  276,  125,   61,   62,   63,   64,   59,  268,   41,  270,
   61,  272,   40,  269,  258,  276,    0,  258,   77,   78,
  125,   91,   41,   93,   43,   44,   45,   46,   87,    0,
  123,  276,   41,   92,   93,   41,   95,   96,   97,   98,
   59,  125,  262,    7,   51,   41,  105,   43,   44,   45,
   46,   59,   49,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  119,   -1,  279,   59,   -1,  124,   -1,   -1,   -1,   -1,
   -1,  265,   91,   -1,   93,  134,   -1,  271,  279,   -1,
  274,  275,  276,  277,   -1,   -1,  279,   -1,   -1,   -1,
   -1,  279,   -1,   -1,  279,   91,   -1,   93,   -1,   -1,
   -1,   -1,  279,   -1,   -1,   -1,   -1,  279,   -1,   -1,
   -1,   -1,   -1,  279,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,  279,   -1,   -1,   -1,
   -1,  279,   -1,   -1,   -1,   -1,  279,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,   -1,  279,   -1,   -1,   -1,   -1,
  279,
};
}
final static short YYFINAL=2;
final static short YYMAXTOKEN=279;
final static String yyname[] = {
"end-of-file",null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,"'!'",null,null,null,null,null,null,"'('","')'","'*'","'+'",
"','","'-'","'.'",null,null,null,null,null,null,null,null,null,null,null,null,
"';'","'<'","'='",null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,"'['",null,"']'",null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,"'{'",null,"'}'",null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,null,
null,null,null,null,null,null,null,null,null,"CLASS","PUBLIC","STATIC","VOID",
"MAIN","RETURN","STRING","EXTENDS","NEW","INT","BOOLEAN","IF","ELSE","WHILE",
"THIS","PRINT","LENGTH","TRUE","FALSE","IDENTIFIER","INTEGER","EQUALS","AND",
};
final static String yyrule[] = {
"$accept : Goal",
"Goal : MainClass ClassDeclarationarationListOptional",
"MainClass : CLASS IDENTIFIER '{' PUBLIC STATIC VOID MAIN '(' STRING '[' ']' IDENTIFIER ')' '{' Statement '}' '}'",
"ClassDeclarationarationList : ClassDeclaration",
"ClassDeclarationarationList : ClassDeclarationarationList ClassDeclaration",
"ClassDeclarationarationListOptional :",
"ClassDeclarationarationListOptional : ClassDeclarationarationList",
"ClassDeclaration : CLASS IDENTIFIER ClassExtendsOptional '{' VarDeclarationList MethodDeclarationList '}'",
"ClassExtendsOptional :",
"ClassExtendsOptional : EXTENDS IDENTIFIER",
"VarDeclarationList :",
"VarDeclarationList : VarDeclarationList VarDeclaration",
"VarDeclaration : Type IDENTIFIER ';'",
"Type : INT '[' ']'",
"Type : BOOLEAN",
"Type : INT",
"Type : IDENTIFIER",
"MethodDeclarationList :",
"MethodDeclarationList : MethodDeclarationList MethodDeclaration",
"MethodDeclaration : PUBLIC Type IDENTIFIER '(' ArgsListOptional ')' '{' VarDeclarationList StatementListOptional RETURN Expression ';' '}'",
"ArgsListOptional :",
"ArgsListOptional : ArgsList",
"ArgsList : ArgsList ',' Arg",
"ArgsList : Arg",
"Arg : Type IDENTIFIER",
"Statement : '{' StatementListOptional '}'",
"Statement : IF '(' Expression ')' Statement ELSE Statement",
"Statement : WHILE '(' Expression ')' Statement",
"Statement : PRINT '(' Expression ')' ';'",
"Statement : IDENTIFIER '=' Expression ';'",
"Statement : IDENTIFIER '[' Expression ']' '=' Expression ';'",
"StatementList : StatementList Statement",
"StatementList : Statement",
"StatementListOptional :",
"StatementListOptional : StatementList",
"Expression : Expression AND Expression",
"Expression : Expression '<' Expression",
"Expression : Expression '+' Expression",
"Expression : Expression '-' Expression",
"Expression : Expression '*' Expression",
"Expression : Expression '[' Expression ']'",
"Expression : Expression '.' LENGTH",
"Expression : Expression '.' IDENTIFIER '(' ExpressionList ')'",
"Expression : INTEGER",
"Expression : TRUE",
"Expression : FALSE",
"Expression : IDENTIFIER",
"Expression : THIS",
"Expression : NEW INT '[' Expression ']'",
"Expression : NEW IDENTIFIER '(' ')'",
"Expression : '!' Expression",
"Expression : '(' Expression ')'",
"ExpressionList :",
"ExpressionList : Expression",
"ExpressionList : ExpressionList ',' Expression",
};

//#line 238 "src/parser.y"

static Map<String, ClassDeclaration> classes = new HashMap<String, ClassDeclaration>();

/* método de chamada do Parser via linha de comando */
public static void main (String [] args) throws IOException {
    Parser yyparser = new Parser(new FileReader(args[0]));
    
    yyparser.yyparse(); // dispara o processo de análise sintática e léxica

    IdentifierType.contextGlobal = classes;

    for(ClassDeclaration classDecl : classes.values()) {
        classDecl.globalContext = classes;
        classDecl.validate();
    }

    System.out.println("Semantic verification success");
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
        int token = lexer.yylex();
        if (token == IDENTIFIER) {
            yylval = new ParserVal(lexer.yytext());
        } else if (token == INTEGER) {
            yylval = new ParserVal(Integer.parseInt(lexer.yytext()));
        } else {
            yylval = new ParserVal(0);
        }
        retVal = token;
    } catch (IOException e) {
        System.err.println("IO Error:" + e);
    }
    return retVal; //retorna o token para o Parser
}

/* método de manipulação de erros de sintaxe */
public void yyerror (String error) {
    System.err.println("Erro: " + error + " na linha " + lexer.getLine());
    throw new Error("Entrada rejeitada");
}

<T> void putIfAbsentOrThrow(Map<String, T> map, String key, T value) {
    if (map.containsKey(key)) {
        throw new Error(key + " already declared");
    } else {
        map.put(key, value);
    }
}
//#line 412 "Parser.java"
//###############################################################
// method: yylexdebug : check lexer state
//###############################################################
void yylexdebug(int state,int ch)
{
String s=null;
  if (ch < 0) ch=0;
  if (ch <= YYMAXTOKEN) //check index bounds
     s = yyname[ch];    //now get it
  if (s==null)
    s = "illegal-symbol";
  debug("state "+state+", reading "+ch+" ("+s+")");
}





//The following are now global, to aid in error reporting
int yyn;       //next next thing to do
int yym;       //
int yystate;   //current parsing state from state table
String yys;    //current token string


//###############################################################
// method: yyparse : parse input and execute indicated items
//###############################################################
int yyparse()
{
boolean doaction;
  init_stacks();
  yynerrs = 0;
  yyerrflag = 0;
  yychar = -1;          //impossible char forces a read
  yystate=0;            //initial state
  state_push(yystate);  //save it
  val_push(yylval);     //save empty value
  while (true) //until parsing is done, either correctly, or w/error
    {
    doaction=true;
    if (yydebug) debug("loop"); 
    //#### NEXT ACTION (from reduction table)
    for (yyn=yydefred[yystate];yyn==0;yyn=yydefred[yystate])
      {
      if (yydebug) debug("yyn:"+yyn+"  state:"+yystate+"  yychar:"+yychar);
      if (yychar < 0)      //we want a char?
        {
        yychar = yylex();  //get next token
        if (yydebug) debug(" next yychar:"+yychar);
        //#### ERROR CHECK ####
        if (yychar < 0)    //it it didn't work/error
          {
          yychar = 0;      //change it to default string (no -1!)
          if (yydebug)
            yylexdebug(yystate,yychar);
          }
        }//yychar<0
      yyn = yysindex[yystate];  //get amount to shift by (shift index)
      if ((yyn != 0) && (yyn += yychar) >= 0 &&
          yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
        {
        if (yydebug)
          debug("state "+yystate+", shifting to state "+yytable[yyn]);
        //#### NEXT STATE ####
        yystate = yytable[yyn];//we are in a new state
        state_push(yystate);   //save it
        val_push(yylval);      //push our lval as the input for next rule
        yychar = -1;           //since we have 'eaten' a token, say we need another
        if (yyerrflag > 0)     //have we recovered an error?
           --yyerrflag;        //give ourselves credit
        doaction=false;        //but don't process yet
        break;   //quit the yyn=0 loop
        }

    yyn = yyrindex[yystate];  //reduce
    if ((yyn !=0 ) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
      {   //we reduced!
      if (yydebug) debug("reduce");
      yyn = yytable[yyn];
      doaction=true; //get ready to execute
      break;         //drop down to actions
      }
    else //ERROR RECOVERY
      {
      if (yyerrflag==0)
        {
        yyerror("syntax error");
        yynerrs++;
        }
      if (yyerrflag < 3) //low error count?
        {
        yyerrflag = 3;
        while (true)   //do until break
          {
          if (stateptr<0)   //check for under & overflow here
            {
            yyerror("stack underflow. aborting...");  //note lower case 's'
            return 1;
            }
          yyn = yysindex[state_peek(0)];
          if ((yyn != 0) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
            if (yydebug)
              debug("state "+state_peek(0)+", error recovery shifting to state "+yytable[yyn]+" ");
            yystate = yytable[yyn];
            state_push(yystate);
            val_push(yylval);
            doaction=false;
            break;
            }
          else
            {
            if (yydebug)
              debug("error recovery discarding state "+state_peek(0)+" ");
            if (stateptr<0)   //check for under & overflow here
              {
              yyerror("Stack underflow. aborting...");  //capital 'S'
              return 1;
              }
            state_pop();
            val_pop();
            }
          }
        }
      else            //discard this token
        {
        if (yychar == 0)
          return 1; //yyabort
        if (yydebug)
          {
          yys = null;
          if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
          if (yys == null) yys = "illegal-symbol";
          debug("state "+yystate+", error recovery discards token "+yychar+" ("+yys+")");
          }
        yychar = -1;  //read another
        }
      }//end error recovery
    }//yyn=0 loop
    if (!doaction)   //any reason not to proceed?
      continue;      //skip action
    yym = yylen[yyn];          //get count of terminals on rhs
    if (yydebug)
      debug("state "+yystate+", reducing "+yym+" by rule "+yyn+" ("+yyrule[yyn]+")");
    if (yym>0)                 //if count of rhs not 'nil'
      yyval = val_peek(yym-1); //get current semantic value
    yyval = dup_yyval(yyval); //duplicate yyval if ParserVal is used as semantic value
    switch(yyn)
      {
//########## USER-SUPPLIED ACTIONS ##########
case 1:
//#line 44 "src/parser.y"
{
            classes = (Map<String, ClassDeclaration>)val_peek(0).obj;
            ClassDeclaration mainClass = (ClassDeclaration)val_peek(1).obj;
            putIfAbsentOrThrow(classes, mainClass.name, mainClass);
        }
break;
case 2:
//#line 51 "src/parser.y"
{
                List<Var> parameters = new ArrayList<Var>();
                Var var = new Var(Type.stringArrayType, val_peek(5).sval);
                parameters.add(var);
                
                List<Statement> statements = new ArrayList<Statement>();
                statements.add((Statement)val_peek(2).obj);
            
                Method main = new Method(
                    "main",
                    Type.voidType,
                    parameters,
                    new HashMap<String, Var>(),
                    statements,
                    null
                );

                Map<String, Method> methods = new HashMap<String, Method>();
                putIfAbsentOrThrow(methods, main.name, main);

                Map<String, Var> fields = new HashMap<String, Var>();

                yyval.obj = new ClassDeclaration(
                    val_peek(15).sval,
                    fields,
                    methods
                );
            }
break;
case 3:
//#line 81 "src/parser.y"
{
                                    HashMap<String, ClassDeclaration> classDeclList = new HashMap<String, ClassDeclaration>();
                                    
                                    ClassDeclaration classDecl = (ClassDeclaration)val_peek(0).obj;
                                    putIfAbsentOrThrow(classDeclList, classDecl.name, classDecl);
                                    
                                    yyval.obj = classDeclList;
                                }
break;
case 4:
//#line 89 "src/parser.y"
{
                                    HashMap<String, ClassDeclaration> classDeclList = (HashMap<String, ClassDeclaration>)val_peek(1).obj;

                                    ClassDeclaration classDecl = (ClassDeclaration)val_peek(0).obj;
                                    putIfAbsentOrThrow(classDeclList, classDecl.name, classDecl);

                                    yyval.obj = classDeclList;
                                }
break;
case 5:
//#line 99 "src/parser.y"
{ yyval.obj = new HashMap<String, ClassDeclaration>(); }
break;
case 6:
//#line 100 "src/parser.y"
{ yyval = val_peek(0); }
break;
case 7:
//#line 103 "src/parser.y"
{
                        yyval.obj = new ClassDeclaration(val_peek(5).sval, (Map<String, Var>)val_peek(2).obj, (Map<String, Method>)val_peek(1).obj);
                    }
break;
case 8:
//#line 108 "src/parser.y"
{}
break;
case 9:
//#line 109 "src/parser.y"
{ yyval = val_peek(0); }
break;
case 10:
//#line 112 "src/parser.y"
{ yyval.obj = new HashMap<String, Var>(); }
break;
case 11:
//#line 113 "src/parser.y"
{
                        HashMap<String, Var> varDeclList = (HashMap<String, Var>)val_peek(1).obj;
                        Var varDecl = (Var)val_peek(0).obj;

                        putIfAbsentOrThrow(varDeclList, varDecl.name, varDecl);

                        yyval.obj = varDeclList;
                    }
break;
case 12:
//#line 123 "src/parser.y"
{
                    yyval.obj = new Var((Type)val_peek(2).obj, val_peek(1).sval);
                }
break;
case 13:
//#line 128 "src/parser.y"
{ yyval.obj = Type.intArrayType; }
break;
case 14:
//#line 129 "src/parser.y"
{ yyval.obj = Type.booleanType; }
break;
case 15:
//#line 130 "src/parser.y"
{ yyval.obj = Type.intType; }
break;
case 16:
//#line 131 "src/parser.y"
{ yyval.obj = new IdentifierType(val_peek(0).sval); }
break;
case 17:
//#line 134 "src/parser.y"
{ yyval.obj = new HashMap<String, Method>(); }
break;
case 18:
//#line 135 "src/parser.y"
{
                            Map<String, Method> methodDeclList = (Map<String, Method>)val_peek(1).obj;
                            Method method = (Method)val_peek(0).obj;

                            putIfAbsentOrThrow(methodDeclList, method.name, method);

                            yyval.obj = methodDeclList;
                        }
break;
case 19:
//#line 146 "src/parser.y"
{
                        yyval.obj = new Method(
                            val_peek(10).sval,
                            (Type)val_peek(11).obj,
                            (List<Var>)val_peek(8).obj,
                            (Map<String, Var>)val_peek(5).obj,
                            (List<Statement>)val_peek(4).obj,
                            (Expression)val_peek(2).obj
                        );
                    }
break;
case 20:
//#line 157 "src/parser.y"
{ yyval.obj = new ArrayList<Var>(); }
break;
case 21:
//#line 158 "src/parser.y"
{ yyval = val_peek(0); }
break;
case 22:
//#line 161 "src/parser.y"
{
                List<Var> argsList = (List<Var>)val_peek(2).obj;
                Var var = (Var)val_peek(0).obj;

                argsList.add(var);

                yyval.obj = argsList;
            }
break;
case 23:
//#line 169 "src/parser.y"
{
                List<Var> argsList = new ArrayList<Var>();
                Var var = (Var)val_peek(0).obj;

                argsList.add(var);

                yyval.obj = argsList;
            }
break;
case 24:
//#line 179 "src/parser.y"
{ yyval.obj = new Var((Type)val_peek(1).obj, val_peek(0).sval); }
break;
case 25:
//#line 181 "src/parser.y"
{ yyval.obj = new BlockStatement((List<Statement>)val_peek(1).obj); }
break;
case 26:
//#line 182 "src/parser.y"
{ yyval.obj = new IfStatement((Expression)val_peek(4).obj, (Statement)val_peek(2).obj, (Statement)val_peek(0).obj); }
break;
case 27:
//#line 183 "src/parser.y"
{ yyval.obj = new WhileStatement((Expression)val_peek(2).obj, (Statement)val_peek(0).obj); }
break;
case 28:
//#line 184 "src/parser.y"
{ yyval.obj = new PrintStatement((Expression)val_peek(2).obj); }
break;
case 29:
//#line 185 "src/parser.y"
{ yyval.obj = new AssignmentStatement(val_peek(3).sval, (Expression)val_peek(1).obj); }
break;
case 30:
//#line 186 "src/parser.y"
{ yyval.obj = new ArrayAssignmentStatement(val_peek(6).sval, (Expression)val_peek(4).obj, (Expression)val_peek(1).obj); }
break;
case 31:
//#line 189 "src/parser.y"
{
                    List<Statement> statementList = (List<Statement>)val_peek(1).obj;
                    statementList.add((Statement)val_peek(0).obj);
                    yyval.obj = statementList;
                }
break;
case 32:
//#line 194 "src/parser.y"
{
                    List<Statement> statementList = new ArrayList<Statement>();
                    statementList.add((Statement)val_peek(0).obj);
                    yyval.obj = statementList;
                }
break;
case 33:
//#line 201 "src/parser.y"
{ yyval.obj = new ArrayList<Statement>(); }
break;
case 34:
//#line 202 "src/parser.y"
{ yyval = val_peek(0); }
break;
case 35:
//#line 205 "src/parser.y"
{ yyval.obj = new BooleanExpression((Expression)val_peek(2).obj, "&&", (Expression)val_peek(0).obj); }
break;
case 36:
//#line 206 "src/parser.y"
{ yyval.obj = new BooleanExpression((Expression)val_peek(2).obj, "<", (Expression)val_peek(0).obj); }
break;
case 37:
//#line 207 "src/parser.y"
{ yyval.obj = new ArithmeticExpression((Expression)val_peek(2).obj, "+", (Expression)val_peek(0).obj); }
break;
case 38:
//#line 208 "src/parser.y"
{ yyval.obj = new ArithmeticExpression((Expression)val_peek(2).obj, "-", (Expression)val_peek(0).obj); }
break;
case 39:
//#line 209 "src/parser.y"
{ yyval.obj = new ArithmeticExpression((Expression)val_peek(2).obj, "*", (Expression)val_peek(0).obj); }
break;
case 40:
//#line 210 "src/parser.y"
{ yyval.obj = new ArrayAccessExpression((Expression)val_peek(3).obj, (Expression)val_peek(1).obj); }
break;
case 41:
//#line 211 "src/parser.y"
{ yyval.obj = new LengthExpression((Expression)val_peek(2).obj); }
break;
case 42:
//#line 212 "src/parser.y"
{ yyval.obj = new MethodCallExpression((Expression)val_peek(5).obj, val_peek(3).sval, (List<Expression>)val_peek(1).obj); }
break;
case 43:
//#line 213 "src/parser.y"
{ yyval.obj = new IntegerLiteralExpression((int)val_peek(0).ival); }
break;
case 44:
//#line 214 "src/parser.y"
{ yyval.obj = new BooleanLiteralExpression(true); }
break;
case 45:
//#line 215 "src/parser.y"
{ yyval.obj = new BooleanLiteralExpression(false); }
break;
case 46:
//#line 216 "src/parser.y"
{ yyval.obj = new IdentifierExpression(val_peek(0).sval); }
break;
case 47:
//#line 217 "src/parser.y"
{ yyval.obj = new IdentifierExpression("this"); }
break;
case 48:
//#line 218 "src/parser.y"
{ yyval.obj = new NewArrayExpression(Type.intType, (Expression)val_peek(1).obj); }
break;
case 49:
//#line 219 "src/parser.y"
{ yyval.obj = new NewObjectExpression(val_peek(2).sval); }
break;
case 50:
//#line 220 "src/parser.y"
{ yyval.obj = new UnaryExpression(val_peek(1).sval, (Expression)val_peek(0).obj); }
break;
case 51:
//#line 221 "src/parser.y"
{ yyval = val_peek(1); }
break;
case 52:
//#line 224 "src/parser.y"
{ yyval.obj = new ArrayList<Expression>(); }
break;
case 53:
//#line 225 "src/parser.y"
{
                    List<Expression> expressionList = new ArrayList<Expression>();
                    expressionList.add((Expression)val_peek(0).obj);
                    yyval.obj = expressionList;
                }
break;
case 54:
//#line 230 "src/parser.y"
{
                    List<Expression> expressions = (List<Expression>)val_peek(2).obj;
                    expressions.add((Expression)val_peek(0).obj);
                    yyval.obj = expressions;
                }
break;
//#line 879 "Parser.java"
//########## END OF USER-SUPPLIED ACTIONS ##########
    }//switch
    //#### Now let's reduce... ####
    if (yydebug) debug("reduce");
    state_drop(yym);             //we just reduced yylen states
    yystate = state_peek(0);     //get new state
    val_drop(yym);               //corresponding value drop
    yym = yylhs[yyn];            //select next TERMINAL(on lhs)
    if (yystate == 0 && yym == 0)//done? 'rest' state and at first TERMINAL
      {
      if (yydebug) debug("After reduction, shifting from state 0 to state "+YYFINAL+"");
      yystate = YYFINAL;         //explicitly say we're done
      state_push(YYFINAL);       //and save it
      val_push(yyval);           //also save the semantic value of parsing
      if (yychar < 0)            //we want another character?
        {
        yychar = yylex();        //get next character
        if (yychar<0) yychar=0;  //clean, if necessary
        if (yydebug)
          yylexdebug(yystate,yychar);
        }
      if (yychar == 0)          //Good exit (if lex returns 0 ;-)
         break;                 //quit the loop--all DONE
      }//if yystate
    else                        //else not done yet
      {                         //get next state and push, for next yydefred[]
      yyn = yygindex[yym];      //find out where to go
      if ((yyn != 0) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn]; //get new state
      else
        yystate = yydgoto[yym]; //else go to new defred
      if (yydebug) debug("after reduction, shifting from state "+state_peek(0)+" to state "+yystate+"");
      state_push(yystate);     //going again, so push state & val...
      val_push(yyval);         //for next action
      }
    }//main loop
  return 0;//yyaccept!!
}
//## end of method parse() ######################################



//## run() --- for Thread #######################################
/**
 * A default run method, used for operating this parser
 * object in the background.  It is intended for extending Thread
 * or implementing Runnable.  Turn off with -Jnorun .
 */
public void run()
{
  yyparse();
}
//## end of method run() ########################################



//## Constructors ###############################################
/**
 * Default constructor.  Turn off with -Jnoconstruct .

 */
public Parser()
{
  //nothing to do
}


/**
 * Create a parser, setting the debug to true or false.
 * @param debugMe true for debugging, false for no debug.
 */
public Parser(boolean debugMe)
{
  yydebug=debugMe;
}
//###############################################################



}
//################### END OF CLASS ##############################
