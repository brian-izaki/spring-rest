package com.logisticAlgaworks.logistic.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

// quando for enviar um json que represente esta model, irá apenas enviar campos que não sejam nulos.
// se for nulo não irá aparecer no json da resposta.
@JsonInclude(JsonInclude.Include.NON_NULL)
// gera o boilerplate de equals e hashcode apenas dos atributos explicitos
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Cliente {

    // EqualsAndHashCode.Include: torna explicito o atributo para ter o boilerplate dele.
    // GenerationType.IDENTITY: utiliza a forma nativa do banco de dados para automatizar a geração de ids
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome não pode ser vazio ou nulo")
    @Size(max = 60)
    private String nome;

    @NotBlank
    @Email
    @Size(max = 255)
    private String email;

    @NotBlank
    @Size(max = 20)
    private String telefone;

}
