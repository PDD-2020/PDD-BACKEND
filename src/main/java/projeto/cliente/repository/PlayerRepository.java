package projeto.cliente.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import projeto.cliente.entity.Player;

import java.util.List;

public interface PlayerRepository extends MongoRepository<Player, Long> {

    List<Player> findAll();
}
