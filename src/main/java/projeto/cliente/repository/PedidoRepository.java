package projeto.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projeto.cliente.entity.Pedido;

import java.util.List;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {

    List<Pedido> findAll();
}
