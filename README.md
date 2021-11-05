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
  
- **_representação de um recurso (representation model)_** são os dados que um response traz.

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

<details>
    <summary>Validations Groups</summary>

- **Bean validation**: são as validações que ficam acima dos atributos de uma model, eles fazem parte da dependencia
  javax.validation.

- Os group validations ficam ocultos, mas por padrão eles possuem o valor default no argumento groups.
    - para fazer uma validação funcionar apenas em um grupo especifico deve usar o `groups` que tem no bean. Como na
      model de [cliente](src/main/java/com/logisticAlgaworks/logistic/domain/model/Cliente.java)
    - para trocar o grupo deve passar o `@ConvertGroup(from = XXX, to = XXX)` acima do atributo. Como na
      model [Entrega](src/main/java/com/logisticAlgaworks/logistic/domain/model/Entrega.java)
        - sempre deve utilizar o `@Valid` do `javax.validation` antes, ele é o responsável por "ativar", as validações
          da model.

</details>

<details>
    <summary>Boas práticas data e hora no REST API</summary>

- padrão recomendado é o [ISO 8601](https://www.iso.org/iso-8601-date-and-time-format.html)
  , [exemplos](https://www.ionos.com/digitalguide/websites/web-development/iso-8601/)
- **offset** é a diferença de horas em relação ao meridiano de greenwich.
- No Java, a classe que possui o offset é o `OffsetDateTime`.
</details>

<details>
    <summary>Domínios</summary>

- Imaginar os domínios da aplicação
- temos:
    - domain model
    - representation model
- utilizando apenas as models para serem enviadas como representação de recurso há riscos de enviar dados indesejados.
- para isso é aconselhado o uso de **DTOs**
    - com ele é possível mudar totalmente ou não o que se deseja enviar como representação do recurso.
</details>

## Referencias

- mergulho spring REST da Algaworks
