package projeto.cliente.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockMailService extends AbstractEmailService{

    private Logger LOG = LoggerFactory.getLogger(MockMailService.class);

    @Override
    public void enviarEmail(SimpleMailMessage message) {
        LOG.info("Simulando envio de email...");
        LOG.info(message.toString());
        LOG.info("Email enviado");
    }
}
