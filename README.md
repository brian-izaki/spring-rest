# Mergulho Spring REST - logistic

Semi projeto de uma API REST para gerenciamento de logisticas.

## Ambiente

- utilizado a IDE Intellij.
- projeto com JAVA 11
- iniciado com o spring initializr
    - add módulo do Spring Web por enquanto.

## Anotações

- a estrutura de src/main/java e src/main/resources é relacionado com o maven e não ao Spring Boot.
    - src/main/java
        - é onde ficam os arquivos do projeto em java
    - src/main/resources
        - arquivos de configuração e estáticos

- **_content negotiation_** é o termo utilizado pra qnd o cliente da api pode alternar a representação (json, xml, etc)
  dos dados, ela especificada no header da requisição no "Accept: application/tipo", podendo o tipo ser o json, xml,
  etc.

<details>
    <summary>Lombok</summary>

## Lombok

- utilizado para diminuir boilerplate na classe. (os getter e setter na model)
    - na vdd ele apenas faz uma geração automática no diretório target. lá é possível ver que a model possui os getter e
      setters.

</details>

<details>
    <summary>Jackson</summary>

- responsável por serializar e desserializar objetos (transfomar em diferentes formatos como xml, json, etc)

</details>

