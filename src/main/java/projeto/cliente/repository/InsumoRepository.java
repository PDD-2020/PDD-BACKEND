package projeto.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import projeto.cliente.entity.Insumo;

import java.util.List;

@Repository
public interface InsumoRepository extends MongoRepository<Insumo, String> {

}
