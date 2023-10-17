java -jar ./bin/JFlex.jar -d ./out src/lexer.flex
# ./bin/yacc.exe -tv -b $(pwd)/out/Parser.java -J src/parser.y
./bin/byaccj.macos -tv -b $(pwd)/out/Parser.java -J src/parser.y
mv Parser.java ParserVal.java ./out
javac -d out out/*.java