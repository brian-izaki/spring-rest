package com.logisticAlgaworks.logistic.api.controller;

import com.logisticAlgaworks.logistic.api.model.DestinatarioResponse;
import com.logisticAlgaworks.logistic.api.model.EntregaResponse;
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
    public ResponseEntity<EntregaResponse> buscar(@PathVariable Long id) {
        return entregaRepository.findById(id)
                .map(entrega -> {
                    EntregaResponse entregaResponse = new EntregaResponse();
                    entregaResponse.setId(entrega.getId());
                    entregaResponse.setNomeCliente(entrega.getCliente().getNome());
                    entregaResponse.setDestinatario(new DestinatarioResponse());
                    entregaResponse.getDestinatario().setNome(entrega.getDestinatario().getNome());
                    entregaResponse.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
                    entregaResponse.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
                    entregaResponse.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
                    entregaResponse.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
                    entregaResponse.setTaxa(entrega.getTaxa());
                    entregaResponse.setStatus(entrega.getStatus());
                    entregaResponse.setDataPedido(entrega.getDataPedido());
                    entregaResponse.setDataEntrega(entrega.getDataFinalizacao());

                    return ResponseEntity.ok(entregaResponse);
                })
//                .map(entrega -> ResponseEntity.ok(entrega))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega) {
        return solicitacaoEntregaService.solicitar(entrega);
    }
}
