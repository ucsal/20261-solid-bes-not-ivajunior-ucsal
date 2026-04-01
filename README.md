# ♟️ Olimpíada de Questões — Minha Refatoração SOLID

Olá! Bem-vindo(a) ao repositório do **Olimpíada de Questões**, um sistema interativo via terminal (CLI) que projetei para aplicar provas com foco em desafios de xadrez.

Aqui é o Ivã. Este projeto nasceu do meu esforço em pegar um código existente construído em **Java 25** e transformá-lo num código **limpo**, aplicando do começo ao fim os **5 princípios do SOLID**. O meu grande desafio pessoal foi realizar isso **sem mudar a lógica de negócio, sem remover funcionalidades e sem instalar nenhum framework gigante** (como o Spring Boot). Mão na massa, código puro!

## 🛠️ Tecnologias que decidi manter
- **Java 25** (pura e direta!)
- **Maven** (Usando o Wrapper, então você nem precisa ter instalado na sua máquina)
- **JUnit 5.10** (Base de testes montada e compilando lisinho)

## 🚀 Resumo da Minha Jornada SOLID

Quando comecei, o projeto original sofria de um mal clássico: a famosa "Classe Deus" (`App.java` com as suas assustadoras 400 linhas). Essa única classe de entrada cuidava do menu iterativo, servia de "banco de dados" segurando listas estáticas, validava os dados, controlava o domínio e para piorar, imprimia um tabuleiro de xadrez FEN inteiro no console. 

Para resolver isso, estruturei a aplicação separando o peso nos princípios clássicos orientados a objeto. Veja como fiz:

### 🎒 **1. Princípio da Responsabilidade Única (SRP)**
Deixei a classe `App` respirar aliviada, criando módulos específicos para cada pedaço da lógica:
- Tirei a leitura do teclado e impressões do console e movi para uma nova classe `ConsoleUI`. E aquela renderização maluca de matrizes no console? Isolei lindamente no meu `TabuleiroRenderer`.
- Todo o processamento e regras foram transferidos para as classes `*Service`. O serviço é quem coordena tudo agora.
- Criei uma pseudo "camada de Dados", movendo o controle dos contadores de ID e as listas `List<Model>` para dentro dos arquivos do `repository/`.
- Deixei os dados obrigatórios e as seeds de inicialização bem isolados no meu `DataSeeder`.

### 🧩 **2. Princípio do Aberto/Fechado (OCP)**
Pensei: "E se futuramente eu quiser adicionar uma prova de 'Verdadeiro e Falso'?"
Transformei a minha classe `Questao` num modelo básico **abstrato**, pronto para se desdobrar no que vier. Todo o comportamento base sobre as alternativas foi para minha nova subclasse super específica `QuestaoMultiplaEscolha`. Conclusão: mantive minha fundação *fechada para ser alterada* loucamente, mas *aberta à extensões* infinitas de tipos de questões!

### 🔄 **3. Princípio da Substituição de Liskov (LSP)**
Eu não queria que nenhum dado inconsistente quebrasse minha camada de Serviço. Para isso, blindei o comportamento natural dos meus modelos. O meu `Participante`, por exemplo, não permite que setem nomes vazios; ele já bloqueia em seu próprio método *Setter*, definindo Invariantes fortes. Reajustei a classe de `Resposta` para guardar de forma polivalente (usando String no lugar de um Char fixo) o seu valor. Assim, eu garanto as respostas compatíveis independente que de meus filhos herdeiros da minha classe abstrata `Questao` respondam no futuro. Tudo conversa sem sobressaltos e sem usar instaceOf!

### 🔪 **4. Princípio da Segregação de Interface (ISP)**
Criar interfaces gigantes não era meu foco. Criei "micro-contratos"! Minhas classes não assinam funções pesadas que não precisam:
- Os acessos a banco agora utilizam a interface enxuta `Repositorio<T>`.
- Para acessar minhas filtragens, basta olhar para a `QuestaoRepositorio`.
- Separei a ideia de renderização visual: fiz minha interface `EntradaSaida` para o scanner básico de menus, mas deixei a interface `Renderizador` solta para cuidar de imagens, como no xadrez.

### 🔌 **5. Princípio da Inversão de Dependência (DIP)**
Por fim, me recusei a acoplar minhas camadas de alto nível em bancos de baixo nível primitivos. Agora todos os meus `Services` recebem Interfaces (abstrações) injetadas de fora. Tome por exemplo meu `TentativaService`: ele é criado no seu construtor exigindo o repositório de interfaces e algo que assine o conceito de `CalculadorNota`. Consegui concentrar toda essa inicialização bruta de infraestrutura numa camada raiz segura, a minha famosa **Composition Root** (na própria main do `App`), que repassa o controle invertido para todas as dependências do meu projeto. 

---

## 🎮 Quero testar! Como rodo sua aplicação?

Eu facilitei o trabalho. Pode rodar os comandos no seu terminal favorito dentro dessa pasta que a CLI levanta direto:

**1. Limpando a casa e compilando a minha versão:**
```bash
./mvnw.cmd clean compile
```

**2. Passando as baterias de testes para atestar o funcionamento:**
```bash
./mvnw.cmd test
```

**3. Jogando meu sistema:**
```bash
./mvnw.cmd exec:java -Dexec.mainClass="br.com.ucsal.olimpiadas.App"
```

Obrigado por conferir o projeto! Gostei muito do aprendizado aplicando Design Patterns clássicos de Arquitetura Limpa aqui. Câmbio desligo! ♔♕♖♗♘♙
