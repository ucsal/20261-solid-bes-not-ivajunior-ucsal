package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.model.QuestaoMultiplaEscolha;
import br.com.ucsal.olimpiadas.repository.QuestaoRepositorio;

import java.util.List;

public class QuestaoService {
	private final QuestaoRepositorio repository;

	public QuestaoService(QuestaoRepositorio repository) {
		this.repository = repository;
	}

	public Questao cadastrar(long provaId, String enunciado, String[] alternativas, char correta) {
		var q = new QuestaoMultiplaEscolha();
		q.setProvaId(provaId);
		q.setEnunciado(enunciado);
		q.setAlternativas(alternativas);
		q.setAlternativaCorreta(correta);
		return repository.salvar(q);
	}

	public List<Questao> buscarPorProvaId(long provaId) {
		return repository.buscarPorProvaId(provaId);
	}
}
