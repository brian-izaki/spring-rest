package com.logisticAlgaworks.logistic.domain.service;

import com.logisticAlgaworks.logistic.domain.exception.EntidadeNaoEncontradaException;
import com.logisticAlgaworks.logistic.domain.exception.NegocioException;
import com.logisticAlgaworks.logistic.domain.model.Entrega;
import com.logisticAlgaworks.logistic.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BuscaEntregaService {

    private final EntregaRepository entregaRepository;

    public Entrega buscar(Long entregaId) {
        return entregaRepository.findById(entregaId)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
    }

}
