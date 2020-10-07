package projeto.cliente.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import projeto.cliente.entity.Prato;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class PratoDAO {

    private final MongoOperations mongoOperations;

    public String updatePrato(Prato prato, MultipartFile imagem, Long id){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));

        Prato pratoUpdate = mongoOperations.findOne(query, Prato.class);
        if (!Objects.isNull(pratoUpdate)){
            Update update = new Update();
            update.set("pratoNome", prato.getNomePrato());
            update.set("pratoDesc", prato.getDescricao());
            update.set("pratoImage", imagem);

            mongoOperations.updateFirst(query, update, Prato.class);
            return "Prato atualizado com sucesso";
        }
        return "Não foi possivel atualizar o prato";
    }
}
