package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Questao;

import java.util.List;

public interface QuestaoRepositorio extends Repositorio<Questao> {
	List<Questao> buscarPorProvaId(long provaId);
}
