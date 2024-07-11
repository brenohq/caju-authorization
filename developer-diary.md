# Diário de bordo

Nesse arquivo vou centralizar os pensamentos que tive e as decisões que tomei durante a execução do projeto.

## Dia 01 - segunda-feira
### Escolha da stack
- Optei por SpringBoot + JPA para abstrair a camada de persistência dos dados, para que eu consiga focar na implementação da regra de negócio em si.
- Para o banco, optei por PostgreSQL. Sei que talvez não seja a melhor solução para resolver o problema proposto (com certeza não é), mas como quero focar na regra de negócio, achei que se eu optasse por algum banco que tenho 0 experiência, talvez eu ficasse travado em coisas básicas e não conseguisse entregar o desafio.
- Tenho total consciência de que se tratando de um autorizador para ser usado em produção, essa escolha deve ser feita com muuuuito mais calma. Pensando por alto, eu optaria por alguma solução cloud native, tipo [Quarkus](https://code.quarkus.io/), para evitar os downtimes causados pela demora do boot de aplicações Spring, por exemplo.
- Em relação a modelagem da entidade `Transaction`, optei por trabalhar com o valor financeiro sendo um `BigDecimal` que representará o valor total em centavos. Isso evita diversos erros de precisão que temos ao trabalhar com pontos flutuantes como `float` e `double`.
- Configurei Docker/Docker Compose para facilitar uma futura execução em um cluster Kubernetes, e também para que o revisor do código não precise se preocupar com o setup do projeto localmente.

## Dia 02 - terça-feira
- Hoje modelei a entidade `Account` e criei o relacionamento com a classe `Transaction`.
- Criei também as classes de service, repository e controller para lidar com a manipulação das operações com `Account`.
- Pesquisei sobre padrões de arquitetura para lidar com pagamentos e cheguei no [CockroachDB](https://www.cockroachlabs.com/), que parece ser o que há de mais eficaz pra se usar em produção nesse cenário.
- Atualizei a classe CreateTransactionDto para poder enviar o campo `"accountId"` na raiz do payload para `/authorize` ao invés de mandar algo do tipo `{ "account": { "accountId": "1" }, ... }`. Não sei se isso foi uma boa decisão, ou se eu deveria manter a modelagem de `Transaction` intacta. Decidi ir pelo payload de exemplo contido na especificação.
- Criei o endpoint `/authorize` que receberá a transação a ser processada, e o método `authorize` na AccountService para implementar todas as validações propostas no desafio. Também fiquei em dúvida aqui sobre se deveria existir uma service específica para isso. Optei por implementar o método no `AccountService`, pois no final de tudo, a entidade `Account` é que será modificada no banco.

## Dia 03 - quarta-feira
- Hoje estruturei uma collection básica no Insomnia pra ajudar a testar e validar as minhas alterações.
- Alterei o `DataLoader` para parar de criar transactions de teste no startup da aplicação.
- Usei as classes `Function` e `BiConsumer` para criar o `BalanceMapper`, que implementa o de -> para de MCC -> saldos.
- Implementei o item L1 do desafio no método `authorize` da `AccountServiceImpl`
- Estudei como o [Stripe](https://docs.stripe.com/api/idempotent_requests) implementa idempotência nas suas chamadas de API.
