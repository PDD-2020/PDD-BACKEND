package projeto.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projeto.cliente.entity.Pedido;
import projeto.cliente.service.PedidoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pedidos")

public class PedidoController {

    @Autowired
    PedidoService pedidoService;

    @GetMapping
    public List<Pedido> findAll(){
        return pedidoService.findAll();
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Pedido pedido){
        Pedido objPedido = pedidoService.insert(pedido);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objPedido.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<Pedido> findById(@PathVariable String id){
        Pedido pedido = pedidoService.findById(id);
        return ResponseEntity.ok().body(pedido);
    }

    @DeleteMapping(value ="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable String id){
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
