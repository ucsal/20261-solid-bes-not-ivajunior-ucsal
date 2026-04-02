package br.com.ucsal.olimpiadas.model;

import java.util.Arrays;

public class QuestaoMultiplaEscolha extends Questao {

	private String[] alternativas = new String[5];
	private char alternativaCorreta;

	@Override
	public String[] getAlternativas() {
		return alternativas;
	}

	public void setAlternativas(String[] alternativas) {
		if (alternativas == null || alternativas.length != 5) {
			throw new IllegalArgumentException("A questão deve possuir exatamente 5 alternativas.");
		}
		this.alternativas = Arrays.copyOf(alternativas, 5);
	}

	public char getAlternativaCorreta() {
		return alternativaCorreta;
	}

	public void setAlternativaCorreta(char alternativaCorreta) {
		this.alternativaCorreta = normalizar(alternativaCorreta);
	}

	@Override
	public boolean isRespostaCorreta(char marcada) {
		return normalizar(marcada) == alternativaCorreta;
	}
}
