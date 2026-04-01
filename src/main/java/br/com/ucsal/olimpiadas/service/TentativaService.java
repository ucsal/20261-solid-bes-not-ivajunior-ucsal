package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;

import java.util.List;

public class TentativaService {
	private final TentativaRepository repository;
	private final CalculadorNota calculadorNota;

	public TentativaService(TentativaRepository repository, CalculadorNota calculadorNota) {
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
