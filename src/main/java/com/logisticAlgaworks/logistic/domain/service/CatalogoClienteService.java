package com.logisticAlgaworks.logistic.domain.service;

import com.logisticAlgaworks.logistic.domain.exception.NegocioException;
import com.logisticAlgaworks.logistic.domain.model.Cliente;
import com.logisticAlgaworks.logistic.domain.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
// diz que é um componente com semântica de serviço pro Spring
@Service
public class CatalogoClienteService {

    private ClienteRepository clienteRepository;

    public Cliente buscar(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));
    }

    @Transactional
    public Cliente salvar(Cliente cliente) {
        boolean hasEmail = clienteRepository.findByEmail(cliente.getEmail())
                .stream()
                .anyMatch(clienteExist -> !clienteExist.equals(cliente));

        if (hasEmail) {
            throw new NegocioException("Já existe um cliente cadastrado com este e-mail.");
        }

        return clienteRepository.save(cliente);
    }

    @Transactional
    public void excluir(Long id) {
        clienteRepository.deleteById(id);
    }

}
