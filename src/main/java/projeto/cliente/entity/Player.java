package projeto.cliente.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NotNull(message = "player is required")
@Document(collection = "player")
public class Player {

    @Transient
    public static final String SEQUENCE_NAME = "player_sequence";

    @Id
    private long id;

    @NotBlank(message = "name is required")
    private String name;

    @NotNull(message = "name is required")
    private int age;

    public Player() {

    }
}
