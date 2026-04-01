package br.com.ucsal.olimpiadas.repository;

import br.com.ucsal.olimpiadas.model.Participante;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ParticipanteRepositoryMemoria implements Repositorio<Participante> {
	private long proximoId = 1;
	private final List<Participante> dados = new ArrayList<>();

	public Participante salvar(Participante p) {
		p.setId(proximoId++);
		dados.add(p);
		return p;
	}

	public List<Participante> listarTodos() {
		return Collections.unmodifiableList(dados);
	}

	public boolean existeId(long id) {
		return dados.stream().anyMatch(p -> p.getId() == id);
	}
}
