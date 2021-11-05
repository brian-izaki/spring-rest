package com.logisticAlgaworks.logistic.api.controller;

import com.logisticAlgaworks.logistic.domain.model.Cliente;
import com.logisticAlgaworks.logistic.domain.repository.ClienteRepository;
import com.logisticAlgaworks.logistic.domain.service.CatalogoClienteService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor // gera um construtor com todas as propriedades.
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    // pode ser utilizado esta maneira, porém pode dificultar quando realizar testes
    // a melhor maneira seria utilizando um construtor para a classe. Pode-se utilizar o lombok para isso
    // @Autowired
    private ClienteRepository clienteRepository;
    private CatalogoClienteService catalogoClienteService;

    @GetMapping
    public List<Cliente> listar() {
        return clienteRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable("id") Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        /*
            ** esta parte comentada seria a mesma coisa que o return funcional.

            if (cliente.isPresent()) {
                return ResponseEntity.ok(cliente.get());
            }

            return ResponseEntity.notFound().build();
        */

        return cliente
                // .map(cliente -> ResponseEntity.ok(cliente)) // seria a forma normal da linha d baixo
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente salvar(@Valid @RequestBody Cliente cliente) {
        return catalogoClienteService.salvar(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id, @RequestBody Cliente cliente) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        cliente.setId(id);
        cliente = catalogoClienteService.salvar(cliente);

        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        if (!clienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        catalogoClienteService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}
