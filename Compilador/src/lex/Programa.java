package lex;

import java.io.FileReader;
import java.io.IOException;

public class Programa {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		//AQUI MIRO EL CODIGO DE MI TOKEN
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
		leerArchivo();
	}
	
	
	public static int columna;
	
	public static int getColumna(char a) {
		//EVALUA SI a ES UNA LETRA
		if(Character.isLetter(a)) {
			return 0;
		};
		//EVALUA SI a ES UN NUMERO
		if(Character.isDigit(a)){
			return 1;
		};
		//EVALUA SI a ES UN ESPACIO EN BLANCO, UN SALTO DE LINEA O UNA TABULACION
		if(Character.isWhitespace(a)) {
			return 15;
		}
		if(a == '{') {
			ListaTokens.tokens.add(new Token(TokenType.LLAVE_ABRE.code(),TokenType.LLAVE_ABRE.name(),Character.toString(a) ,1));
			return 2;
		}
		if(a == '}') {
			ListaTokens.tokens.add(new Token(TokenType.LLAVE_CIERRA.code(),TokenType.LLAVE_CIERRA.name(),Character.toString(a),1));
			return 3;
			}
		if(a == '(') {
			ListaTokens.tokens.add(new Token(TokenType.PAR_PARENTESIS_CIERRA.code(),TokenType.PAR_PARENTESIS_CIERRA.name(),Character.toString(a),1));
			return 4;
		}
		if(a == ')') {
			ListaTokens.tokens.add(new Token(TokenType.PAR_PARENTESIS_CIERRA.code(),TokenType.PAR_PARENTESIS_CIERRA.name(),Character.toString(a),1));
			return 5;
		}
		if(a == ';') {
			ListaTokens.tokens.add(new Token(TokenType.FIN_DE_LINEA.code(),TokenType.FIN_DE_LINEA.name(),Character.toString(a),1));
			return 6;
		}
		if(a == '-') {
			ListaTokens.tokens.add(new Token(TokenType.OPERADOR_RESTA.code(),TokenType.OPERADOR_RESTA.name(),Character.toString(a),1));
			return 7;
		}
		if(a == '+') {
			ListaTokens.tokens.add(new Token(TokenType.OPERADOR_SUMA.code(),TokenType.OPERADOR_SUMA.name(),Character.toString(a),1));
			return 8;
		}
		if(a == '/') return 9;
		if(a == '*') {
			ListaTokens.tokens.add(new Token(TokenType.OPERADOR_MULTIPLICACION.code(),TokenType.OPERADOR_MULTIPLICACION.name(),Character.toString(a),1));
			return 10;
		}
		if(a == '<') return 11;
		if(a == '>') return 12;
		if(a == '!') return 13;
		if(a == '=') return 14;
		//si no es nada de esto sera el final del documento
		else return 16;
		
		}
	
	public static void leerArchivo() {
		String palabra;
		int resultado;
		int codigo;
		int columna;
		int fila;
		//LEO EL ARCHIVO
		try(FileReader Lector = new FileReader("/Users/waltergomez/eclipse-workspace/Compilador/src/lex/pruebas/validas/test1.txt")){
			while((codigo = Lector.read()) > -1) {
				char c = (char) codigo;
				columna = getColumna(c);
				fila = AF.searchFila(columna);
				resultado = Function_table.ejecutarFuncion(fila, columna, c);
				if(resultado == 2) {
					palabra = Function_table.getValor();
					if(Keywords.esReservada(palabra)) {
						palabra = palabra.toUpperCase();
						switch(palabra){
							case "IF":
								ListaTokens.tokens.add(new Token(Keywords.IF.code(),palabra,palabra,1));
								break;
							case "ELSE":
								ListaTokens.tokens.add(new Token(Keywords.ELSE.code(),palabra,palabra,1));
								break;
							case "WHILE":
								ListaTokens.tokens.add(new Token(Keywords.WHILE.code(),palabra,palabra,1));
								break;
							case "VISUALIZADOR":
								ListaTokens.tokens.add(new Token(Keywords.VISUALIZADOR.code(),palabra,palabra,1));
								break;
							case "AND":
								ListaTokens.tokens.add(new Token(Keywords.AND.code(),palabra,palabra,1));
								break;
							case "OR":
								ListaTokens.tokens.add(new Token(Keywords.OR.code(),palabra,palabra,1));
								break;
							case "INT":
								ListaTokens.tokens.add(new Token(Keywords.INT.code(),palabra,palabra,1));
								break;
						}
					}else{
						palabra = Function_table.getValor();
						ListaTokens.tokens.add(new Token(TokenType.IDENTIFICADOR.code(),TokenType.IDENTIFICADOR.name(),palabra,1));
					}
				}else if (resultado == 3) {
					palabra = Function_table.getValor();
					ListaTokens.tokens.add(new Token(TokenType.NUMERO.code(),TokenType.NUMERO.name(),palabra,1));
				}
				//System.out.println("El caracter es: " + c + " Su columna es: " + getColumna(c)+ " su fila es: "+ fila);
			}
			ListaTokens.imprimirLista();
		}catch(IOException e) {
			System.err.println("Error al leer el archivo: " + e.getMessage());
		}
	}
	/*
	 * METODO QUE HAGA EL ANALISIS LEXICO
	 * LEO CHAR
	 * OBTENGO LA COLUMNA
	 * LUEGO EN AF OBTENGO LA FILA
	 * Y AHI AUTORIZO A HACER LA FUNCION CORRESPONDIENTE
	 * UNA VEZ QUE LLEGUE A ESTADO FINAL
	 * ANTES DE GUARDAR EL TOKEN TENGO QUE HACER LA VERIFICACION SI ES UNA KEYWORDS
	 * EN CASO DE NO SERLO LO GUARDARE COMO UN  ID CON EL VALOR DE LA PALABRA FORMADA
	 * EN CASO DE SER LA PALABRA RESERVADA GUARDARE EL TOKEN CORRESPONDIENTE A ESA PALABRA
	 * UNA VEZ ECHO ESTO EVALUARE SI EL DOCUMENTO CONTINUA O NO 
	 */
	
	
	
	/*
	 * NECESITO UNA CLASE TOKEN DONDE GUARDE LOS SIGUIENTES ATRIBUTOS
	 * 	*CODIGO
	 * 	*NOMBRE
	 *  *VALOR
	 *  *POSICION
	 * Y LUEGO TODO ESTO SETEARLO CON LAS DEMAS CLASES
	 * NECESITAMOS UNA CLASE QUE GUARDE LA LISTA DE TOKENS
	 * 
	 */
	
	
}
