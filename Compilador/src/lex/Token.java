package lex;

public class Token {

	private int codigo;
	private String tipo;
	private String valor;
	private int posicion;
	
	public Token (int codigo, String tipo, String valor, int posicion ) {
		this.codigo = codigo;
		this.tipo = tipo;
		this.valor = valor;
		this.posicion = posicion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public int getPosicion() {
		return posicion;
	}

	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	
	
}
