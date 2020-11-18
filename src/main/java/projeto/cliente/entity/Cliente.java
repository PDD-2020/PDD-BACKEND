package projeto.cliente.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Getter
@Setter
@NotNull(message = "cliente is required")
@Document(collection = "cliente")
public class Cliente {

    @Transient
    public static final String SEQUENCE_NAME = "cliente_sequence";

    @Id
    private long id;

    @NotBlank(message = "Nome é obrigatório")
    private String name;

    @NotBlank(message = "Endereço é obrigatório")
    private String endereco;

    @NotBlank(message = "Número é obrigatório")
    private String numero;

    private String email;

    private List<Pedido> pedidos;

    public Cliente() {

    }
}
