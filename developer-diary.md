# Diário de bordo

Nesse arquivo vou centralizar os pensamentos que tive e as decisões que tomei durante a execução do projeto.

## Dia 01 - segunda-feira
### Escolha da stack
- Optei por SpringBoot + JPA para abstrair a camada de persistência dos dados, para que eu consiga focar na implementação da regra de negócio em si.
- Para o banco, optei por PostgreSQL. Sei que talvez não seja a melhor solução para resolver o problema proposto (com certeza não é), mas como quero focar na regra de negócio, achei que se eu optasse por algum banco que tenho 0 experiência, talvez eu ficasse travado em coisas básicas e não conseguisse entregar o desafio.
- Tenho total consciência de que se tratando de um autorizador para ser usado em produção, essa escolha deve ser feita com muuuuito mais calma. Pensando por alto, eu optaria por alguma solução cloud native, tipo [Quarkus](https://code.quarkus.io/), para evitar os downtimes causados pela demora do boot de aplicações Spring, por exemplo.
- Em relação a modelagem da entidade `Transaction`, optei por trabalhar com o valor financeiro sendo um `BigDecimal` que representará o valor total em centavos. Isso evita diversos erros de precisão que temos ao trabalhar com pontos flutuantes como `float` e `double`.
- Configurei Docker/Docker Compose para facilitar uma futura execução em um cluster Kubernetes, e também para que o revisor do código não precise se preocupar com o setup do projeto localmente.

