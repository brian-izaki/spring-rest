package com.logisticAlgaworks.logistic.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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

    @ManyToOne
    private Cliente cliente;

    // como dados no destinatário irá ficar tudo na mesma tabela, é utilizado o embedded, seria como se empacotasse os
    // dados em uma classe mas na tabela ta tudo no msm lugar.
    @Embedded
    private Destinatario destinatario;

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
