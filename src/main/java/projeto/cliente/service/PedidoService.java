package projeto.cliente.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import projeto.cliente.entity.Cliente;
import projeto.cliente.entity.Pedido;
import projeto.cliente.exception.ObjectNotFoundException;
import projeto.cliente.repository.ClienteRepository;
import projeto.cliente.repository.PedidoRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EmailService emailService;

    private Logger LOG = LoggerFactory.getLogger(PedidoService.class);

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido findById(String id) {
        return pedidoRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Pedido não encontrado"));
    }

    public Pedido insert(Pedido pedido){
        pedido.setId(null);
        //pedido.setCliente(clienteService.get(pedido.getCliente().getId()));
        pedido = pedidoRepository.insert(pedido);
        emailService.enviarAvisoPromocao(pedido);
        return pedido;
    }

    public void delete(String id){
        findById(id);
        pedidoRepository.deleteById(id);
    }

    public Pedido update(Pedido pedido){
        Pedido updatedPedido = findById(pedido.getId());
        updateData(updatedPedido, pedido);
        return pedidoRepository.save(updatedPedido);
    }

    private void updateData(Pedido updatedPedido, Pedido pedido) {
        updatedPedido.setCliente(pedido.getCliente());
        updatedPedido.setInsumo(pedido.getInsumo());
        updatedPedido.setProdutos(pedido.getProdutos());
    }

    public Long countPedidoByCliente(Long idClente){
        Long quantidadePedidoByCliente = pedidoRepository.countPedidoByCliente(idClente);

        if (quantidadePedidoByCliente % 10 == 0){
            LOG.info("Este cliente completou 10 pedidos.");
        }
        return quantidadePedidoByCliente;
    }

}
