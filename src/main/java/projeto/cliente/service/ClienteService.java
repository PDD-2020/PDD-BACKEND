package projeto.cliente.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import projeto.cliente.dao.ClienteDAO;
import projeto.cliente.entity.Cliente;
import projeto.cliente.entity.Pedido;
import projeto.cliente.repository.ClienteRepository;
import projeto.cliente.repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClienteService {

    @Getter
    @Setter
    private Integer length = 0;

    private final ClienteRepository repository;
    private final ClienteDAO clienteDAO;
    private final SequenceGeneratorService serviceId;
    private final PedidoRepository pedidoRepository;


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
        return "Cliente incluído com sucesso.";
    }

    public String updateAge(Long id, Cliente cliente) {
        return this.clienteDAO.updateCliente(id, cliente);
    }

    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    public String addPedido(String idPedido, String idCliente){
        Optional<Pedido> pedido = this.pedidoRepository.findById(idPedido);
        if(!pedido.isPresent()){
            return "O pedido informado não existe.";
        }
        return this.clienteDAO.addPedido(idCliente, pedido.get());
    }
}
