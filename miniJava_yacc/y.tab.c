#ifndef lint
static char yysccsid[] = "@(#)yaccpar	1.8 (Berkeley) 01/20/90";
#endif
#define YYBYACC 1
#line 2 "parser_c.y"
#include <stdio.h>
#include "node.h"

extern int yylineno;

int yylex();
void yyerror(char *s);
#line 11 "parser_c.y"
typedef union {
    struct Node* node;
    double d;
} YYSTYPE;
#line 19 "y.tab.c"
#define Class 257
#define Public 258
#define Static 259
#define Void 260
#define Main 261
#define If 262
#define Else 263
#define While 264
#define Extends 265
#define Integer 266
#define Boolean 267
#define String 268
#define True 269
#define False 270
#define Id 271
#define IntegerIteral 272
#define And 273
#define This 274
#define New 275
#define Println 276
#define Length 277
#define Return 278
#define Rbrace 279
#define Raccess 280
#define LBracket 281
#define RBracket 282
#define Semicolon 283
#define Comma 284
#define Laccess 285
#define Lbrace 286
#define YYERRCODE 256
short yylhs[] = {                                        -1,
    0,    1,    6,    6,    5,    5,    7,    2,    2,    8,
    8,    9,   10,   10,   11,   11,   12,   13,   13,   14,
   14,   15,   16,   16,   17,   17,    4,    4,    4,    4,
   18,   18,   18,   18,   18,   18,   19,   19,   20,   20,
   21,   21,   21,   21,   21,   21,   21,   21,   21,   21,
   21,   21,   21,   21,   21,   21,   21,    3,
};
short yylen[] = {                                         2,
    2,   17,    1,    2,    0,    1,    7,    0,    2,    0,
    2,    3,    0,    1,    1,    2,   13,    0,    1,    1,
    3,    2,    0,    1,    1,    2,    1,    3,    1,    1,
    3,    7,    5,    5,    4,    7,    0,    1,    1,    3,
    3,    3,    3,    3,    3,    4,    3,    6,    1,    1,
    1,    1,    5,    4,    2,    3,    1,    1,
};
short yydefred[] = {                                      0,
    0,    0,    0,   58,    0,    0,    1,    0,    3,    0,
    0,    4,    0,    0,    0,    0,    9,   10,    0,    0,
    0,    0,    0,   29,   30,    0,   11,    0,    0,   15,
    0,    0,    0,    0,    7,   16,    0,    0,   28,   12,
    0,    0,    0,    0,    0,    0,   20,    0,   22,    0,
    0,    0,   10,   21,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,   25,    0,    0,    0,    0,
    0,    0,    0,    0,   26,   49,   50,   57,   52,    0,
    0,    0,   51,    0,    0,    0,   31,    0,    0,    2,
    0,    0,    0,    0,   55,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   35,    0,    0,    0,
   56,   41,    0,    0,   47,    0,    0,    0,    0,   42,
   33,   34,    0,   17,    0,   54,    0,   46,    0,    0,
   53,   32,    0,    0,    0,   36,   48,    0,    0,
};
short yydgoto[] = {                                       2,
    3,   15,   83,   26,    7,    8,    9,   20,   27,   28,
   29,   30,   45,   46,   47,   64,   65,   66,  133,  134,
   84,
};
short yysindex[] = {                                   -251,
 -262,    0, -244,    0, -259, -262,    0, -244,    0, -223,
 -227,    0, -202, -262, -219, -177,    0,    0, -169, -164,
 -182, -158, -184,    0,    0, -262,    0, -174, -144,    0,
 -153, -262, -163, -165,    0,    0, -166, -161,    0,    0,
 -159, -158, -262, -262, -160, -154,    0, -157,    0, -162,
 -158, -150,    0,    0, -117, -133, -149, -140, -139, -117,
  -60, -142,  -60, -132, -117,    0,  -33,  -33,  -33, -131,
  -33,  -33, -129,  -33,    0,    0,    0,    0,    0, -247,
  -33,  -33,    0,  -38,  -17,  -12,    0,   -6,   -1,    0,
    4, -134, -126,   10,    0,  -33, -117,  -33, -261,  -33,
  -33,  -33,  -33, -117, -127,   91,    0, -114,  -33, -120,
    0,    0,  -97,   20,    0, -113,   15,  -58,  -58,    0,
    0,    0,  -33,    0,   26,    0, -117,    0,  -33,   31,
    0,    0, -115, -112,   36,    0,    0,  -33,   36,
};
short yyrindex[] = {                                      0,
    0,    0,  173,    0,    0,    0,    0,  174,    0,    0,
 -111,    0,    0,    0,    0,    0,    0,    0,    0, -103,
    0,    0,  -94,    0,    0,    0,    0,    0, -101,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0, -102,    0,    0,    0, -100,    0,    0,    0,    0,
    0,    0,    0,    0,    0,  -99,    0,    0,    0,  -95,
    0,    0,  -85,    0, -167,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
    0,    0,    0,    0,    0,    0,   42,  -31,  -25,    0,
    0,    0,    0,    0,    0,    0,    0,    0,  -89,    0,
    0,    0,    0,  -88, -195,    0,    0,    0, -187,
};
short yygindex[] = {                                      0,
    0,    0,   84,  -19,    0,    0,  187,  143,    0,    0,
    0,  168,    0,    0,  148,  140,    0,   -4,    0,    0,
   89,
};
#define YYTABLESIZE 327
short yytable[] = {                                      82,
   72,  103,   32,  100,  101,    1,  102,   99,    4,    4,
   43,   43,    6,   43,   43,  115,   44,   44,   92,   44,
   44,  103,   44,    4,  100,  101,   10,  102,   99,  100,
  101,   44,  102,   99,   13,  100,  101,   14,  102,   99,
  100,  101,  103,  102,   99,  100,  101,  103,  102,   99,
   62,  100,  101,  103,  102,   99,   16,  101,  103,  102,
   75,  100,  101,  103,  102,   99,   18,  100,  101,  103,
  102,   99,  100,  101,  103,  102,   99,  100,  101,  103,
  102,   99,   19,   45,    5,  103,   39,   45,   39,   11,
  103,   21,  113,   22,   40,  103,   40,   17,   31,  121,
   33,   23,   24,   25,   35,   25,    4,   23,   24,   34,
   24,   24,    4,   22,   37,   38,   39,   40,   41,   42,
   43,   50,  132,   53,   52,   25,   48,   49,   57,   51,
   58,   67,   23,   24,   25,   55,   73,    4,   61,   63,
   68,   69,   59,   61,   57,   74,   58,   87,   61,   90,
  109,  123,   60,    4,  110,  122,   85,   86,   59,   88,
   89,  126,   91,   93,  124,  127,  137,  129,   60,   94,
   95,  138,    5,    6,    8,   13,   27,   14,   23,   18,
   61,   19,  116,   23,  112,   30,  114,   61,  117,  118,
  119,  120,   37,   38,   12,   56,   36,  125,   54,   70,
    0,    0,    0,    0,    0,    0,    0,    0,    0,    0,
   61,  130,    0,    0,   96,    0,    0,  135,    0,    0,
    0,    0,    0,    0,   71,    0,  139,    0,    0,    0,
    0,    0,    0,    0,   96,   76,   77,    4,   78,    0,
   79,   80,    0,   97,    0,    0,   98,   81,   43,    0,
   43,   43,   43,   43,   44,   96,   44,   44,   44,   44,
   96,    0,    0,    0,  104,    0,   96,   98,    0,  105,
    0,   96,   98,  106,    0,    0,   96,    0,   98,    0,
    0,  107,   96,   98,    0,    0,  108,   96,   98,    0,
    0,  111,   96,    0,   98,    0,    0,    0,   96,  128,
    0,    0,    0,   96,   98,  131,    0,    0,   96,    0,
   98,    0,    0,  136,    0,   98,    0,    0,    0,    0,
   98,   45,    0,   45,   45,   45,   45,
};
short yycheck[] = {                                      33,
   61,   60,   22,   42,   43,  257,   45,   46,  271,  271,
   42,   43,  257,   45,   46,  277,   42,   43,  266,   45,
   46,   60,   42,  271,   42,   43,  286,   45,   46,   42,
   43,   51,   45,   46,  258,   42,   43,  265,   45,   46,
   42,   43,   60,   45,   46,   42,   43,   60,   45,   46,
   55,   42,   43,   60,   45,   46,  259,   43,   60,   45,
   65,   42,   43,   60,   45,   46,  286,   42,   43,   60,
   45,   46,   42,   43,   60,   45,   46,   42,   43,   60,
   45,   46,  260,   42,    1,   60,  282,   46,  284,    6,
   60,  261,   97,  258,  282,   60,  284,   14,  281,  104,
  285,  266,  267,   20,  279,   22,  271,  266,  267,   26,
  278,  279,  271,  258,  268,   32,  280,  283,  285,  281,
  280,  282,  127,  286,  282,   42,   43,   44,  262,  284,
  264,  281,  266,  267,   51,  286,  279,  271,   55,   56,
  281,  281,  276,   60,  262,  278,  264,  279,   65,  279,
  285,   61,  286,  271,  281,  283,   68,   69,  276,   71,
   72,  282,   74,   80,  279,  263,  282,  281,  286,   81,
   82,  284,    0,    0,  286,  279,  271,  279,  278,  282,
   97,  282,   99,  279,   96,  271,   98,  104,  100,  101,
  102,  103,  282,  282,    8,   53,   29,  109,   51,   60,
   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,   -1,
  127,  123,   -1,   -1,  273,   -1,   -1,  129,   -1,   -1,
   -1,   -1,   -1,   -1,  285,   -1,  138,   -1,   -1,   -1,
   -1,   -1,   -1,   -1,  273,  269,  270,  271,  272,   -1,
  274,  275,   -1,  282,   -1,   -1,  285,  281,  280,   -1,
  282,  283,  284,  285,  280,  273,  282,  283,  284,  285,
  273,   -1,   -1,   -1,  282,   -1,  273,  285,   -1,  282,
   -1,  273,  285,  280,   -1,   -1,  273,   -1,  285,   -1,
   -1,  283,  273,  285,   -1,   -1,  283,  273,  285,   -1,
   -1,  282,  273,   -1,  285,   -1,   -1,   -1,  273,  280,
   -1,   -1,   -1,  273,  285,  280,   -1,   -1,  273,   -1,
  285,   -1,   -1,  283,   -1,  285,   -1,   -1,   -1,   -1,
  285,  280,   -1,  282,  283,  284,  285,
};
#define YYFINAL 2
#ifndef YYDEBUG
#define YYDEBUG 1
#endif
#define YYMAXTOKEN 286
#if YYDEBUG
char *yyname[] = {
"end-of-file",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
"'!'",0,0,0,0,0,0,0,0,"'*'","'+'",0,"'-'","'.'",0,0,0,0,0,0,0,0,0,0,0,0,0,"'<'",
"'='",0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,
"Class","Public","Static","Void","Main","If","Else","While","Extends","Integer",
"Boolean","String","True","False","Id","IntegerIteral","And","This","New",
"Println","Length","Return","Rbrace","Raccess","LBracket","RBracket",
"Semicolon","Comma","Laccess","Lbrace",
};
char *yyrule[] = {
"$accept : Goal",
"Goal : MainClass ClassDeclarations",
"MainClass : Class Identifier Lbrace Public Static Void Main LBracket String Laccess Raccess Identifier RBracket Lbrace Statement Rbrace Rbrace",
"ClassDeclarationList : ClassDeclaration",
"ClassDeclarationList : ClassDeclarationList ClassDeclaration",
"ClassDeclarations :",
"ClassDeclarations : ClassDeclarationList",
"ClassDeclaration : Class Identifier ExtendOpt Lbrace VarDeclarations MethodDeclarations Rbrace",
"ExtendOpt :",
"ExtendOpt : Extends Identifier",
"VarDeclarations :",
"VarDeclarations : VarDeclarations VarDeclaration",
"VarDeclaration : Type Identifier Semicolon",
"MethodDeclarations :",
"MethodDeclarations : MethodDeclarationList",
"MethodDeclarationList : MethodDeclaration",
"MethodDeclarationList : MethodDeclarationList MethodDeclaration",
"MethodDeclaration : Public Type Identifier LBracket TypeIdentifiers RBracket Lbrace VarDeclarations Statements Return Expression Semicolon Rbrace",
"TypeIdentifiers :",
"TypeIdentifiers : TypeIdentifierList",
"TypeIdentifierList : TypeIdentifier",
"TypeIdentifierList : TypeIdentifierList Comma TypeIdentifier",
"TypeIdentifier : Type Identifier",
"Statements :",
"Statements : StatementList",
"StatementList : Statement",
"StatementList : StatementList Statement",
"Type : Integer",
"Type : Integer Laccess Raccess",
"Type : Boolean",
"Type : Identifier",
"Statement : Lbrace Statements Rbrace",
"Statement : If LBracket Expression RBracket Statement Else Statement",
"Statement : While LBracket Expression RBracket Statement",
"Statement : Println LBracket Expression RBracket Semicolon",
"Statement : Identifier '=' Expression Semicolon",
"Statement : Identifier Laccess Expression Raccess '=' Expression Semicolon",
"Expressions :",
"Expressions : ExpressionList",
"ExpressionList : Expression",
"ExpressionList : ExpressionList Comma Expression",
"Expression : Expression And Expression",
"Expression : Expression '<' Expression",
"Expression : Expression '+' Expression",
"Expression : Expression '-' Expression",
"Expression : Expression '*' Expression",
"Expression : Expression Laccess Expression Raccess",
"Expression : Expression '.' Length",
"Expression : Expression '.' Identifier LBracket Expressions RBracket",
"Expression : True",
"Expression : False",
"Expression : Identifier",
"Expression : This",
"Expression : New Integer Laccess Expression Raccess",
"Expression : New Identifier LBracket RBracket",
"Expression : '!' Expression",
"Expression : LBracket Expression RBracket",
"Expression : IntegerIteral",
"Identifier : Id",
};
#endif
#define yyclearin (yychar=(-1))
#define yyerrok (yyerrflag=0)
#ifdef YYSTACKSIZE
#ifndef YYMAXDEPTH
#define YYMAXDEPTH YYSTACKSIZE
#endif
#else
#ifdef YYMAXDEPTH
#define YYSTACKSIZE YYMAXDEPTH
#else
#define YYSTACKSIZE 500
#define YYMAXDEPTH 500
#endif
#endif
int yydebug;
int yynerrs;
int yyerrflag;
int yychar;
short *yyssp;
YYSTYPE *yyvsp;
YYSTYPE yyval;
YYSTYPE yylval;
short yyss[YYSTACKSIZE];
YYSTYPE yyvs[YYSTACKSIZE];
#define yystacksize YYSTACKSIZE
#line 407 "parser_c.y"
void yyerror(char *s) {
    fprintf(stderr, "line %d: %s \n", yylineno, s);
}

int main(void) {
    yyparse();
    return 0;
}
#line 311 "y.tab.c"
#define YYABORT goto yyabort
#define YYACCEPT goto yyaccept
#define YYERROR goto yyerrlab
int
yyparse()
{
    register int yym, yyn, yystate;
#if YYDEBUG
    register char *yys;
    extern char *getenv();

    if (yys = getenv("YYDEBUG"))
    {
        yyn = *yys;
        if (yyn >= '0' && yyn <= '9')
            yydebug = yyn - '0';
    }
#endif

    yynerrs = 0;
    yyerrflag = 0;
    yychar = (-1);

    yyssp = yyss;
    yyvsp = yyvs;
    *yyssp = yystate = 0;

yyloop:
    if (yyn = yydefred[yystate]) goto yyreduce;
    if (yychar < 0)
    {
        if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, reading %d (%s)\n", yystate,
                    yychar, yys);
        }
#endif
    }
    if ((yyn = yysindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: state %d, shifting to state %d (%s)\n",
                    yystate, yytable[yyn],yyrule[yyn]);
#endif
        if (yyssp >= yyss + yystacksize - 1)
        {
            goto yyoverflow;
        }
        *++yyssp = yystate = yytable[yyn];
        *++yyvsp = yylval;
        yychar = (-1);
        if (yyerrflag > 0)  --yyerrflag;
        goto yyloop;
    }
    if ((yyn = yyrindex[yystate]) && (yyn += yychar) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yychar)
    {
        yyn = yytable[yyn];
        goto yyreduce;
    }
    if (yyerrflag) goto yyinrecovery;
#ifdef lint
    goto yynewerror;
#endif
yynewerror:
    yyerror("syntax error");
#ifdef lint
    goto yyerrlab;
#endif
yyerrlab:
    ++yynerrs;
yyinrecovery:
    if (yyerrflag < 3)
    {
        yyerrflag = 3;
        for (;;)
        {
            if ((yyn = yysindex[*yyssp]) && (yyn += YYERRCODE) >= 0 &&
                    yyn <= YYTABLESIZE && yycheck[yyn] == YYERRCODE)
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: state %d, error recovery shifting\
 to state %d\n", *yyssp, yytable[yyn]);
#endif
                if (yyssp >= yyss + yystacksize - 1)
                {
                    goto yyoverflow;
                }
                *++yyssp = yystate = yytable[yyn];
                *++yyvsp = yylval;
                goto yyloop;
            }
            else
            {
#if YYDEBUG
                if (yydebug)
                    printf("yydebug: error recovery discarding state %d\n",
                            *yyssp);
#endif
                if (yyssp <= yyss) goto yyabort;
                --yyssp;
                --yyvsp;
            }
        }
    }
    else
    {
        if (yychar == 0) goto yyabort;
#if YYDEBUG
        if (yydebug)
        {
            yys = 0;
            if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
            if (!yys) yys = "illegal-symbol";
            printf("yydebug: state %d, error recovery discards token %d (%s)\n",
                    yystate, yychar, yys);
        }
#endif
        yychar = (-1);
        goto yyloop;
    }
yyreduce:
#if YYDEBUG
    if (yydebug)
        printf("yydebug: state %d, reducing by rule %d (%s)\n",
                yystate, yyn, yyrule[yyn]);
#endif
    yym = yylen[yyn];
    yyval = yyvsp[1-yym];
    switch (yyn)
    {
case 1:
#line 42 "parser_c.y"
{
        yyval.node = new_node("Goal", 2, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
        /*if (check($$)) */
        {
            printf("Syntax tree:\n");
            Print(yyval.node, 0);
        }
    }
break;
case 2:
#line 55 "parser_c.y"
{
        yyval.node = new_node("MainClass", 17, yyvsp[-16].node, yyvsp[-15].node, new_node("{", 0), yyvsp[-13].node, yyvsp[-12].node,
                                       yyvsp[-11].node, yyvsp[-10].node, yyvsp[-9].node, yyvsp[-8].node, new_node("[", 0), yyvsp[-6].node,
                                       yyvsp[-5].node, yyvsp[-4].node, new_node("{", 0), yyvsp[-2].node, yyvsp[-1].node,
                                       yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 3:
#line 66 "parser_c.y"
{
        yyval.node = new_node("ClassDeclarationList", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 4:
#line 71 "parser_c.y"
{
        yyval.node = new_node("ClassDeclarationList", 2, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 5:
#line 79 "parser_c.y"
{
        yyval.node = NULL;
    }
break;
case 6:
#line 83 "parser_c.y"
{
        yyval.node = new_node("ClassDeclarations", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 7:
#line 91 "parser_c.y"
{
        yyval.node = new_node("ClassDeclaration", 7, yyvsp[-6].node, yyvsp[-5].node, yyvsp[-4].node, new_node("{", 0),
                                             yyvsp[-2].node, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 8:
#line 100 "parser_c.y"
{
        yyval.node = NULL;
    }
break;
case 9:
#line 104 "parser_c.y"
{
        yyval.node = new_node("ExtendOpt", 2, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 10:
#line 112 "parser_c.y"
{
        yyval.node = NULL;
    }
break;
case 11:
#line 116 "parser_c.y"
{
        yyval.node = new_node("VarDeclarations", 1, yyvsp[-1].node);
        yyval.node->line = yylineno;
    }
break;
case 12:
#line 124 "parser_c.y"
{
        yyval.node = new_node("VarDeclaration", 3, yyvsp[-2].node, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 13:
#line 132 "parser_c.y"
{
        yyval.node = NULL;
    }
break;
case 14:
#line 136 "parser_c.y"
{
        yyval.node = new_node("MethodDeclarations", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 15:
#line 144 "parser_c.y"
{
        yyval.node = new_node("MethodDeclarationList", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 16:
#line 149 "parser_c.y"
{
        yyval.node = new_node("MethodDeclarationList", 2, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 17:
#line 157 "parser_c.y"
{
        yyval.node = new_node("MethodDeclaration", 13, yyvsp[-12].node, yyvsp[-11].node, yyvsp[-10].node, yyvsp[-9].node,
                                               yyvsp[-8].node, yyvsp[-7].node, new_node("{", 0),
                                               yyvsp[-5].node, yyvsp[-4].node, yyvsp[-3].node, yyvsp[-2].node,
                                               yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 18:
#line 168 "parser_c.y"
{
        yyval.node = NULL;
    }
break;
case 19:
#line 172 "parser_c.y"
{
        yyval.node = new_node("TypeIdentifiers", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 20:
#line 180 "parser_c.y"
{
        yyval.node = new_node("TypeIdentifierList", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 21:
#line 185 "parser_c.y"
{
        yyval.node = new_node("TypeIdentifierList", 3, yyvsp[-2].node, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 22:
#line 193 "parser_c.y"
{
        yyval.node = new_node("TypeIdentifer", 2, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 23:
#line 201 "parser_c.y"
{
        yyval.node = NULL;
    }
break;
case 24:
#line 205 "parser_c.y"
{
        yyval.node = new_node("Statements", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 25:
#line 213 "parser_c.y"
{
        yyval.node = new_node("StatementList", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 26:
#line 218 "parser_c.y"
{
        yyval.node = new_node("StatementList", 2, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 27:
#line 226 "parser_c.y"
{
        yyval.node = new_node("Type", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 28:
#line 231 "parser_c.y"
{
        yyval.node = new_node("Type", 3, yyvsp[-2].node, new_node("[", 0), yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 29:
#line 236 "parser_c.y"
{
        yyval.node = new_node("Type", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 30:
#line 241 "parser_c.y"
{
        yyval.node = new_node("Type", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 31:
#line 249 "parser_c.y"
{
        yyval.node = new_node("Statement", 3, new_node("{", 0), yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 32:
#line 254 "parser_c.y"
{
        yyval.node = new_node("Statement", 7, yyvsp[-6].node, yyvsp[-5].node, yyvsp[-4].node, yyvsp[-3].node,
                                      yyvsp[-2].node, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 33:
#line 260 "parser_c.y"
{
        yyval.node = new_node("Statement", 5, yyvsp[-4].node, yyvsp[-3].node, yyvsp[-2].node, yyvsp[-1].node,
                                      yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 34:
#line 266 "parser_c.y"
{
        yyval.node = new_node("Statement", 5, yyvsp[-4].node, yyvsp[-3].node, yyvsp[-2].node, yyvsp[-1].node,
                                      yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 35:
#line 272 "parser_c.y"
{
        yyval.node = new_node("Statement", 4, yyvsp[-3].node, new_node("=", 0), yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 36:
#line 277 "parser_c.y"
{
        yyval.node = new_node("Statement", 7, yyvsp[-6].node, new_node("[", 0), yyvsp[-4].node, yyvsp[-3].node,
                                      new_node("=", 0), yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 37:
#line 286 "parser_c.y"
{
        yyval.node = NULL;
    }
break;
case 38:
#line 290 "parser_c.y"
{
        yyval.node = new_node("Expressions", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 39:
#line 298 "parser_c.y"
{
        yyval.node = new_node("ExpressionList", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 40:
#line 303 "parser_c.y"
{
        yyval.node = new_node("ExpressionList", 3, yyvsp[-2].node, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 41:
#line 311 "parser_c.y"
{
        yyval.node = new_node("Expression", 3, yyvsp[-2].node, new_node("$$", 0), yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 42:
#line 316 "parser_c.y"
{
        yyval.node = new_node("Expression", 3, yyvsp[-2].node, new_node("<", 0), yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 43:
#line 321 "parser_c.y"
{
        yyval.node = new_node("Expression", 3, yyvsp[-2].node, new_node("+", 0), yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 44:
#line 326 "parser_c.y"
{
        yyval.node = new_node("Expression", 3, yyvsp[-2].node, new_node("-", 0), yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 45:
#line 331 "parser_c.y"
{
        yyval.node = new_node("Expression", 3, yyvsp[-2].node, new_node("*", 0), yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 46:
#line 336 "parser_c.y"
{
        yyval.node = new_node("Expression", 4, yyvsp[-3].node, new_node("[", 0), yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 47:
#line 341 "parser_c.y"
{
        yyval.node = new_node("Expression", 3, yyvsp[-2].node, new_node(".", 0), yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 48:
#line 346 "parser_c.y"
{
        yyval.node = new_node("Expression", 6, yyvsp[-5].node, new_node(".", 0), yyvsp[-3].node,
                                       yyvsp[-2].node, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 49:
#line 352 "parser_c.y"
{
        yyval.node = new_node("Expression", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 50:
#line 357 "parser_c.y"
{
        yyval.node = new_node("Expression", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 51:
#line 362 "parser_c.y"
{
        yyval.node = new_node("Expression", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 52:
#line 367 "parser_c.y"
{
        yyval.node = new_node("Expression", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 53:
#line 372 "parser_c.y"
{
        yyval.node = new_node("Expression", 5, yyvsp[-4].node, yyvsp[-3].node, new_node("[", 0), yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 54:
#line 377 "parser_c.y"
{
        yyval.node = new_node("Expression", 4, yyvsp[-3].node, yyvsp[-2].node, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 55:
#line 382 "parser_c.y"
{
        yyval.node = new_node("Expression", 2, new_node("!", 0), yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 56:
#line 387 "parser_c.y"
{
        yyval.node = new_node("Expression", 3, yyvsp[-2].node, yyvsp[-1].node, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 57:
#line 392 "parser_c.y"
{
        yyval.node = new_node("Expression", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
case 58:
#line 400 "parser_c.y"
{
        yyval.node = new_node("Expression", 1, yyvsp[0].node);
        yyval.node->line = yylineno;
    }
break;
#line 867 "y.tab.c"
    }
    yyssp -= yym;
    yystate = *yyssp;
    yyvsp -= yym;
    yym = yylhs[yyn];
    if (yystate == 0 && yym == 0)
    {
#if YYDEBUG
        if (yydebug)
            printf("yydebug: after reduction, shifting from state 0 to\
 state %d\n", YYFINAL);
#endif
        yystate = YYFINAL;
        *++yyssp = YYFINAL;
        *++yyvsp = yyval;
        if (yychar < 0)
        {
            if ((yychar = yylex()) < 0) yychar = 0;
#if YYDEBUG
            if (yydebug)
            {
                yys = 0;
                if (yychar <= YYMAXTOKEN) yys = yyname[yychar];
                if (!yys) yys = "illegal-symbol";
                printf("yydebug: state %d, reading %d (%s)\n",
                        YYFINAL, yychar, yys);
            }
#endif
        }
        if (yychar == 0) goto yyaccept;
        goto yyloop;
    }
    if ((yyn = yygindex[yym]) && (yyn += yystate) >= 0 &&
            yyn <= YYTABLESIZE && yycheck[yyn] == yystate)
        yystate = yytable[yyn];
    else
        yystate = yydgoto[yym];
#if YYDEBUG
    if (yydebug)
        printf("yydebug: after reduction, shifting from state %d \
to state %d\n", *yyssp, yystate);
#endif
    if (yyssp >= yyss + yystacksize - 1)
    {
        goto yyoverflow;
    }
    *++yyssp = yystate;
    *++yyvsp = yyval;
    goto yyloop;
yyoverflow:
    yyerror("yacc stack overflow");
yyabort:
    return (1);
yyaccept:
    return (0);
}
