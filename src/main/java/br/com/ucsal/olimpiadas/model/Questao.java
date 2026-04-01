package br.com.ucsal.olimpiadas.model;

public abstract class Questao {

	private long id;
	private long provaId;
	private String enunciado;
	private String fenInicial;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getProvaId() {
		return provaId;
	}

	public void setProvaId(long provaId) {
		this.provaId = provaId;
	}

	public String getEnunciado() {
		return enunciado;
	}

	public void setEnunciado(String enunciado) {
		this.enunciado = enunciado;
	}

	public String getFenInicial() {
		return fenInicial;
	}

	public void setFenInicial(String fenInicial) {
		this.fenInicial = fenInicial;
	}

	/**
	 * Verifica se a resposta marcada está correta.
	 */
	public abstract boolean isRespostaCorreta(char marcada);

	/**
	 * Retorna as alternativas da questão para exibição.
	 */
	public abstract String[] getAlternativas();

	/**
	 * Normaliza um caractere de alternativa (A–E).
	 */
	public static char normalizar(char c) {
		char up = Character.toUpperCase(c);
		if (up < 'A' || up > 'E') {
			throw new IllegalArgumentException("Alternativa deve estar entre A e E.");
		}
		return up;
	}
}
