package projeto.cliente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projeto.cliente.entity.Cliente;
import projeto.cliente.service.ClienteService;

import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> get(@RequestParam(required = false) Long id) {
        return this.service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody Cliente cliente) {
        return this.service.create(cliente);
    }

    @PutMapping("/{id}")
    public String updateAge(@RequestBody Cliente cliente,
                            @PathVariable Long id) {
        return this.service.updateAge(id, cliente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }
}
