package com.logisticAlgaworks.logistic.domain.service;

import com.logisticAlgaworks.logistic.domain.exception.NegocioException;
import com.logisticAlgaworks.logistic.domain.model.Entrega;
import com.logisticAlgaworks.logistic.domain.model.Ocorrencia;
import com.logisticAlgaworks.logistic.domain.repository.EntregaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

    private final BuscaEntregaService buscaEntregaService;

    @Transactional
    public Ocorrencia registrar(Long entregaId, String descricao) {
        // neste método não precisa realizar save. Pois como já estamos pegando um registro o jakarta entende que
        // quando ocorrer alteração no registro, ele vai sincronizar e persistir com o BD
        // obs: é necssário realizar uma config na entidade Entrega para isso.
        Entrega entrega = buscaEntregaService.buscar(entregaId);

        // está sendo feito uma alteração tanto no objeto como no registro.
        return entrega.adicionarOcorrencia(descricao);
    }

}
