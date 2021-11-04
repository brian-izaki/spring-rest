package com.logisticAlgaworks.logistic.api.controller;

import com.logisticAlgaworks.logistic.domain.model.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping
    public List<Cliente> listar() {
        return manager
                .createQuery("from Cliente", Cliente.class)
                .getResultList();
    }

}
