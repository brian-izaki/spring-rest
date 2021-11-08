package com.logisticAlgaworks.logistic.api.assembler;

import com.logisticAlgaworks.logistic.api.model.EntregaResponse;
import com.logisticAlgaworks.logistic.api.model.request.EntregaRequest;
import com.logisticAlgaworks.logistic.domain.model.Entrega;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

// classe responsável pela montagem do mapper
@AllArgsConstructor
@Component
public class EntregaAssembler {
    private final ModelMapper modelMapper;

    public EntregaResponse toModel(Entrega entrega) {
        return modelMapper.map(entrega, EntregaResponse.class);
    }

    public List<EntregaResponse> toCollectionModel(List<Entrega> entregas) {
        return entregas.stream()
                // o this representa a nossa model, por isso ele pode pegar o toModel
                .map(this::toModel) // converte o tipo Entrega para o tipo entregaResponse
                .collect(Collectors.toList());
    }

    public Entrega toEntity(EntregaRequest entregaRequest) {
        return modelMapper.map(entregaRequest, Entrega.class);
    }

}
