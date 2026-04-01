# Olimpíada de Questões - Refatoração SOLID

Este projeto é um sistema de console para aplicar provas de perguntas (estilo xadrez) desenvolvido em Java 25. O repositório contém a versão **refatorada** do sistema original, com o objetivo de aplicar integralmente os 5 princípios SOLID, garantindo um código limpo, testável e extensível.

## 🛠️ Tecnologias
- Java 25
- Maven (com wrapper)
- JUnit 5.10.2

## 🔄 Resumo das Mudanças (SOLID)

A versão original continha um "God Object" (`App.java`) que possuía quase 400 linhas e misturava lógica de menu interativo, banco de dados em memória (listas estáticas), validação de regras de negócio, e até a renderização visual do tabuleiro de Xadrez (Notação FEN).

A refatoração dividiu a responsabilidade em camadas (`model`, `repository`, `service`, `ui`, `seed`), respeitando as restrições originais de não adicionar novos frameworks ou alterar a funcionalidade exposta ao usuário. 

Abaixo estão detalhados os 5 princípios aplicados:

### 1. S - Single Responsibility Principle (SRP)
- **Como foi aplicado:** A classe `App` foi completamente diluída. Em seu lugar, criamos subpacotes.
  - O acesso a dados (antigas coleções `static List<>`) foi extraído para classes `*RepositoryMemoria`.
  - As regras de validação, criação, e cálculos de notas foram extraídas para classes `*Service`.
  - O desenho do menu interativo e leitura do console (`Scanner`) foi transferido para `ConsoleUI`.
  - A renderização do tabuleiro FEN foi isolada no `TabuleiroRenderer`.
  - A injeção inicial de dados (Olimpíada de Xadrez) foi transferida para `DataSeeder`.

### 2. O - Open/Closed Principle (OCP)
- **Como foi aplicado:** A classe base `Questao` foi transformada num modelo **abstrato**, possuindo métodos genéricos de `isRespostaCorreta(char)` e `getAlternativas()`. Todo o comportamento original para questões de múltipla escolha (A-E) foi inserido em uma nova classe concreta `QuestaoMultiplaEscolha`. Deste modo o sistema agora está *aberto à extensão* para novos tipos de perguntas (V/F, dissertativa, etc) e *fechado para modificação* das classes principais do serviço.

### 3. L - Liskov Substitution Principle (LSP)
- **Como foi aplicado:** Garantimos as validações essenciais instanciando os invariantes diretos dentro de *Setters* dos objetos modelo (ex: validação de nulo em `Participante/Prova`), garantindo que não se construam entidades inválidas que causem erros na camada de serviço. Além disso, a classe `Resposta` foi alterada para aceitar subrespostas via `String` ao invés de `char`, fortalecendo os contratos para que qualquer tipo futuro polimórfico de `Questao` funcione nativamente nos serviços.

### 4. I - Interface Segregation Principle (ISP)
- **Como foi aplicado:** Nós desassociamos classes diretas em componentes de interface dedicados a operações restritas para cada consumidor de dependência:
  - Adicionado interface `Repositorio<T>` genérica.
  - Adicionado interface restrita `QuestaoRepositorio` que se limita a prover buscas por IDs de prova específicos.
  - Adicionado interface `EntradaSaida` para métodos de tela, e interface especifica `Renderizador` permitindo customizar a maneira com que os dados (como tabuleiros) são exportados (Console, JSON log, etc).
  - Adicionado interface limitadora `CalculadorNota` separando o dever de calcular aciertos sem ter acesso a modificador de `Tentativa`.

### 5. D - Dependency Inversion Principle (DIP)
- **Como foi aplicado:** As camadas superiores (*Services* e *App*) agora operam baseadas puramente nas interfaces (ex: injetando via construtor `Repositorio<Participante>` ao invés da classe sólida). A *Composition Root* (`App.main()`) foi mantida como único lugar ciente das classes concretas injetáveis (`ParticipanteRepositoryMemoria`, `CalculadorNotaSimples`), o sistema é totalmente invertível.

## 🚀 Como Executar

Compile e inicie o sistema utilizando os comandos Maven Wrapper presentes no repositório:

```bash
# Para compilar o projeto:
./mvnw.cmd clean compile

# Para testar:
./mvnw.cmd test

# Para executar a CLI:
./mvnw.cmd exec:java -Dexec.mainClass="br.com.ucsal.olimpiadas.App"
```
