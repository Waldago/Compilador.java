package lex;

import java.util.ArrayList;
import java.util.List;

public class ListaTokens {

	public static List <Token> tokens = new ArrayList<>();

	public static void imprimirLista() {
		for (Token t: tokens) {
			System.out.println("codigo: " + t.getCodigo() +
					" tipo : " + t.getTipo() +
					" valor: " + t.getValor() +
					" posicion: " + t.getPosicion());
		}
	}
	
	
}
