package com.logisticAlgaworks.logistic.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.logisticAlgaworks.logistic.domain.ValidationGroups;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Entrega {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Valid
    @ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)
    @NotNull
    @ManyToOne
    private Cliente cliente;

    // como dados no destinatário irá ficar tudo na mesma tabela, é utilizado o embedded, seria como se empacotasse os
    // dados em uma classe mas na tabela ta tudo no msm lugar.
    @Embedded
    @Valid
    @NotNull
    private Destinatario destinatario;

    @NotNull
    private BigDecimal taxa;

    // o access limita o cliente de enviar esses dados na requisição para alterações
    // o cliente poderá apenas ver os dados. isso apenas ignorar.
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime dataFinalizacao;
}
