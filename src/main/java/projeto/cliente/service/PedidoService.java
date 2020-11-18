package projeto.cliente.service;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import projeto.cliente.entity.Cliente;
import projeto.cliente.entity.Pedido;
import projeto.cliente.exception.ObjectNotFoundException;
import projeto.cliente.repository.ClienteRepository;
import projeto.cliente.repository.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    private Logger LOG = LoggerFactory.getLogger(PedidoService.class);

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido findById(String id) {
        return pedidoRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Pedido não encontrado"));
    }

    public Pedido insert(Pedido pedido){
        pedido.setFidelidade(false);
        return pedidoRepository.insert(pedido);
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
        updatedPedido.setFidelidade(false);
    }

    public Long countPedidoByCliente(Long idClente){
        Long quantidadePedidoByCliente = pedidoRepository.countPedidoByCliente(idClente);

        Query query = new Query(Criteria.where("cliente.id").is(idClente));

        if (quantidadePedidoByCliente == 2){
            Update u = Update.update("fidelidade", "false").set("fidelidade", "true");
            //updateFidelidade(idClente);
            LOG.info("Este cliente completou 10 pedidos.");
        }
        return quantidadePedidoByCliente;

    }

    private Long updateFidelidade(Long idCliente){
        return pedidoRepository.updateFidelidade(idCliente);
    }
}
