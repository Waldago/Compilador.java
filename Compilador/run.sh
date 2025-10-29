#!/bin/bash

: <<'COMENTARIO'
echo "== Generando parser =="
java -jar lib/java-cup-11b.jar \
  -package parser -parser Parser -symbols sym \
  -destdir src/parser src/parser/grammar.cup || exit 1
COMENTARIO

echo "== Compilando =="
mkdir -p out
javac -cp "lib/java-cup-11b.jar:lib/java-cup-11b-runtime.jar" \
  -d out src/parser/sym.java src/parser/Parser.java src/parser/TestFakeScanner.java || exit 1

echo "== Ejecutando =="
java -cp "out:lib/java-cup-11b.jar:lib/java-cup-11b-runtime.jar" parser.TestFakeScanner
