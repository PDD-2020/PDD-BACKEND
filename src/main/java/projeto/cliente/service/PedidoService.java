package projeto.cliente.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
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

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido findById(String id) {
        return pedidoRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Pedido não encontrado"));
    }

    public Pedido insert(Pedido pedido){
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
    }

    public Long countByPedidoCliente(Long idCliente){
        return pedidoRepository.buscarPedidoPorCliente(idCliente);
    }

}
