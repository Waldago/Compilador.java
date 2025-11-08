package lex;

public enum TokenType {
	EOF(0),
	ERROR(-1),
	IDENT(256),
	NUMBER(257),
	ASSIGN(259),
	SEMI(260),
	LBRACE(264),
	RBRACE(265),
	LPAREN(266),
	RPAREN(267),
	PLUS(269),
	MINUS(270),
	STAR(271),
	SLASH(272),
	EQEQ(273),
	NEQ(274),
	LT(275),
	GT(276),
	LTE(277),
	GTE(278),
	NOT(281);
	
	private final int code;
	
	TokenType(int code) 
	{ 
		this.code = code; 
	}
	
	public int code() 
	{
		return code;
	}
    // 🔹 Método estático para obtener el TokenType a partir del código
    public static TokenType fromCode(int code) {
        for (TokenType t : TokenType.values()) {
            if (t.code == code) {
                return t;
            }
        }
        return null; // o ERROR si preferís no devolver null
    }
    // 🔹 Método que devuelve solo el nombre como String
    public static String nameFromCode(int code) {
        TokenType t = fromCode(code);
        return (t != null) ? t.name() : "DESCONOCIDO";
    }
	
}
