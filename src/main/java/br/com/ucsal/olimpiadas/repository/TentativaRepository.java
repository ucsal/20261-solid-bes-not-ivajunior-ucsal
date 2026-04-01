package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Tentativa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TentativaRepository implements Repositorio<Tentativa> {
	private long proximoId = 1;
	private final List<Tentativa> dados = new ArrayList<>();

	public Tentativa salvar(Tentativa t) {
		t.setId(proximoId++);
		dados.add(t);
		return t;
	}

	public List<Tentativa> listarTodos() {
		return Collections.unmodifiableList(dados);
	}

	@Override
	public boolean existeId(long id) {
		return dados.stream().anyMatch(t -> t.getId() == id);
	}
}
