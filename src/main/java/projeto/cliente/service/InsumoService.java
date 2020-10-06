package projeto.cliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.cliente.entity.Insumo;
import projeto.cliente.exception.ObjectNotFoundException;
import projeto.cliente.repository.InsumoRepository;

import java.util.List;

@Service
public class InsumoService {

    @Autowired
    InsumoRepository insumoRepository;

    public List<Insumo> findAll(){
        return insumoRepository.findAll();
    }

    public String create(Insumo insumo) {
        insumoRepository.save(insumo);
        return "Insumo incluído com sucesso.";
    }

    public Insumo findById(String id) {
        return insumoRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Insumo não encontrado"));

    }

}
