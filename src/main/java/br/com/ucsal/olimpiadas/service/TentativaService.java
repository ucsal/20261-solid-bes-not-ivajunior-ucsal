package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.Repositorio;

import java.util.List;

public class TentativaService {
	private final Repositorio<Tentativa> repository;
	private final CalculadorNota calculadorNota;

	public TentativaService(Repositorio<Tentativa> repository, CalculadorNota calculadorNota) {
		this.repository = repository;
		this.calculadorNota = calculadorNota;
	}

	public Tentativa salvar(Tentativa tentativa) {
		return repository.salvar(tentativa);
	}

	public int calcularNota(Tentativa tentativa) {
		return calculadorNota.calcular(tentativa);
	}

	public List<Tentativa> listarTodas() {
		return repository.listarTodos();
	}
}
