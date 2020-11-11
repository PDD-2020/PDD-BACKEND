package projeto.cliente.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Document(collection = "produtos")
@Data
@AllArgsConstructor
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String nome;
    private Double valor;
    private String descricao;
    //private MultipartFile foto;

    public Produto(){}

}
