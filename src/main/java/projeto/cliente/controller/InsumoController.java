package projeto.cliente.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projeto.cliente.entity.Insumo;
import projeto.cliente.service.InsumoService;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/insumo")

public class InsumoController {

    @Autowired
    InsumoService insumoService;

    @GetMapping
    public List<Insumo> findAll(){
        return insumoService.findAll();
    }

    @GetMapping(value ="/{id}")
    public ResponseEntity<Insumo> findById(@PathVariable String id){
        Insumo insumo = insumoService.findById(id);
        return ResponseEntity.ok().body(insumo);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@Valid @RequestBody Insumo insumo) {
        return insumoService.create(insumo);
    }


    @DeleteMapping(value ="/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(@PathVariable String id){
        insumoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value ="/{id}")
    public ResponseEntity<Void> update(@RequestBody Insumo insumo, @PathVariable String id){
        insumo.setId(id);
        insumo = insumoService.update(insumo);
        return ResponseEntity.noContent().build();
    }



}
