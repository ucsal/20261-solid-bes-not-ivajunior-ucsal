package br.com.ucsal.olimpiadas.service;

import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;

import java.util.List;

public class TentativaService {
	private final TentativaRepository repository;

	public TentativaService(TentativaRepository repository) {
		this.repository = repository;
	}

	public Tentativa salvar(Tentativa tentativa) {
		return repository.salvar(tentativa);
	}

	public int calcularNota(Tentativa tentativa) {
		int acertos = 0;
		for (var r : tentativa.getRespostas()) {
			if (r.isCorreta())
				acertos++;
		}
		return acertos;
	}

	public List<Tentativa> listarTodas() {
		return repository.listarTodos();
	}
}
