package lex;

import java.io.FileReader;

import java.io.IOException;

public class Programa {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
	
		/*
		AQUI MIRO EL CODIGO DE MI TOKEN
		TokenType a = TokenType.COMPARADOR_MENOR;
		System.out.println(a.code());
		
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
		
		System.out.println(getColumna('x'));
		
		LectorCaracteres lc = new LectorCaracteres("/Users/waltergomez/eclipse-workspace/Compilador/src/lex/pruebas/validas/test1.txt");
		lc.leerArchivo();
		*/
		analizadorLexico();
		ListaSimbolos.imprimirLista();
		
		//System.out.println(TokenTable.getCodigo(3, 15));
	}
	
	
	public static int columna;
	
	
	
	public static void analizadorLexico() throws IOException 
	{
		int resultado; int codigo; int columna; int fila;
		if (leer() != null) 
		{
			codigo = leer().read();
			while(codigo > -1)//LEO MIENTRAS NO SEA EL FIN DEL DOCUMENTO
			{
				char c = (char) codigo;//CASTEO EL CODIGO A CHAR, PARA PODER TRABAJARLO
				columna = getColumna(c);
				fila = AF.searchFila(columna);
				resultado = Function_table.ejecutarFuncion(fila, columna, c);
				if(resultado == 2) esPalabra();//CON EL CODIGO 2 QUIERE DECIR QUE UNA PALABRA SE FORMO
				else if (resultado == 3) esNumero();//SI EL RESULTADO ES 3 QUIERE DECIR QUE SE FORMO UN NUMERO
				else if (columna>1 && columna<17) //SI TODO LO ANTERIOR NO SE ENCUADRA QUIERE DECIR QUE ES UN SIMBOLO
				{
					if(TokenTable.getCodigo(fila, columna)>100)//SI EL SIMBOLO ES MAYOR A 100 TENGO UN TOKEN PARA MOSTRAR
					{
						System.out.println(TokenTable.getCodigo(fila, columna));
					}
				}
			}
		}
	}
	
	//DEPENDIENDO DEL CARACTER VOY A DEVOLVER UN NUMERO DE COLUMNA
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
	
	public static FileReader leer() {
		//LEO EL ARCHIVO
		try (FileReader Lector = new FileReader("/Users/waltergomez/eclipse-workspace/Compilador/src/lex/pruebas/validas/test1.txt")){
			return Lector;
		}catch(IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
			return null;
		}
	}
	
	public static void esPalabra() {
		String palabra;
		//TRAIGO EL CONTENIDO DE LA PALABRA FORMADA
		palabra = Function_table.getValor();
		if(Keywords.esReservada(palabra)) 
		{
			//VERIFICO QUE KEYWORDS ES..
			esKeywords(palabra);
		}
		else
		{
			//SI NO ES UNA KEYWORDS ENTONCES ES UN IDENTIFICADOR
			ListaSimbolos.tokens.add(new Simbolo(TokenType.IDENTIFICADOR.code(),TokenType.IDENTIFICADOR.name(),palabra,1));
			System.out.println(TokenType.IDENTIFICADOR.code());
			
		}
	}
	
	public static void esNumero() {
		String palabra;
		palabra = Function_table.getValor();
		ListaSimbolos.tokens.add(new Simbolo(TokenType.NUMERO.code(),("_"+ palabra),palabra,1));
		System.out.println(TokenType.NUMERO.code());
	}
	//SABIENDO QUE ES UNA KEYWORD BUSCO SEGUN CUAL SEA SU CODIGO
	public static void esKeywords(String palabra) 
	{
		switch(palabra)
		{
		case "if":
			System.out.println(Keywords.IF.code());
			break;
		case "else":
			System.out.println(Keywords.ELSE.code());
			break;
		case "while":
			System.out.println(Keywords.WHILE.code());
			break;
		case "visualizador":
			System.out.println(Keywords.VISUALIZADOR.code());
			break;
		case "and":
			System.out.println(Keywords.AND.code());
			break;
		case "or":
			System.out.println(Keywords.OR.code());
			break;
		case "int":
			System.out.println(Keywords.INT.code());
			break;
		}
	}
	
}
