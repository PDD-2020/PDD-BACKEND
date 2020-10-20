package projeto.cliente.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.cliente.entity.Pedido;
import projeto.cliente.repository.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {

    @Autowired
    PedidoRepository pedidoRepository;

    public List<Pedido> findAll(){
        return pedidoRepository.findAll();
    }

    public Pedido insert(Pedido pedido){
        return pedidoRepository.insert(pedido);
    }

}
