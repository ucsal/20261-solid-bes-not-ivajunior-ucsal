Olimpíada de Questões - Refatoração com SOLID

E aí pessoal, Ivã aqui!

Esse repositório é um trabalho da faculdade baseado no jogo de Olimpíada de Questões. O projeto original (em Java 25) já funcionava, mas estava bem acoplado e difícil de manter — basicamente um “espaguete de código”.

A proposta foi refatorar aplicando os princípios do SOLID, sem alterar a lógica existente e sem usar frameworks (tipo Spring). Ou seja, tudo foi feito na mão mesmo, o que deu um certo trabalho, principalmente pra organizar melhor as responsabilidades sem quebrar nada.

Abaixo explico como apliquei cada princípio na prática:

1. SRP (Single Responsibility Principle)

Antes, praticamente toda a lógica estava concentrada no App.java: menu, dados mockados, validações e até renderização do tabuleiro.

Na refatoração:

Separei a camada de dados em repository/ (simulando um banco em memória)
Criei serviços (Service) para centralizar regras de negócio
Implementei um ConsoleUI responsável apenas pela entrada/saída no terminal

A ideia foi deixar cada classe com uma única responsabilidade bem definida, facilitando manutenção e leitura.

2. OCP (Open/Closed Principle)

A classe Questao foi transformada em abstrata, servindo como base para especializações.

Criei, por exemplo:

QuestaoMultiplaEscolha

Com isso, o sistema fica aberto para extensão (novos tipos de questão, como verdadeiro/falso), mas fechado para modificação nas classes já existentes. Ou seja, dá pra evoluir sem sair quebrando o que já funciona.

3. LSP (Liskov Substitution Principle)

Aqui a preocupação foi garantir que as subclasses realmente possam substituir a classe base sem comportamento inesperado.

Alguns ajustes:

Validação no model Participante (ex: não permite nome vazio)
Alterei o tipo de resposta de char para String, pensando em suportar diferentes formatos de questão no futuro

Isso ajuda a manter consistência entre as entidades e evita problemas quando novas implementações de Questao forem adicionadas.

4. ISP (Interface Segregation Principle)

Em vez de criar interfaces grandes e genéricas, optei por interfaces menores e mais específicas:

Repositorio<T> (genérico)
QuestaoRepositorio (com comportamentos específicos)
Renderizador (responsável pela visualização do tabuleiro)

Assim, cada classe depende apenas do que realmente precisa, evitando acoplamento desnecessário.

5. DIP (Dependency Inversion Principle)

Os serviços não dependem mais de implementações concretas, e sim de abstrações (interfaces).

Exemplo:

TentativaService recebe interfaces via construtor

A classe App.java (na main) ficou responsável por instanciar as implementações concretas (como TentativaRepositoryMemoria) e injetar nos serviços.

Isso facilita bastante a troca de implementações no futuro (ex: sair de memória pra banco real) sem alterar a lógica de negócio.

Como rodar o projeto

No terminal (cmd ou PowerShell), dentro da pasta do projeto:

Compilar:

.\mvnw.cmd clean compile

Rodar testes:

.\mvnw.cmd test

Executar a aplicação:

.\mvnw.cmd exec:java -Dexec.mainClass="br.com.ucsal.olimpiadas.App"

É isso!
Foi um exercício bem prático de SOLID, principalmente na parte de organizar melhor o código sem poder sair mudando tudo.
