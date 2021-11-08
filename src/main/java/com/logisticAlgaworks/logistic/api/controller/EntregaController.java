package com.logisticAlgaworks.logistic.api.controller;

import com.logisticAlgaworks.logistic.api.assembler.EntregaAssembler;
import com.logisticAlgaworks.logistic.api.model.EntregaResponse;
import com.logisticAlgaworks.logistic.api.model.request.EntregaRequest;
import com.logisticAlgaworks.logistic.domain.model.Entrega;
import com.logisticAlgaworks.logistic.domain.repository.EntregaRepository;
import com.logisticAlgaworks.logistic.domain.service.SolicitacaoEntregaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

    private final EntregaRepository entregaRepository;
    private final SolicitacaoEntregaService solicitacaoEntregaService;
    private final EntregaAssembler entregaAssembler;

    @GetMapping
    public List<EntregaResponse> listar() {
        List<Entrega> entregas = entregaRepository.findAll();
        return entregaAssembler.toCollectionModel(entregas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaResponse> buscar(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(entrega -> ResponseEntity.ok(entregaAssembler.toModel(entrega)))
//                .map(entrega -> ResponseEntity.ok(entrega))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaResponse solicitar(@Valid @RequestBody EntregaRequest entregaRequest) {
        Entrega entrega = entregaAssembler.toEntity(entregaRequest);
        Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(entrega);

        return entregaAssembler.toModel(entregaSolicitada);
    }
}
