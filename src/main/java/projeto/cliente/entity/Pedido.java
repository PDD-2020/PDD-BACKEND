package projeto.cliente.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Document(collection = "pedidos")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Pedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    @DBRef
    private Cliente cliente;
    @DBRef
    private List<Produto> produtos = new ArrayList<>();
    private Number tipoInsumo;

    public String toString(){
        StringBuilder builder = new StringBuilder();
        builder.append("Olá, " + getCliente().getEmail());
        builder.append("! Você completou 10 pedidos. Favor entrar em contato para solicitar seu prato extra.");
        return builder.toString();
    }

}
