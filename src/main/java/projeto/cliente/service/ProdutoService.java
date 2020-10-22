package projeto.cliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.cliente.entity.Produto;
import projeto.cliente.exception.ObjectNotFoundException;
import projeto.cliente.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    ProdutoRepository produtoRepository;

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto findById(String id) {
        return produtoRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Produto não encontrado"));
    }

    public Produto insert(Produto produto){
        return produtoRepository.save(produto);
    }

}
