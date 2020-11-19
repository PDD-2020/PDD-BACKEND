package projeto.cliente.service;

import org.springframework.mail.SimpleMailMessage;
import projeto.cliente.entity.Pedido;

public interface EmailService {

    void enviarAvisoPromocao(Pedido pedido);

    void enviarEmail(SimpleMailMessage message);
}
