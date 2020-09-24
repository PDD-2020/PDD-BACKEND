package projeto.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import projeto.cliente.entity.Cliente;

import java.util.List;

public interface ClienteRepository extends MongoRepository<Cliente, Long> {

    List<Cliente> findAll();
}
