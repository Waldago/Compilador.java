package lex;

public class Function_table {
	/*
	 * 101 = create_var()
	 * 102 = add_char()
	 * 103 = create_int()
	 * 104 = add_num()
	 * 105 = finish_var()
	 * 106 = finish_int()
	 * 107 = do_nothing()
	*/
	private static int [][] matrizDeFunction = {
	/*Estados	/L	/E  /{  /}  /(  /)  /;  /-  /+  //  /*  /<  />  /!  /=  /b  /eof */
	/*0*/		{101,103,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*1*/		{102,102,105,105,105,105,105,105,105,105,105,105,105,105,105,105,105},
	/*2*/		{106,104,106,106,106,106,106,106,106,106,106,106,106,106,106,106,106},
	/*3*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*4*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*5*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*6*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*7*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*8*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*9*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*10*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*11*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*12*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*13*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*14*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*15*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*16*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*17*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*18*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*19*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*20*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	/*21*/		{107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107,107},
	};
	
	public static String ID= "";
	
	public static String number;
	
	public static int contador = 0;
	
	public static int long_max = 30;
	
	public static String valor;
	
	//TENDRIA QUE HACER UN METODO QUE SEGUN EL NUMERO DE FILA Y COLUMNA QUE VENGAN CON EL CHAR, QUE SEGUN EL NUMERO
	//EJECUTE LA FUNCION QUE DEBA
	public static int ejecutarFuncion(int f, int c, char a) {
		int funcion = matrizDeFunction[f][c];
		int code;
		switch (funcion) {
		case 101:
			code = create_var(a);
			return code;
			//break;
		case 102:
			code = add_char(a);
			return code;
			//break;
		case 103:
			code = create_int(a);
			return code;
			//break;
		case 104:
			code = add_number(a);
			return code;
			//break;
		case 105:
			code = finish_var();
			return code;
			//break;
		case 106:
			code = finish_int();
			return code;
			//break;
		case 107:
			code = do_nothing();
			return code;
			//break;
		}
		return -1;
	}
	
	
	
	public static int create_var(char a) {
		ID = "";
		ID = ID + a;
		contador++;
		return 0;
	}
	
	public static int add_char(char a) {
		if (ID.length()<long_max) {
			ID = ID + a;
			contador++;
			return 0;
		}else {
			System.out.println("se supero el maximo de caracteres");
			return -1;
		}
	}
	
	public static int create_int(char a) {
		number = "";
		number = number + a;
		contador++;
		return 0;
	}
	
	public static int add_number(char a) {
		if(number.length()<long_max) {
			number = number + a;
			return 0;
		}else {
			System.out.println("se supero el maximo de caracteres");
			return -1;
		}
		
	}
	
	public static int finish_var() {
		//System.out.println("Se formo la palabra: " + ID);
		valor = ID;
		contador = 0;
		return 2;
	}
	
	public static int finish_int() {
		//System.out.println("Se formo el numero: "+ number);
		valor = number;
		contador = 0;
		return 2;
	}
	
	public static int do_nothing() {
		return 0;
	}
	
	public static String getValor() {
		return valor;
	}
}
