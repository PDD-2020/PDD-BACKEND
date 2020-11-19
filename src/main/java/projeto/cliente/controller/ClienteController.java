package projeto.cliente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projeto.cliente.entity.Cliente;
import projeto.cliente.entity.Pedido;
import projeto.cliente.service.ClienteService;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController()
@RequestMapping("/cliente")
@CrossOrigin
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Cliente> findAll() {
        return service.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Cliente cliente){
        Cliente obj = service.create(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<Cliente> findById(@PathVariable String id){
        Cliente cliente = service.findById(id);
        return ResponseEntity.ok().body(cliente);
    }

    @PutMapping("/{id}")
    public String updateAge(@RequestBody Cliente cliente,
                            @PathVariable Long id) {
        return this.service.updateAge(id, cliente);
    }

    @DeleteMapping(value ="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable String id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
