package com.logisticAlgaworks.logistic.api.controller;

import com.logisticAlgaworks.logistic.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @GetMapping
    public List<Cliente> listar() {
        Cliente cliente = new Cliente();
        cliente.setId(1L);
        cliente.setNome("Teste 1");
        cliente.setEmail("teste@teste.com");
        cliente.setTelefone("12234-13423");
        Cliente cliente2 = new Cliente();
        cliente2.setId(2L);
        cliente2.setNome("Teste 2");
        cliente2.setEmail("test2@teste.com");
        cliente2.setTelefone("2222-13423");

        return List.of(cliente, cliente2);
    }

}
