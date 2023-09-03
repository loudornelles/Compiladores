JFLEX  = java -jar jflex.jar
JAVAC  = javac

# targets:

all: Parser.class

clean:
	rm -f *~ *.class Yylex.java

Parser.class: Parser.java Yylex.java
	$(JAVAC) Parser.java

Yylex.java: lexer.flex
	$(JFLEX) lexer.flex

