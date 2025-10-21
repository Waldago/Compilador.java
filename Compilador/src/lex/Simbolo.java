package lex;

public class Simbolo {
/*
 * int x = 5
 * int = solo envio codigo
 * leo x 
 * codigo = 256
 * en la tabla de simbolos agrego el simbolo x
 * entonces almaceno de x el nombre del id
 * cuando leo el 5 
 * almaceno el tipo : int nombre de la var : _5 y su valor : 5.
 * 
 */
	private int codigo;
	private String tipo;
	private String valor;
	private int posicion;
	// tipo String/numerico, nombre de la variable = "caca" ,
	public Simbolo (int codigo, String tipo, String valor, int posicion ) {
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
