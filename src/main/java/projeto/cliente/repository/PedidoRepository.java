package projeto.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import projeto.cliente.entity.Pedido;

import java.util.List;

@Repository
public interface PedidoRepository extends MongoRepository<Pedido, String> {

    @Query(value = "{'cliente.$id':?0}", count = true)
    Long countPedidoByCliente(Long idCliente);
}
