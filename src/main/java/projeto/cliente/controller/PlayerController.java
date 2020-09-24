package projeto.cliente.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projeto.cliente.entity.Player;
import projeto.cliente.service.PlayerService;


import javax.validation.Valid;
import java.util.List;

@RestController()
@RequestMapping("/player")
public class PlayerController {

    private final PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @GetMapping
    public List<Player> get(@RequestParam(required = false) Long id) {
        return this.service.get(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody Player player) {
        return this.service.create(player);
    }

    @PutMapping("/{id}")
    public String updateAge(@RequestBody Player player,
                            @PathVariable Long id) {
        return this.service.updateAge(id, player);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) {
        this.service.delete(id);
    }
}
