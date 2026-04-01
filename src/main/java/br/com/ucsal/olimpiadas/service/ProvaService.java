package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.repository.Repositorio;

import java.util.List;

public class ProvaService {
	private final Repositorio<Prova> repository;

	public ProvaService(Repositorio<Prova> repository) {
		this.repository = repository;
	}

	public Prova cadastrar(String titulo) {
		var prova = new Prova();
		prova.setTitulo(titulo);
		return repository.salvar(prova);
	}

	public List<Prova> listarTodos() {
		return repository.listarTodos();
	}
}
