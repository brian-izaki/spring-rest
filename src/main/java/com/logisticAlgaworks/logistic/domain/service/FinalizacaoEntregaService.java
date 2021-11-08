package com.logisticAlgaworks.logistic.domain.service;

import com.logisticAlgaworks.logistic.domain.model.Entrega;
import com.logisticAlgaworks.logistic.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

    private final EntregaRepository entregaRepository;
    private final BuscaEntregaService buscaEntregaService;

    @Transactional
    public void finalizar(Long entregaId) {
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        entrega.finalizar();

        entregaRepository.save(entrega);
    }


}
