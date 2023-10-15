java -jar JFlex.jar -d out lexer.flex
# ./yacc.exe -tv -b $(pwd)/out/Parser.java -J parser.y
./byaccj.macos -tv -b $(pwd)/out/Parser.java -J parser.y
mv Parser.java ParserVal.java ./out
javac -d out out/*.java