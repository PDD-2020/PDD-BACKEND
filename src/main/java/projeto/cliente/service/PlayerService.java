package projeto.cliente.service;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import projeto.cliente.dao.PlayerDAO;
import projeto.cliente.entity.Player;
import projeto.cliente.repository.PlayerRepository;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PlayerService {

    @Getter
    @Setter
    private Integer length = 0;

    private static final String TEXT_PLAIN = "text/plain";

    private final PlayerRepository repository;
    private final PlayerDAO playerDAO;
    private final SequenceGeneratorService serviceId;


    public List<Player> get(Long id) {
        if (Objects.isNull(id)) {
            return this.repository.findAll();
        } else {
            List<Player> players = new ArrayList<>();
            players.add(this.repository.findById(id).orElse(new Player()));

            if (players.get(0).getId() == 0) {
                players.clear();
            }
            return players;
        }
    }

    public String create(Player player) {
        player.setId(serviceId.generateSequence(Player.SEQUENCE_NAME));
        this.repository.save(player);
        return "Player successfully included.";
    }

    public String updateAge(Long id, Player player) {
        return this.playerDAO.updatePlayer(id, player);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    private Player buildPlayer(String[] line) {
        Player player = new Player();
        player.setId(serviceId.generateSequence(Player.SEQUENCE_NAME));
        player.setName(line[0]);
        player.setAge(Integer.parseInt(line[1]));
        return player;
    }

    private String getbufferedReaderString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        return bufferedReader.readLine();
    }

}
