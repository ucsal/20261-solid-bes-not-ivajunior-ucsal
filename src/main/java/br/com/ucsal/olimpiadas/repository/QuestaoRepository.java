package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Questao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestaoRepository implements QuestaoRepositorio {
	private long proximoId = 1;
	private final List<Questao> dados = new ArrayList<>();

	public Questao salvar(Questao q) {
		q.setId(proximoId++);
		dados.add(q);
		return q;
	}

	public List<Questao> listarTodos() {
		return Collections.unmodifiableList(dados);
	}

	public List<Questao> buscarPorProvaId(long provaId) {
		return dados.stream().filter(q -> q.getProvaId() == provaId).toList();
	}

	public boolean existeId(long id) {
		return dados.stream().anyMatch(q -> q.getId() == id);
	}
}
