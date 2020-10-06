package projeto.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import projeto.cliente.entity.Insumo;
import projeto.cliente.service.InsumoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/insumo")

public class InsumoController {

    @Autowired
    InsumoService insumoService;

    @GetMapping
    public List<Insumo> findAll(){
        return insumoService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody Insumo insumo) {
        return insumoService.create(insumo);
    }

}
