package projeto.cliente.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import projeto.cliente.dao.ClienteDAO;
import projeto.cliente.entity.Cliente;
import projeto.cliente.exception.ObjectNotFoundException;
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

    private final ClienteRepository clienteRepository;
    private final ClienteDAO clienteDAO;

    public List<Cliente> findAll(){
        return clienteRepository.findAll();
    }


    public Cliente findById(String id){
        return clienteRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Cliente não encontrado"));
    }

    public Cliente create(Cliente cliente) {
        return clienteRepository.insert(cliente);
    }

    public String updateAge(Long id, Cliente cliente) {
        return this.clienteDAO.updateCliente(id, cliente);
    }

    public void delete(String id) {
        findById(id);
        clienteRepository.deleteById(id);
    }

}
