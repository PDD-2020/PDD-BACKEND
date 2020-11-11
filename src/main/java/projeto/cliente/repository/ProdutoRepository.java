package projeto.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projeto.cliente.entity.Produto;

@Repository
public interface ProdutoRepository extends MongoRepository<Produto, String> {
}