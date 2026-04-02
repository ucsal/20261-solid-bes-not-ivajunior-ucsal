package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Prova;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProvaRepositoryMemoria implements Repositorio<Prova> {
	private long proximoId = 1;
	private final List<Prova> dados = new ArrayList<>();

	public Prova salvar(Prova p) {
		p.setId(proximoId++);
		dados.add(p);
		return p;
	}

	public List<Prova> listarTodos() {
		return Collections.unmodifiableList(dados);
	}

	public boolean existeId(long id) {
		return dados.stream().anyMatch(p -> p.getId() == id);
	}
}
