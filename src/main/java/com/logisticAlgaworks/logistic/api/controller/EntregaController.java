package com.logisticAlgaworks.logistic.api.controller;

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

    @GetMapping
    public List<Entrega> listar() {
        return entregaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> buscar(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(ResponseEntity::ok)
//                .map(entrega -> ResponseEntity.ok(entrega))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return solicitacaoEntregaService.solicitar(entrega);
    }
}
