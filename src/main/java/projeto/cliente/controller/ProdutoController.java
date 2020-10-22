package projeto.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import projeto.cliente.entity.Produto;
import projeto.cliente.service.ProdutoService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

    @Autowired
    ProdutoService produtoService;

    @GetMapping
    public List<Produto> findAll(){
        return produtoService.findAll();
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<Produto> findById(@PathVariable String id){
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok().body(produto);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Produto produto){
        Produto objProduto = produtoService.insert(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(objProduto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping(value ="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable String id){
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
