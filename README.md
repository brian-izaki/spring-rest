# Mergulho Spring REST - logistic

Semi projeto de uma API REST para gerenciamento de logisticas.

## Ambiente

- utilizado a IDE Intellij.
- projeto com JAVA 11
- iniciado com o spring initializr
    - add módulo do Spring Web por enquanto.

## Anotações

- **_content negotiation_** é o termo utilizado pra qnd o cliente da api pode alternar a representação (json, xml, etc)
  dos dados, ela especificada no header da requisição no "Accept: application/tipo", podendo o tipo ser o json, xml,
  etc.


- **_representação de um recurso (representation model)_** são os dados que um response leva. veja dominios.


- **validações com mensagens de erro customizadas** estão
  no [ApiExceptionHandler](src/main/java/com/logisticAlgaworks/logistic/api/exceptionHandler/ApiExceptionHandler.java)
    - os textos de mensagens customizadas globalmente estão
      no [messages.properties](src/main/resources/messages.properties) (lembrando que o nome do arquivo deve ser
      exatamente igual)
    - mais infos estão no próprio código.

- **subrecursos** é um recurso (endpoint) que fica dentro de outro recurso, ex:
    - `entrega/1/ocorrencia`

- recusos como a "finalização da entrega" (que não possuem uma entidade especifica) podem ser feitos semelhantes ao
  subrecursos, com a diferença que ele não fica como verbo mas sim um substantivo, como visto
  no [PutMapping de finalizar](src/main/java/com/logisticAlgaworks/logistic/api/controller/EntregaController.java)

<details>
    <summary>Estrutura de diretórios</summary>

- a estrutura de  `src/main/java` e `src/main/resources` **é relacionado com o maven e não ao Spring Boot**.
    - `src/main/java`
        - é onde ficam os arquivos do projeto em java
    - `src/main/resources`
        - arquivos de configuração e estáticos

- diretório `src/domain` representa a parte de domínio da aplicação nele possui as:
    - regras de negócio
    - models que representam tabelas no BD.
    - requisições de acesso ao BD.
    - exceções que são relacionadas às regras de negócio

- o diretório `src/api` representa a parte que gerencia a aplicação nele possui:
    - diretório para models, mas ele se refere a representation Model, ou seja, models customizadas para que não
      precisemos ficar enviando dados desnecessários ou dados customizados e etc nas respostas de requisições.
    - endpoints da aplicação (controllers)
    - manipuladores de exceções genéricas.

</details>

<details>
    <summary>Dependencias</summary>

### Lombok

- utilizado para diminuir boilerplate na classe. (os getter e setter na model)
    - na vdd ele apenas faz uma geração automática no diretório target. lá é possível ver que a model possui os getter e
      setters.

### Jackson

- responsável por serializar e desserializar objetos (transfomar em diferentes formatos como xml, json, etc)

### Jakarta

- na dependencia spring data jpa já possui o hibernate que implementa o jakarta persistence (é uma especificação do
  jakarta EE antiga Java EE)

### ModelMapper

- utilizado neste projeto para diminuir o boilerplate do mapeamento de uma model para outra, assim, evitando usar vários
  sets.

- Para utilizar ele é necessário criar um Bean que instância o modelMapper para que fique disponível no contexto do
  Spring, assim ele pode realizar a injeção de dependência.

- existem outras dependencias que realizam essa msm ação. ex: MapStruct, orika,
  etc. [veja as comparações](https://www.baeldung.com/java-performance-mapping-frameworks)

- exemplo utilização (utilizado
  em [EntregaController](src/main/java/com/logisticAlgaworks/logistic/api/controller/EntregaController.java)):

```java
    public class Comparação {
    public String semModelMapper() {
        EntregaResponse entregaResponse = new EntregaResponse();
        entregaResponse.setId(entrega.getId());
        entregaResponse.setNomeCliente(entrega.getCliente().getNome());
        entregaResponse.setDestinatario(new DestinatarioResponse());
        entregaResponse.getDestinatario().setNome(entrega.getDestinatario().getNome());
        entregaResponse.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
        entregaResponse.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
        entregaResponse.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
        entregaResponse.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
        entregaResponse.setTaxa(entrega.getTaxa());
        entregaResponse.setStatus(entrega.getStatus());
        entregaResponse.setDataPedido(entrega.getDataPedido());
        entregaResponse.setDataEntrega(entrega.getDataFinalizacao());
    }

    public String comModelMapper() {
        EntregaResponse entregaResponse = modelMapper.map(entrega, EntregaResponse.class);
    }
}
```

- as regras de nomes dos atributos para conversão estão na documentação
  em [matching-strategies](http://modelmapper.org/user-manual/configuration/#matching-strategies)

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

### Dominios

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
