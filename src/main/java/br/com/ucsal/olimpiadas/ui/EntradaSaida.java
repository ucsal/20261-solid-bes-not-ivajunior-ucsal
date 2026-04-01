package br.com.ucsal.olimpiadas.ui;

public interface EntradaSaida {
	String lerLinha();
	void print(String msg);
	void println(String msg);
	void printf(String fmt, Object... args);
}
