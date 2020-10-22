package projeto.cliente.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor

public class StandartError implements Serializable {

    private static final long serialVersionUID = 1l;

    private Long timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;

    public StandartError(){}

}
