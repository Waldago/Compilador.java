package parser;

import java_cup.runtime.Symbol;
import java_cup.runtime.Scanner;

public class TestFakeScanner {

    static class FakeScanner implements Scanner {
        private final Symbol[] s; private int i=0;
        FakeScanner(Symbol... s){ this.s=s; }
        @Override public Symbol next_token(){ return (i<s.length)? s[i++] : new Symbol(sym.EOF); }
    }

    public static void main(String[] args) throws Exception {

        // PROG_A: SOLO DECLARACIONES  ->  programa ::= lista_declaraciones
        // int x = 5;
        Symbol[] PROG_A = {
            new Symbol(sym.INT_KW), new Symbol(sym.IDENT,"x"), new Symbol(sym.ASSIGN),
            new Symbol(sym.NUMBER,5), new Symbol(sym.SEMI),
            new Symbol(sym.EOF)
        };

        // PROG_B: DECLARACIONES + UNA SENTENCIA -> programa ::= lista_declaraciones lista_sentencias
        // int x = 5;  visualizar(x);
        Symbol[] PROG_B = {
            new Symbol(sym.INT_KW), new Symbol(sym.IDENT,"x"), new Symbol(sym.ASSIGN),
            new Symbol(sym.NUMBER,5), new Symbol(sym.SEMI),

            new Symbol(sym.VISUALIZAR), new Symbol(sym.LPAREN), new Symbol(sym.IDENT,"x"),
            new Symbol(sym.RPAREN), new Symbol(sym.SEMI),

            new Symbol(sym.EOF)
        };

        // PROG_C: SOLO SENTENCIAS -> programa ::= lista_sentencias
        // while (x > 0) { INT x = 1; visualizar(x); }
        Symbol[] PROG_C = {
            new Symbol(sym.WHILE),
            new Symbol(sym.LPAREN), new Symbol(sym.IDENT,"x"), new Symbol(sym.GT), new Symbol(sym.NUMBER,0), new Symbol(sym.RPAREN),

            new Symbol(sym.LBRACE),
            new Symbol(sym.INT_KW), new Symbol(sym.IDENT,"x"), new Symbol(sym.ASSIGN), new Symbol(sym.NUMBER,1), new Symbol(sym.SEMI),
                    //new Symbol(sym.IDENT,"x"), new Symbol(sym.MINUS), new Symbol(sym.NUMBER,1), new Symbol(sym.SEMI),
                new Symbol(sym.VISUALIZAR), new Symbol(sym.LPAREN), new Symbol(sym.IDENT,"x"), new Symbol(sym.RPAREN), new Symbol(sym.SEMI),
            new Symbol(sym.RBRACE),

            new Symbol(sym.EOF)
        };

        // Elegí cuál probar:
        Symbol[] programa = PROG_C;

        Parser p = new Parser(new FakeScanner(programa));
        p.parse();
        System.out.println("✅ Parse OK con FakeScanner.");
    }
}
