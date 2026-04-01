package br.com.ucsal.olimpiadas;

import java.util.Scanner;

import br.com.ucsal.olimpiadas.model.Questao;
import br.com.ucsal.olimpiadas.model.Resposta;
import br.com.ucsal.olimpiadas.model.Tentativa;
import br.com.ucsal.olimpiadas.repository.ParticipanteRepository;
import br.com.ucsal.olimpiadas.repository.ProvaRepository;
import br.com.ucsal.olimpiadas.repository.QuestaoRepository;
import br.com.ucsal.olimpiadas.repository.TentativaRepository;
import br.com.ucsal.olimpiadas.seed.DataSeeder;
import br.com.ucsal.olimpiadas.service.ParticipanteService;
import br.com.ucsal.olimpiadas.service.ProvaService;
import br.com.ucsal.olimpiadas.service.QuestaoService;
import br.com.ucsal.olimpiadas.service.TentativaService;
import br.com.ucsal.olimpiadas.ui.ConsoleUI;
import br.com.ucsal.olimpiadas.ui.TabuleiroRenderer;

public class App {

	private final ConsoleUI ui;
	private final ParticipanteService participanteService;
	private final ProvaService provaService;
	private final QuestaoService questaoService;
	private final TentativaService tentativaService;
	private final TabuleiroRenderer tabuleiroRenderer;

	public App(ConsoleUI ui,
			ParticipanteService participanteService,
			ProvaService provaService,
			QuestaoService questaoService,
			TentativaService tentativaService,
			TabuleiroRenderer tabuleiroRenderer) {
		this.ui = ui;
		this.participanteService = participanteService;
		this.provaService = provaService;
		this.questaoService = questaoService;
		this.tentativaService = tentativaService;
		this.tabuleiroRenderer = tabuleiroRenderer;
	}

	public void executar() {
		while (true) {
			ui.exibirMenu();

			switch (ui.lerLinha()) {
			case "1" -> cadastrarParticipante();
			case "2" -> cadastrarProva();
			case "3" -> cadastrarQuestao();
			case "4" -> aplicarProva();
			case "5" -> listarTentativas();
			case "0" -> {
				ui.println("tchau");
				return;
			}
			default -> ui.println("opção inválida");
			}
		}
	}

	private void cadastrarParticipante() {
		ui.print("Nome: ");
		var nome = ui.lerLinha();

		ui.print("Email (opcional): ");
		var email = ui.lerLinha();

		try {
			var p = participanteService.cadastrar(nome, email);
			ui.println("Participante cadastrado: " + p.getId());
		} catch (IllegalArgumentException e) {
			ui.println(e.getMessage());
		}
	}

	private void cadastrarProva() {
		ui.print("Título da prova: ");
		var titulo = ui.lerLinha();

		try {
			var prova = provaService.cadastrar(titulo);
			ui.println("Prova criada: " + prova.getId());
		} catch (IllegalArgumentException e) {
			ui.println(e.getMessage());
		}
	}

	private void cadastrarQuestao() {
		if (provaService.listarTodos().isEmpty()) {
			ui.println("não há provas cadastradas");
			return;
		}

		var provaId = ui.escolherProva(provaService.listarTodos());
		if (provaId == null)
			return;

		ui.println("Enunciado:");
		var enunciado = ui.lerLinha();

		var alternativas = new String[5];
		for (int i = 0; i < 5; i++) {
			char letra = (char) ('A' + i);
			ui.print("Alternativa " + letra + ": ");
			alternativas[i] = letra + ") " + ui.lerLinha();
		}

		ui.print("Alternativa correta (A–E): ");
		char correta;
		try {
			correta = Questao.normalizar(ui.lerLinha().trim().charAt(0));
		} catch (Exception e) {
			ui.println("alternativa inválida");
			return;
		}

		var q = questaoService.cadastrar(provaId, enunciado, alternativas, correta);
		ui.println("Questão cadastrada: " + q.getId() + " (na prova " + provaId + ")");
	}

	private void aplicarProva() {
		if (participanteService.listarTodos().isEmpty()) {
			ui.println("cadastre participantes primeiro");
			return;
		}
		if (provaService.listarTodos().isEmpty()) {
			ui.println("cadastre provas primeiro");
			return;
		}

		var participanteId = ui.escolherParticipante(participanteService.listarTodos());
		if (participanteId == null)
			return;

		var provaId = ui.escolherProva(provaService.listarTodos());
		if (provaId == null)
			return;

		var questoesDaProva = questaoService.buscarPorProvaId(provaId);

		if (questoesDaProva.isEmpty()) {
			ui.println("esta prova não possui questões cadastradas");
			return;
		}

		var tentativa = new Tentativa();
		tentativa.setParticipanteId(participanteId);
		tentativa.setProvaId(provaId);

		ui.println("\n--- Início da Prova ---");

		for (var q : questoesDaProva) {
			ui.println("\nQuestão #" + q.getId());
			ui.println(q.getEnunciado());

			ui.println("Posição inicial:");
			tabuleiroRenderer.renderizar(q.getFenInicial());

			for (var alt : q.getAlternativas()) {
			    ui.println(alt);
			}

			ui.print("Sua resposta (A–E): ");
			char marcada;
			try {
				marcada = Questao.normalizar(ui.lerLinha().trim().charAt(0));
			} catch (Exception e) {
				ui.println("resposta inválida (marcando como errada)");
				marcada = 'X';
			}

			var r = new Resposta();
			r.setQuestaoId(q.getId());
			r.setAlternativaMarcada(String.valueOf(marcada));
			r.setCorreta(q.isRespostaCorreta(marcada));

			tentativa.getRespostas().add(r);
		}

		tentativaService.salvar(tentativa);

		int nota = tentativaService.calcularNota(tentativa);
		ui.println("\n--- Fim da Prova ---");
		ui.println("Nota (acertos): " + nota + " / " + tentativa.getRespostas().size());
	}

	private void listarTentativas() {
		ui.println("\n--- Tentativas ---");
		for (var t : tentativaService.listarTodas()) {
			ui.printf("#%d | participante=%d | prova=%d | nota=%d/%d%n", t.getId(), t.getParticipanteId(),
					t.getProvaId(), tentativaService.calcularNota(t), t.getRespostas().size());
		}
	}

	public static void main(String[] args) {
		var participanteRepo = new ParticipanteRepository();
		var provaRepo = new ProvaRepository();
		var questaoRepo = new QuestaoRepository();
		var tentativaRepo = new TentativaRepository();

		var ui = new ConsoleUI(new Scanner(System.in));
		var tabuleiroRenderer = new TabuleiroRenderer();

		var participanteService = new ParticipanteService(participanteRepo);
		var provaService = new ProvaService(provaRepo);
		var questaoService = new QuestaoService(questaoRepo);
		var tentativaService = new TentativaService(tentativaRepo);

		new DataSeeder(provaRepo, questaoRepo).executar();

		var app = new App(ui, participanteService, provaService, questaoService, tentativaService, tabuleiroRenderer);
		app.executar();
	}
}