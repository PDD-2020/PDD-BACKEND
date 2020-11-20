package projeto.cliente.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.cliente.entity.Pedido;
import projeto.cliente.exception.ObjectNotFoundException;
import projeto.cliente.repository.PedidoRepository;

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
        pedido.setCliente(clienteService.findById(pedido.getCliente().getId()));
        pedido = pedidoRepository.insert(pedido);

        Double quantidadePedidoByCliente = countPedidoByCliente(pedido.getCliente().getId());
        LOG.info(String.valueOf(quantidadePedidoByCliente));
        if (quantidadePedidoByCliente % 10 == 0){
            emailService.enviarAvisoPromocao(pedido);
        }

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
        updatedPedido.setProdutos(pedido.getProdutos());
        updatedPedido.setTipoInsumo(pedido.getTipoInsumo());
    }

    public Double countPedidoByCliente(String idClente){
        return pedidoRepository.countPedidoByCliente(idClente);
    }

}
