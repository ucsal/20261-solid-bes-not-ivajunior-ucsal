package br.com.ucsal.olimpiadas.model;

public class Prova {

	private long id;
	private String titulo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		if (titulo == null || titulo.isBlank()) {
			throw new IllegalArgumentException("título inválido");
		}
		this.titulo = titulo;
	}

}
