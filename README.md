# Olimpíada de Questões - Minha Refatoração SOLID

Eai pessoal, aqui é o Ivã! 

Esse repositório é do meu trabalho de faculdade sobre o jogo de Olimpíada de Questões (um esquema meio de xadrez) no terminal. O código original em Java 25 tava funcionando mas era tipo um espaguete de código. Minha missão foi pegar isso e aplicar aqueles 5 princípios do SOLID que a gente estuda. Deu um trabalhão pq eu não podia mudar a lógica que já funcionava e nem usar coisas como Spring, tive q fazer tudo no braço.

Aqui embaixo eu explico mais ou menos como eu enxerguei e fiz cada parte:

### 1. SRP (Responsabilidade Única)
Antes tava tudo socado dentro do `App.java`. Era menu, lista de dados cravada lá dentro, validação e até o desenho do tabuleiro. Eu tirei tudo de lá.
Criei a pastinha `repository/` pra guardar as listas de dados finjindo que é um banco. Criei os `Service` pra fazer as regras e um `ConsoleUI` pra lidar com aquele scanner chato de ler teclado. 

### 2. OCP (Aberto/Fechado)
Mudei a classe `Questao` pra ser abstrata. Eu não sabia se depois a gente ia precisar fazer questão de Certo ou Errado além de ser A,B,C,D. Então eu fiz uma classe separada `QuestaoMultiplaEscolha` q herda dela. Assim se alguém quiser inventar moda depois, é só criar outra classe e não precisa mexer muito na classe velha.

### 3. LSP (Substituição Liskov)
Esse eu dei umas travadas mas a ideia foi blindar os meus models. O `Participante` não deixa colocar nome em branco agora já nos seters. E na `Resposta` eu mudei de char pra String pra caso uma questão mude no futuro, o serviço vai aceitar sem quebrar, já que eu arrumei as respostas pra ser tudo String de qualquer jeito pros filhos de Questao conversarem legal.

### 4. ISP (Segregação de Interface)
Eu via q tava tudo misturado então eu fiz um monte de interface pequena em vez de uma grandona. Criei o `Repositorio<T>`, `QuestaoRepositorio` pra umas coisas especificas, `Renderizador` pra parte do tabuleiro. Funciona bem porque cada classe q precisar só vai usar oq interessa pra ela.

### 5. DIP (Inversão de Dependência)
Essa foi a pra fechar. Eu tirei as dependencias cravadas nos serviços. Todo o serviço meu tipo `TentativaService` agora recebe as interfaces no construtor. Pra ficar facil, o `App.java` la na main é que instancia o `TentativaRepositoryMemoria` real e joga pra dentro do serviço. Pelo menos ficou mais facil se quiser trocar o banco depois.

---

## Como rodar ai no seu pc

Abram o terminal (eu uso o cmd ou powershell msm) na pasta e rodem:

Pra compilar antes:
`.\mvnw.cmd clean compile`

Se quiser ver se os testes tão ok:
`.\mvnw.cmd test`

E pra abrir o jogo pra jogar:
`.\mvnw.cmd exec:java -Dexec.mainClass="br.com.ucsal.olimpiadas.App"`

É isso. Valeuu!
