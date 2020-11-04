package projeto.cliente.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import projeto.cliente.entity.Prato;
import projeto.cliente.service.PratoService;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/prato")
@CrossOrigin
public class PratoController {

    private final PratoService service;

    public PratoController(PratoService service){ this.service = service;}

    @GetMapping
    public List<Prato> get(@RequestParam(required = false) Long id){ return this.service.get(id);}

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody Prato prato,
                         @RequestParam(value = "imagem", required = true) MultipartFile imagem) throws IOException {
        return this.service.create(prato, imagem);}

    @PutMapping("/{id}")
    public String updatePrato(@RequestBody Prato prato,
                              @RequestParam(value = "imagem", required = false) MultipartFile imagem,
                              @PathVariable Long id){
        return this.service.updatePrato(prato, imagem, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void delete(@PathVariable Long id) { this.service.delete(id);
    }
}
