package projeto.cliente.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "insumo")

@Data
@AllArgsConstructor

public class Insumo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String tipoEmbalagem;

    public Insumo(){}
}
