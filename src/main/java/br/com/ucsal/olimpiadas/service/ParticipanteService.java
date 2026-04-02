package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.repository.Repositorio;

import java.util.List;

public class ParticipanteService {
	private final Repositorio<Participante> repository;

	public ParticipanteService(Repositorio<Participante> repository) {
		this.repository = repository;
	}

	public Participante cadastrar(String nome, String email) {
		var p = new Participante();
		p.setNome(nome);
		p.setEmail(email);
		return repository.salvar(p);
	}

	public List<Participante> listarTodos() {
		return repository.listarTodos();
	}
}
