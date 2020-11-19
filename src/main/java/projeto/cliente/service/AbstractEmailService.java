package projeto.cliente.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import projeto.cliente.entity.Pedido;

import java.util.Date;

public abstract class AbstractEmailService implements EmailService{
    @Value("${default.sender}")
    private String sender;

    @Override
    public void enviarAvisoPromocao(Pedido pedido) {
        SimpleMailMessage message = prepareSimpleMailMessageFromPedido(pedido);
        enviarEmail(message);
    }

    protected SimpleMailMessage prepareSimpleMailMessageFromPedido(Pedido pedido){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(pedido.getCliente().getEmail());
        message.setFrom(sender);
        message.setSubject("Pedido extra!");
        message.setSentDate(new Date(System.currentTimeMillis()));
        message.setText(pedido.toString());
        return message;
    }

}
