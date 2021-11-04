package com.logisticAlgaworks.logistic.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    private String nome;
    private String email;
    private String telefone;

}
