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

- **validações com mensagens de erro customizadas** estão
  no [ApiExceptionHandler](src/main/java/com/logisticAlgaworks/logistic/api/exceptionHandler/ApiExceptionHandler.java)
    - os textos de mensagens customizadas globalmente estão
      no [messages.properties](src/main/resources/messages.properties) (lembrando que o nome do arquivo deve ser
      exatamente igual)
    - mais infos estão no próprio código.

<details>
    <summary>Dependencias</summary>

## Lombok

- utilizado para diminuir boilerplate na classe. (os getter e setter na model)
    - na vdd ele apenas faz uma geração automática no diretório target. lá é possível ver que a model possui os getter e
      setters.

## Jackson

- responsável por serializar e desserializar objetos (transfomar em diferentes formatos como xml, json, etc)

## Jakarta

- na dependencia spring data jpa já possui o hibernate que implementa o jakarta persistence (é uma especificação do
  jakarta EE antiga Java EE)

</details>

<details>
    <summary>Response Entity</summary>

- ReseponseEntity permite uma manipulação mais customizada de um endpoint. exemplos:
    - enviar cabeçalhos diferentes.
    - status diferentes.

</details>

