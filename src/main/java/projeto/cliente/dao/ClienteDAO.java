package projeto.cliente.dao;

import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import projeto.cliente.entity.Cliente;
import projeto.cliente.entity.Pedido;

import java.util.ArrayList;
import java.util.Objects;

@Component
@AllArgsConstructor
public class ClienteDAO {

    private final MongoOperations mongoOperation;
    private final Query query;

    public String updateCliente(Long id, Cliente cliente) {
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

    public String addPedido(String id, Pedido pedido){
        Update update = new Update();
        query.addCriteria(Criteria.where("id").is(id));
        Cliente cliente = this.mongoOperation.findOne(query, Cliente.class);

        if (!Objects.isNull(cliente)){
            if (Objects.isNull(cliente.getPedidos())){
                cliente.setPedidos(new ArrayList<>());
            }

            boolean haveTen = cliente.getPedidos().size() == 10;
            if (haveTen) {
                System.out.print("Usuário atingiu 10 pedidos");
            }

            cliente.getPedidos().add(pedido);
            update.set("pedidos", cliente.getPedidos());
            this.mongoOperation.updateFirst(query, update, Cliente.class);
            return "Pedido adicionado com sucesso.";
        }
        return "Pedido não inserido";
    }
}
