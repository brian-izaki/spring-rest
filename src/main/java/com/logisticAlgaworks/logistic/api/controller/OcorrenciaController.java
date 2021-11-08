package com.logisticAlgaworks.logistic.api.controller;

import com.logisticAlgaworks.logistic.api.assembler.OcorrenciaAssembler;
import com.logisticAlgaworks.logistic.api.model.OcorrenciaResponse;
import com.logisticAlgaworks.logistic.api.model.request.OcorrenciaRequest;
import com.logisticAlgaworks.logistic.domain.model.Entrega;
import com.logisticAlgaworks.logistic.domain.model.Ocorrencia;
import com.logisticAlgaworks.logistic.domain.service.BuscaEntregaService;
import com.logisticAlgaworks.logistic.domain.service.RegistroOcorrenciaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

    private final BuscaEntregaService buscaEntregaService;
    private final RegistroOcorrenciaService registroOcorrenciaService;
    private final OcorrenciaAssembler ocorrenciaAssembler;

    @GetMapping
    public List<OcorrenciaResponse> listar(@PathVariable Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        return ocorrenciaAssembler.toCollectionResponse(entrega.getOcorrencias());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OcorrenciaResponse registrar(@PathVariable Long entregaId,
                                        @Valid @RequestBody OcorrenciaRequest ocorrenciaRequest) {
        Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId, ocorrenciaRequest.getDescricao());

        return ocorrenciaAssembler.toResponse(ocorrenciaRegistrada);
    }

}
