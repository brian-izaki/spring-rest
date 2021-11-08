package com.logisticAlgaworks.logistic.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.logisticAlgaworks.logistic.domain.ValidationGroups;
import com.logisticAlgaworks.logistic.domain.exception.NegocioException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

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

    // o cascade serve para sincronizar as alterações q ocorrem no objeto sem a necessidade de realizar o save nos
    // repository
    @OneToMany(mappedBy = "entrega", cascade = CascadeType.ALL)
    private List<Ocorrencia> ocorrencias = new ArrayList<>();

    // o access limita o cliente de enviar esses dados na requisição para alterações
    // o cliente poderá apenas ver os dados. isso apenas ignorar.
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Enumerated(EnumType.STRING)
    private StatusEntrega status;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataPedido;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private OffsetDateTime dataFinalizacao;

    public boolean podeFinalizar() {
        return getStatus().equals(StatusEntrega.PENDENTE);
    }

    // esta será um método de negócio
    public Ocorrencia adicionarOcorrencia(String descricao) {
        Ocorrencia ocorrencia = new Ocorrencia();
        ocorrencia.setDescricao(descricao);
        ocorrencia.setDataRegistro(OffsetDateTime.now());
        ocorrencia.setEntrega(this);

        this.getOcorrencias().add(ocorrencia);

        return ocorrencia;
    }

    public void finalizar() {
        if (!podeFinalizar()) {
            throw new NegocioException("Entrega não pode ser finalizada");
        }
        setStatus(StatusEntrega.FINALIZADA);
        setDataFinalizacao(OffsetDateTime.now());
    }
}
