# Space.Invaders

### Link: https://github.com/beatrizsoaressd/Space.Invaders
## Sobre o Projeto

Este projeto é o trabalho final da disciplina de Programação Orientada a Objetos ministrada pelo Profº Leonardo Nogueira Matos. O objetivo principal foi o desenvolvimento de uma aplicação com GUI em duas linguagens, sendo uma delas Java (de maneira obrigatória), e a segunda linguagem escolhida pelos alunos, que em nosso caso foi JavaScript (com a condição de aplicarmos o paradigma orientado a objetos na criação do projeto), tendo como finalidade analisarmos criticamente como o paradigma é implementado nas diferentes linguagens.

A temática da aplicação escolhida e desenvolvida é uma versão simplificada do clássico jogo Space Invaders, implementada em **Java** com **JavaFX**, em **JavaScript** com **HTML5 Canvas** e **CSS**.

### **Participantes:**
* Ana Beatriz Soares Dantas
* Vanessa Santos Oliveira

--- 

## Estrutura e Instruções de Execução

O repositório está organizado com os códigos-fonte em pastas separadas.

* `/java-version`: Contém o código-fonte da versão em Java dentro da pasta `/spaceinvaders`, além disso, dentro da pasta `/resources` há imagens que foram selecionadas e usadas para "dar vida" aos elementos.


* `/javascript-version`: Contém o código-fonte da versão em JavaScript, e dentro desse diretório há também a pasta `/assets`designada a armazenar as imagens destinadas aos elementos para a versão correspondente da linguagem.

A solução contém instruções que permitem ao leitor reproduzi-la e executá-la integralmente.

### Como Executar a Versão em Java

A GUI em Java foi feita com JavaFX. E existem duas formas de executar o jogo: 
#### Opção 1: Executando a Partir do Código-Fonte (IDE)

**Pré-requisitos:**
* JDK (Java Development Kit) versão 11 ou superior.
* JavaFX SDK.
* Uma IDE Java, (utilizamos a IntelliJ IDEA).

**Passos:**
1.  Clone este repositório.
2.  Abra o projeto na pasta `java-version` no IntelliJ IDEA (ou IDE de sua escolha).
3.  Configure a IDE para usar o SDK do JavaFX.
4.  Localize e execute a classe `Main.java` contida no pacote `spaceinvaders`.

#### Opção 2: Executando o Arquivo `.jar` (Recomendado apenas para MAC OS)

Através do arquivo `.jar` (`Space.Invaders.jar`) disponível neste repositório, esta será a forma mais simples de executar o jogo.

**Pré-requisitos:**
* Java Runtime Environment (JRE) instalado no computador.

**Passos:**
* (Possuindo o arquivo), poderá executar o jogo clicando 2x no arquivo JAR (se a JRE estiver bem configurada no computador) ou, de forma mais garantida, abrindo o terminal e executando o comando `java -jar Space.Invaders.jar`.

### Como Executar a Versão em JavaScript

**Pré-requisitos:**
* Um navegador de internet moderno (Google Chrome, Firefox, Edge, etc.).

**Passos:**
1.  Clone este repositório.
2.  Navegue até a pasta `javascript-version`.
3.  Abra o arquivo `index.html` diretamente no seu navegador. O jogo iniciará automaticamente.

--- 

##  Análise Comparativa: POO em JavaScript 
Conforme o objetivo do trabalho, esta seção apresenta as impressões sobre como o paradigma da orientação a objetos é tratado na segunda linguagem (JavaScript).

### 1. Tipagem: Estática (Java) vs. Dinâmica (JavaScript)

Uma das diferenças mais impactantes que percebemos foi o sistema de tipos. Em Java, a tipagem é **estática e forte**, exigindo que cada variável tenha seu tipo declarado (`int score;`, `Player player;`). Mas por um lado, isso oferece grande segurança em tempo de compilação, detectando erros antes mesmo de executar o programa.

Em JavaScript, a tipagem é **dinâmica**. Variáveis são declaradas com `let`, `const` ou `var`, e seu tipo é determinado em tempo de execução. Observamos que isso proporcionou uma maior agilidade e flexibilidade ao escrever o código, mas também exigiu mais atenção e testes para evitar erros de tipo que só aparecem durante a execução (como passar um argumento incorreto para uma função). 

### 2. Estrutura de Classes e Sintaxe

A sintaxe de `class` do JavaScript (ES6) é cosmeticamente similar à do Java, com `constructor` e métodos. No entanto, existem diferenças fundamentais:

* **Encapsulamento:** Java possui modificadores de acesso explícitos (`private`, `public`, `protected`). Em JavaScript, o encapsulamento é tradicionalmente feito por convenção (ex: prefixo `_` em métodos "privados") ou através de recursos mais modernos. A ausência de um controle de acesso tão rígido quanto o de Java pareceu exigir mais disciplina do programador.


* **Herança:** Ambos suportam herança, mas o sistema de protótipos do JavaScript, embora abstraído pela sintaxe `class`, ainda sim é conceitualmente diferente do sistema de herança clássico do Java.

### 3. Ecossistema e Ambiente de Desenvolvimento

O desenvolvimento em **Java com IntelliJ** ofereceu uma experiência "tudo em um": a IDE gerencia a compilação, a execução e a depuração de forma integrada.

Já em **JavaScript**, o ambiente é fragmentado: o código é escrito em um editor (como IntelliJ ou VS Code), mas a execução e, principalmente, a depuração, ocorrem no **navegador**. As Ferramentas de Desenvolvedor (F12 ou também conhecido como `botão direito do mouse no navegador > "Inspecionar"`) e o `console.log` se tornaram essenciais para entender o comportamento do código durante alguns bugs que enfrentávamos, ou seja, um fluxo de trabalho diferente do debugger integrado do Java.

### 4. Loop de Jogo e Animação

A implementação do loop principal do jogo evidenciou uma grande diferença de arquitetura:

* **JavaFX:** Utiliza a classe `AnimationTimer`, uma abstração de alto nível que se integra ao ciclo de vida da plataforma.


* **JavaScript:** Utiliza a função `requestAnimationFrame(callback)`, uma API de baixo nível do navegador. Foi necessário controlar manualmente o tempo entre os frames (`deltaTime`) para garantir que a animação e a física do jogo fossem consistentes e fluidas, um controle que em JavaFX é mais abstraído.

OBS: O botão de "SAIR" na versão **JavaScript** foi feito apenas para fins de fidelidade ao visual feito na versão **Java**, porque scripts não são autorizados a serem capaz de fechar a sua aba do navegador por questões de proteção e seguranca. A maioria dos navegadores inclusive bloqueiam essa ação, pois um script só é autorizado a fechar algo que ele mesmo abriu, por exemplo, se fosse um client de jogo, seria autorizado. Mas uma aba de navegador, apenas o próprio usuário deve ter controle sobre ela e se opta por fechá-la ou não.

### 5. Impressões Finais
Desenvolver em **Java** nos deu uma sensação de robustez e segurança, graças à sua tipagem forte e ao poderoso suporte da IDE. A estrutura do código é mais rígida, o que guia o desenvolvedor a um design mais organizado desde o início.

Porém, desenvolver em **JavaScript** foi mais rápido e flexível. A natureza dinâmica da linguagem permitiu prototipar funcionalidades rapidamente. No entanto, a liberdade veio com a responsabilidade de gerenciar o estado da aplicação com mais cuidado e de depender de ferramentas externas (o navegador) para depuração. A implementação do projeto demonstrou que, embora as sintaxes possam parecer semelhantes, os fundamentos e o fluxo de trabalho em cada linguagem são distintos, nos fazendo refletir suas diferentes filosofias e ecossistemas. No fim, concluímos que o projeto foi uma ótima oportunidade para vermos de maneira tão conjunta as diferenças acontecendo numa mesma temática e ao mesmo tempo.

