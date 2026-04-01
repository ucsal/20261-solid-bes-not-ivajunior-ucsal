package br.com.ucsal.olimpiadas.ui;

import br.com.ucsal.olimpiadas.model.Participante;
import br.com.ucsal.olimpiadas.model.Prova;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
	private final Scanner scanner;

	public ConsoleUI(Scanner scanner) {
		this.scanner = scanner;
	}

	public String lerLinha() {
		return scanner.nextLine();
	}

	public void print(String msg) {
		System.out.print(msg);
	}

	public void println(String msg) {
		System.out.println(msg);
	}

	public void printf(String fmt, Object... args) {
		System.out.printf(fmt, args);
	}

	public void exibirMenu() {
		println("\n=== OLIMPÍADA DE QUESTÕES (V1) ===");
		println("1) Cadastrar participante");
		println("2) Cadastrar prova");
		println("3) Cadastrar questão (A–E) em uma prova");
		println("4) Aplicar prova (selecionar participante + prova)");
		println("5) Listar tentativas (resumo)");
		println("0) Sair");
		print("> ");
	}

	public Long escolherParticipante(List<Participante> participantes) {
		println("\nParticipantes:");
		for (var p : participantes) {
			printf("  %d) %s%n", p.getId(), p.getNome());
		}
		print("Escolha o id do participante: ");

		try {
			long id = Long.parseLong(lerLinha());
			boolean existe = participantes.stream().anyMatch(p -> p.getId() == id);
			if (!existe) {
				println("id inválido");
				return null;
			}
			return id;
		} catch (Exception e) {
			println("entrada inválida");
			return null;
		}
	}

	public Long escolherProva(List<Prova> provas) {
		println("\nProvas:");
		for (var p : provas) {
			printf("  %d) %s%n", p.getId(), p.getTitulo());
		}
		print("Escolha o id da prova: ");

		try {
			long id = Long.parseLong(lerLinha());
			boolean existe = provas.stream().anyMatch(p -> p.getId() == id);
			if (!existe) {
				println("id inválido");
				return null;
			}
			return id;
		} catch (Exception e) {
			println("entrada inválida");
			return null;
		}
	}
}
