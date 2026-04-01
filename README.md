# ♟️ Olimpíada de Questões — Refatoração SOLID

Olá! Bem-vindo(a) ao repositório do **Olimpíada de Questões**, um sistema interativo via terminal (CLI) projetado para aplicar provas com foco em desafios de xadrez.

Este projeto nasceu como uma refatoração de um código existente construído em **Java 25**. O grande objetivo aqui foi pegar um código que funcionava e transformá-lo num código **limpo**, aplicando do começo ao fim os **5 princípios do código SOLID**, mas com um desafio extra: **não mudar a lógica de negócio, não remover funcionalidades que o usuário já tinha acesso e sem instalar nenhum framework gigante** da vida real (como Spring Boot).

## 🛠️ Tecnologias que usamos
- **Java 25** (pura e direta!)
- **Maven** (Usando o Wrapper, então você nem precisa instalar ele solto na sua máquina)
- **JUnit 5.10** (A base de testes está montada e compila!)

## 🚀 Resumo da Jornada SOLID

O projeto original sofria um grande mal: a famosa "Classe Deus" (`App.java` com as suas 400 linhas). A classe de entrada cuidava do menu, da "banco de dados" em listas estáticas, validava dados, guardava regras de domínio e ainda por cima desenhava um tabuleiro FEN no painel do console. Era muito fardo nas costas dela!

Aqui está como tratamos e dividimos essa aplicação aplicando os grandes princípios orientados a objeto:

### 🎒 **1. Princípio da Responsabilidade Única (SRP)**
Deixamos a classe `App` respirar aliviada, criando caixinhas pra cada parte da lógica:
- Tiramos a leitura do teclado e impressões do console e botamos no `ConsoleUI`. Ah, e a renderização maluca de matrizes no console? Fica lindamente no `TabuleiroRenderer`.
- Todo o processamento lógico foi para os novos arquivos `*Service`. O serviço coordena as validações e as ações.
- Criamos a nossa pseudo "camada de Dados", movendo o controle dos contadores de ID e o controle das listas `List<Model>` para dentro de arquivos da pasta `repository/`.
- Os dados inaugurais (o famoso seed de testes rápidos com os lances já valendo ponto) foram pro cantinho deles no `DataSeeder`.

### 🧩 **2. Princípio do Aberto/Fechado (OCP)**
Se amanhã inventarem que a prova precisa de uma questão de "Verdadeiro e Falso"? Hoje a `Questao` não passa mais sufoco.
Transformamos a classe `Questao` num modelo básico **abstrato**, pronto para se transformar e expandir pro que vier. Toda a base das perguntas clássicas e o seu painel de verificação foi alocado na super específica classe `QuestaoMultiplaEscolha`. Conclusão: estamos *abertos para a extensão* de tipos, mas o esqueleto do sistema se manteve *fechado para ser tocado a todo momento*.

### 🔄 **3. Princípio da Substituição de Liskov (LSP)**
Nenhum objeto ruim é jogado na cara das camadas dos Serviços. Blindamos o comportamento natural dos Modelos. Exemplo prático: O `Participante` não deixa mais colocarem seu nome vazio (lançando os bloqueios já nos seus próprios Setters).  Foi reajustada também flexibilidade da classe que carrega os pontos: `Resposta` não funciona de maneira restrita aos `char` ("A, B, C"); ele assume um viés mais polivalente em forma de String. Agora, os subtipos (classes-filhas) de `Questao` e os resultados conversam em harmonia total e com naturalidade entre si!

### 🔪 **4. Princípio da Segregação de Interface (ISP)**
Criar interfaces generalistas é ruim. Fizemos "micro-contratos". As classes consumidoras não herdam funções lixo da qual ignorariam o uso:
- Quem acessa os dados vê apenas a interface `Repositorio<T>`.
- Quem vai pesquisar filtros de questões olha focado para a `QuestaoRepositorio`.
- Para o visual existe os papéis isolados da interface `EntradaSaida` contra a focada num único tipo output da interface `Renderizador`.

### 🔌 **5. Princípio da Inversão de Dependência (DIP)**
Camadas de alto nível não dependem de implementações burras e rústicas de baixo nível! Agora os serviços todos trabalham sob regência das interfaces. Por exemplo: o `TentativaService` é instanciado recebendo de injeção externa seu humilde construtor de repositório e alguém que saiba a função naturalística de um `CalculadorNota`. O único ponto focal que espirra o mundo concreto é mantido numa bolha protegida na **Composition Root** onde a main inicializa tudo: ela faz as "matrículas" reais pros services (`ParticipanteRepositoryMemoria`, `CalculadorNotaSimples`), amarrando a inversão lindamente.

---

## 🎮 Quero matar minhas saudades. Como rodar?

Dê seus checks nesses comandos no terminal (pode ser o Powershell, Git Bash ou Cmd mesmo) na pasta do projeto e o jogo via CLI ganha vida novamente:

**1. Rodando a limpeza da casa e subindo o projeto compilado:**
```bash
./mvnw.cmd clean compile
```

**2. Passando as baterias dos testes pra garantir saúde total:**
```bash
./mvnw.cmd test
```

**3. Jogando:**
```bash
./mvnw.cmd exec:java -Dexec.mainClass="br.com.ucsal.olimpiadas.App"
```

Obrigado pela visita, esperamos que esse pequeno playground de Arquitetura Limpa possa ter provado a força do uso dos Padrões (Design Patterns)! ♔♕♖♗♘♙
