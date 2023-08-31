JFLEX  = jflex
JAVAC  = javac

# targets:

all: JSON.class

clean:
	rm -f *~ *.class Yylex.java

JSON.class: JSON.java Yylex.java
	$(JAVAC) JSON.java

Yylex.java: JSON.flex
	$(JFLEX) JSON.flex

