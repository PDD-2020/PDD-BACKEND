package projeto.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projeto.cliente.entity.Cliente;

import java.util.List;

@Repository
public interface ClienteRepository extends MongoRepository<Cliente, String> {

}
