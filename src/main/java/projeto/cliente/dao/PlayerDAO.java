package projeto.cliente.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import projeto.cliente.entity.Player;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PlayerDAO {

    private final MongoOperations mongoOperation;

    public String updatePlayer(Long id, Player player) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Player playerUpdate = mongoOperation.findOne(query, Player.class);
        if (!Objects.isNull(playerUpdate)) {
            Update update = new Update();
            update.set("name", player.getName());
            update.set("age", player.getAge());

            mongoOperation.updateFirst(query, update, Player.class);
            return "Update successfully.";
        }
        return "It was not possible to update, please contact the administration.";
    }
}
