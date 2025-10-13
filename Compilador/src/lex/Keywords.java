package lex;

public enum Keywords {
	IF(261),
	ELSE(262),
	WHILE(263),
	VISUALIZADOR(268),
	AND(279),
	OR(280),
	INT(258);
	
	private final int code;
	
	Keywords(int code) 
	{ 
		this.code = code; 
	}
	
	public int code() 
	{
		return code;
	}
	
	public static boolean esReservada(String palabra) {
		try {
			//AQUI BUSCO LA CONSTANTE DE MI ENUM QUE COINCIDA
			Keywords.valueOf(palabra.toUpperCase());
			return true;
		}catch(IllegalArgumentException e) {
			return false;
		}
	}
}
