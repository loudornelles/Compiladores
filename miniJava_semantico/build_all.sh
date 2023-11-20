dir=$(CDPATH= cd -- "$(dirname -- "$0")" && pwd)

# cd into root of project
cd "$dir"

java -jar ./bin/jflex.jar -d ./out src/lexer.flex
./bin/yacc.exe -tv -b $(pwd)/out/Parser.java -J src/parser.y
#  ./bin/byaccj.macos -tv -b $(pwd)/out/Parser.java -J src/parser.y
javac -d out -Xlint:unchecked src/**/*.java out/*.java