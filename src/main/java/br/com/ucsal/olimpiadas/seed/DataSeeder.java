package br.com.ucsal.olimpiadas.seed;

import br.com.ucsal.olimpiadas.model.Prova;
import br.com.ucsal.olimpiadas.model.QuestaoMultiplaEscolha;
import br.com.ucsal.olimpiadas.repository.Repositorio;
import br.com.ucsal.olimpiadas.repository.QuestaoRepositorio;

public class DataSeeder {
	private final Repositorio<Prova> provaRepository;
	private final QuestaoRepositorio questaoRepository;

	public DataSeeder(Repositorio<Prova> provaRepository, QuestaoRepositorio questaoRepository) {
		this.provaRepository = provaRepository;
		this.questaoRepository = questaoRepository;
	}

	public void executar() {

		var prova = new Prova();
		prova.setTitulo("Olimpíada 2026 • Nível 1 • Prova A");
		provaRepository.salvar(prova);

		var q1 = new QuestaoMultiplaEscolha();
		q1.setProvaId(prova.getId());

		q1.setEnunciado("""
				Questão 1 — Mate em 1.
				É a vez das brancas.
				Encontre o lance que dá mate imediatamente.
				""");

		q1.setFenInicial("6k1/5ppp/8/8/8/7Q/6PP/6K1 w - - 0 1");

		q1.setAlternativas(new String[] { "A) Qh7#", "B) Qf5#", "C) Qc8#", "D) Qh8#", "E) Qe6#" });

		q1.setAlternativaCorreta('C');

		questaoRepository.salvar(q1);
	}
}
