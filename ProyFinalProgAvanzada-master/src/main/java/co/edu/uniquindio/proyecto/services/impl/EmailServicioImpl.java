package co.edu.uniquindio.proyecto.services.impl;

import co.edu.uniquindio.proyecto.DTO.EmailDTO;
import co.edu.uniquindio.proyecto.services.interfaces.I_EmailServicie;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class EmailServicioImpl implements I_EmailServicie {


    @Override
    @Async
    public void enviarCorreo(EmailDTO emailDTO) throws Exception {

// El método withPlainText() se podría reemplazar por withHTMLText() si el cuerpo del email lleva etiquetas HTML.
        Email email = EmailBuilder.startingBlank()
                .from("proyfinalprogavanzada@gmail.com")
                .to(emailDTO.destinatario())
                .withSubject(emailDTO.asunto())
                .withPlainText(emailDTO.mensaje())
                .buildEmail();


        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "proyfinalprogavanzada@gmail.com", "gwjzviheocmsxsux")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {


            mailer.sendMail(email);
        }


    }


}
