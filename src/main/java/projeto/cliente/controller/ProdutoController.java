package projeto.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projeto.cliente.entity.Produto;
import projeto.cliente.service.ProdutoService;

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

}
