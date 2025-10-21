package lex;

import java.util.ArrayList;
import java.util.List;

public class ListaSimbolos {

	public static List <Simbolo> tokens = new ArrayList<>();

	public static void imprimirLista() {
		for (Simbolo t: tokens) {
			System.out.println("codigo: " + t.getCodigo() +
					" tipo : " + t.getTipo() +
					" valor: " + t.getValor() +
					" posicion: " + t.getPosicion());
		}
	}
	
	
}
