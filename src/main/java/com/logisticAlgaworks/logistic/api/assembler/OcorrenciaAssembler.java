package com.logisticAlgaworks.logistic.api.assembler;

import com.logisticAlgaworks.logistic.api.model.OcorrenciaResponse;
import com.logisticAlgaworks.logistic.domain.model.Ocorrencia;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

    private final ModelMapper modelMapper;

    public OcorrenciaResponse toResponse(Ocorrencia ocorrencia) {
        return modelMapper.map(ocorrencia, OcorrenciaResponse.class);
    }

    public List<OcorrenciaResponse> toCollectionResponse(List<Ocorrencia> ocorrencias) {
        return ocorrencias.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
