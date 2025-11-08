package parser;
import java_cup.runtime.Symbol;
import lex.Lexico;

public class Sintactico {

	@SuppressWarnings("deprecation")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Lexico prog = new Lexico();
		Lexico.analizadorLexico();
		prog.imprimirLista();
		Parser p = new Parser(prog);
		Symbol result = p.parse();
		System.out.println("Análisis terminado.");
	    System.out.println("Resultado: " + result.value); // si tu gramática devuelve algo
	}
}
