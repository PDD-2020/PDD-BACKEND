package projeto.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projeto.cliente.entity.Pedido;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {
}
