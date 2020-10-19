package projeto.cliente.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Produto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private Double valor;
    private String descricao;
    private MultipartFile foto;

    public Produto(){}

}
