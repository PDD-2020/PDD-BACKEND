package projeto.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.web.multipart.MultipartFile;
import projeto.cliente.entity.Cliente;
import projeto.cliente.entity.Prato;

import java.util.List;

public interface PratoRepository extends MongoRepository<Prato, Long> {

    List<Prato> findAll();

    void save(Prato prato, MultipartFile imagem);
}
