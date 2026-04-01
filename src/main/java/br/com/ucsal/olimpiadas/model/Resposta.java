package br.com.ucsal.olimpiadas.model;

public class Resposta {

	private long questaoId;
	private String alternativaMarcada;
	private boolean correta;

	public long getQuestaoId() {
		return questaoId;
	}

	public void setQuestaoId(long questaoId) {
		this.questaoId = questaoId;
	}

	public String getAlternativaMarcada() {
		return alternativaMarcada;
	}

	public void setAlternativaMarcada(String alternativaMarcada) {
		this.alternativaMarcada = alternativaMarcada;
	}

	public boolean isCorreta() {
		return correta;
	}

	public void setCorreta(boolean correta) {
		this.correta = correta;
	}

}
