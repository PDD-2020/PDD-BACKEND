package projeto.cliente.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import projeto.cliente.dao.ClienteDAO;
import projeto.cliente.entity.Cliente;
import projeto.cliente.repository.ClienteRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Getter
    @Setter
    private Integer length = 0;

    private final ClienteRepository repository;
    private final ClienteDAO clienteDAO;
    private final SequenceGeneratorService serviceId;


    public List<Cliente> get(Long id) {
        if (Objects.isNull(id)) {
            return this.repository.findAll();
        } else {
            List<Cliente> clientes = new ArrayList<>();
            clientes.add(this.repository.findById(id).orElse(new Cliente()));

            if (clientes.get(0).getId() == 0) {
                clientes.clear();
            }
            return clientes;
        }
    }

    public String create(Cliente cliente) {
        cliente.setId(serviceId.generateSequence(Cliente.SEQUENCE_NAME));
        this.repository.save(cliente);
        return "Cliente successfully included.";
    }

    public String updateAge(Long id, Cliente cliente) {
        return this.clienteDAO.updateCliente(id, cliente);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    private Cliente buildCliente(String[] line) {
        Cliente cliente = new Cliente();
        cliente.setId(serviceId.generateSequence(Cliente.SEQUENCE_NAME));
        cliente.setName(line[0]);
        cliente.setAge(Integer.parseInt(line[1]));
        return cliente;
    }

}
