package lex;

import java.io.FileReader;
import java.io.IOException;
import java.io.PushbackReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java_cup.runtime.Scanner;
import java_cup.runtime.Symbol;
import parser.sym;

public class Lexico implements Scanner{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		/*
		AQUI MIRO EL CODIGO DE MI TOKENß
		TokenType a = TokenType.COMPARADOR_MENOR;
		System.out.println(a.name());
		
		//ACA COMPRUEBO SI LA PALABRA QUE RECIBI ES UNA PALABRA RESERVADA O NO
		System.out.println(Keywords.esReservada("if"));
		
		//COMPRUEBO QUE LAS FUNCIONES PARA ID HAGAN LO SUYO
		Function_table.create_var('a');
		Function_table.add_char('r');
		Function_table.add_char('b');
		Function_table.add_char('o');
		Function_table.add_char('l');
		Function_table.finish_var();
		
		//COMPRUEBO QUE LAS FUNCIONES PARA NUMBER HAGAN LO SUYO
		Function_table.create_int('2');
		Function_table.add_number('0');
		Function_table.add_number('0');
		Function_table.finish_int();
		
		//VEO QUE FUNCIONE LA FUNCION PRINCIPAL DE FUNCTION_TABLE 
		Function_table.ejecutarFuncion(0, 0, 'a');
		Function_table.ejecutarFuncion(1, 1, 'r');
		Function_table.ejecutarFuncion(1, 1, 'b');
		Function_table.ejecutarFuncion(1, 1, 'o');
		Function_table.ejecutarFuncion(1, 1, 'l');
		Function_table.ejecutarFuncion(1, 3, ' ');
		
		//HAGO LA PRUEBA DEL AUTOMATA FINITO QUE ME HAGA LOS SALTOS DE ESTADO
		System.out.println(AF.searchFila(0));
		AF.verEstadoActual();
		System.out.println(AF.searchFila(0));
		AF.verEstadoActual();
		System.out.println(AF.searchFila(1));
		AF.verEstadoActual();
		System.out.println(AF.searchFila(0));
		AF.verEstadoActual();
		System.out.println(AF.searchFila(4));
		AF.verEstadoActual();
		*/
		analizadorLexico();
		Lexico l = new Lexico();
		l.imprimirLista();
	}
	
	
	public static int columna;
	
	private static ArrayList<Symbol> s = new ArrayList<>();
	
	private static int posicion=0;
	
	public static void analizadorLexico() {
		int resultado; int codigo; int columna; int fila;
		//LEO EL ARCHIVO
		try(PushbackReader Lector = new PushbackReader(new FileReader("/Users/waltergomez/eclipse-workspace/Compilador/src/lex/pruebas/validas/test1.txt")))
		{
			while((codigo = Lector.read()) > -1) //Si lee correctamente un carácter, te devuelve su código numérico (por ejemplo, 'A' → 65). Si llega al final del archivo, devuelve -1.
			{
				char c = (char) codigo;//CASTEO EL CODIGO A CHAR, PARA PODER TRABAJARLO
				System.out.println(c);
				columna = getColumna(c);//DEPENDIENDO DEL CARACTER VOY A DEVOLVER UN NUMERO DE COLUMNA
				fila = AF.searchFila(columna);//CON LA COLUMNA Y EL ESTADO DEL AF VOY A SABER LOS SALTOS QUE DEBA HACER EN LA TABLA
				resultado = Function_table.ejecutarFuncion(fila, columna, c);
				if(resultado == 2)
				{	
					esPalabra();//CON EL CODIGO 2 ENTIENDO QUE SE FORMO UNA PALABRA
					Lector.unread(codigo);
				}
				else if (resultado == 3) 
				{	
					esNumero();//SI EL RESULTADO ES 3 QUIERE DECIR QUE SE FORMO UN NUMERO
					Lector.unread(codigo);
				}
				else if (columna>1 && columna<17)//SI TODO LO ANTERIOR NO SE ENCUADRA QUIERE DECIR QUE ES UN SIMBOLO
				{
					if(TokenTable.getCodigo(fila, columna)>100) 
					{
						//System.out.println(TokenType.nameFromCode(TokenTable.getCodigo(fila, columna)));//SI EL SIMBOLO ES MAYOR A 100 TENGO UN TOKEN PARA MOSTRAR
						String nombre = TokenType.nameFromCode(TokenTable.getCodigo(fila, columna));
						try {
						    // busca en la clase sym un campo que se llame igual que la variable nombre
						    Field f = sym.class.getField(nombre);
						    int code = f.getInt(null); // null porque es static
						    s.add(new Symbol(code));
						    Lector.unread(codigo);
						} catch (Exception e) {
						    // si no existe ese nombre en sym, agregamos un error simbólico
						    s.add(new Symbol(sym.error, "Token no encontrado: " + nombre));
						}
					}
				}
			}
		}
		catch(IOException e) 
		{
			System.err.println("Error al leer el archivo: " + e.getMessage());
		}
	}
	
	@Override
	public Symbol next_token()
	{ 
		return (posicion < s.size()) ? s.get(posicion++) : new Symbol(sym.EOF);
    }
	
	public static int getColumna(char a) 
	{
		//EVALUA SI a ES UNA LETRA
		if(Character.isLetter(a))return 0;
		//EVALUA SI a ES UN NUMERO
		if(Character.isDigit(a)) return 1;
		//EVALUA SI a ES UN ESPACIO EN BLANCO, UN SALTO DE LINEA O UNA TABULACION
		if(Character.isWhitespace(a)) return 15;
		if(a == '{') return 2;
		if(a == '}') return 3;
		if(a == '(') return 4;
		if(a == ')') return 5;
		if(a == ';') return 6;
		if(a == '-') return 7;
		if(a == '+') return 8;
		if(a == '/') return 9;
		if(a == '*') return 10;
		if(a == '<') return 11;
		if(a == '>') return 12;
		if(a == '!') return 13;
		if(a == '=') return 14;
		//si no es nada de esto sera el final del documento
		else return 16;
		
	}
	
	public void imprimirLista() {
		for (Symbol simbolo : s) {
		    System.out.println(simbolo);
		}
	}
	//SABIENDO QUE ES UNA KEYWORD BUSCO SEGUN CUAL SEA SU CODIGO
	public static void esKeywords(String palabra) 
	{
		switch(palabra)
		{
		case "if":
			s.add(new Symbol(sym.IF));
			break;
		case "else":
			s.add(new Symbol(sym.ELSE));
			break;
		case "while":
			s.add(new Symbol(sym.WHILE));
			break;
		case "visualizar":
			s.add(new Symbol(sym.VISUALIZAR));
			break;
		case "and":
			s.add(new Symbol(sym.AND));
			break;
		case "or":
			s.add(new Symbol(sym.OR));
			break;
		case "int":
			s.add(new Symbol(sym.INT_KW));
			break;
		}
	}
	
	public static void esPalabra() {
		String palabra; 
		//TRAIGO EL CONTENIDO DE LA PALABRA FORMADA
		palabra = Function_table.getValor();
		if(Keywords.esReservada(palabra)) esKeywords(palabra);//VERIFICO QUE KEYWORDS ES y LO ENVIO.
		else//SI NO ES UNA KEYWORDS ENTONCES ES UN IDENTIFICADOR
		{
			ListaSimbolos.tokens.add(new Simbolo(TokenType.IDENT.code(),TokenType.IDENT.name(),palabra,1));
			s.add(new Symbol(sym.IDENT, palabra));
			//System.out.println(TokenType.IDENT.name());
			
		}
	}
	
	public static void esNumero() {
		String palabra; 
		palabra = Function_table.getValor();
		ListaSimbolos.tokens.add(new Simbolo(TokenType.NUMBER.code(),("_"+ palabra),palabra,1));
		s.add(new Symbol(sym.NUMBER, Integer.parseInt(palabra)));
		//System.out.println(TokenType.NUMBER.name());
	}
}
