package projeto.cliente.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import projeto.cliente.entity.Cliente;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class ClienteDAO {

    private final MongoOperations mongoOperation;

    public String updateCliente(Long id, Cliente cliente) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Cliente clienteUpdate = mongoOperation.findOne(query, Cliente.class);
        if (!Objects.isNull(clienteUpdate)) {
            Update update = new Update();
            update.set("name", cliente.getName());
            update.set("endereco", cliente.getEndereco());
            update.set("numero", cliente.getNumero());
            update.set("email", cliente.getEmail());

            mongoOperation.updateFirst(query, update, Cliente.class);
            return "Cliente atualizado com sucesso.";
        }
        return "Não foi possível efetuar a atualização, favor entre em contato com o administrador.";
    }
}
