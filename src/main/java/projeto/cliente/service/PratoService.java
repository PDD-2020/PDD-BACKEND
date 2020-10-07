package projeto.cliente.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projeto.cliente.dao.PratoDAO;
import projeto.cliente.entity.Prato;
import projeto.cliente.repository.PratoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class PratoService {

    private final PratoRepository repository;
    private final PratoDAO pratoDAO;
    private final SequenceGeneratorService serviceId;

    public List<Prato> get(Long id){
        if (Objects.isNull(id)){
            return this.repository.findAll();
        } else {
            List<Prato> pratos = new ArrayList<>();
            pratos.add(this.repository.findById(id).orElse(new Prato()));

            if (pratos.get(0).getId() == 0){
                pratos.clear();
            }
            return pratos;
        }
    }

    public String create(Prato prato, MultipartFile imagem){
        prato.setId(serviceId.generateSequence(Prato.SEQUENCE_NAME));
        this.repository.save(prato, imagem);
        return "Prato salvo com sucesso";
    }

    public String updatePrato(Prato prato, MultipartFile imagem, Long id){
        return this.pratoDAO.updatePrato(prato, imagem, id);
    }

    public void delete (Long id){
        this.repository.deleteById(id);
    }
}
