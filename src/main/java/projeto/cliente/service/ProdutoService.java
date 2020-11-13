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

    public void delete(String id){
        findById(id);
        produtoRepository.deleteById(id);
    }

    public Produto update(Produto produto){
        Produto updatedProduto = findById(produto.getId());
        updateData(updatedProduto, produto);
        return produtoRepository.save(updatedProduto);
    }

    private void updateData(Produto updatedProduto, Produto produto) {
        updatedProduto.setNome(produto.getNome());
        updatedProduto.setValor(updatedProduto.getValor());
        updatedProduto.setDescricao(produto.getDescricao());
        //updatedProduto.setUrlFoto(produto.getUrlFoto());
    }

}
