package br.com.senai.Notes.Service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
   private  String origin;

public EmailService(JavaMailSender mailSender) {
       this.mailSender = mailSender;
}

 public void enviarEmail(String destino, String novaSenha) {
       SimpleMailMessage menssage = new SimpleMailMessage();
        menssage.setFrom(origin);
        menssage.setTo(destino);
        menssage.setSubject("SENAI Notes - A SUA SENHA FOI REDEFINIDA COM SUCESSO");
        menssage.setText("OLÁ, \n\n SUA SENHA FOI REDEFINIDA COM SUCESSO!" +
         "SUA NOVA SENHA É: " + novaSenha);

        mailSender.send(menssage);
 }
}
