package projeto.cliente.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NotNull (message = "Prato é obrigatório.")
@Document(collection = "prato")
public class Prato {

    @Transient
    public static final String SEQUENCE_NAME = "prato_sequence";

    @Id
    private long id;

    @NotBlank(message = "O nome do prato é obrigatório")
    private String nomePrato;

    @NotBlank(message = "A descrição do prato é obrigatória.")
    private String descricao;

    @NotNull(message = "É necessário adicionar uma foto do prato")
    private MultipartFile imagem;
}
