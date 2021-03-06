package projeto.cliente.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import projeto.cliente.service.EmailService;
import projeto.cliente.service.MockMailService;
import projeto.cliente.service.SmtpEmailService;

@Configuration
public class TestConfig {

    @Bean
    public EmailService emailService(){
        return new SmtpEmailService();
    }

}
