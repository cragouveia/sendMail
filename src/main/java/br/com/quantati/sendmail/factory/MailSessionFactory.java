package br.com.quantati.sendmail.factory;

import br.com.quantati.sendmail.annotations.MailPassword;
import br.com.quantati.sendmail.annotations.MailPort;
import br.com.quantati.sendmail.annotations.MailSender;
import br.com.quantati.sendmail.annotations.MailServer;
import java.util.Properties;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

/**
 *
 * @author carlos
 */
public class MailSessionFactory {

  @Inject
  @MailServer
  private String mailServer;
  @Inject
  @MailPort
  private String mailPort;
  @Inject
  @MailSender
  private String sender;
  @Inject
  @MailPassword
  private String password;

  @Produces
  public Session createMailSession() {
    Properties props = new Properties();
    // as 3 primeiras propriedades sao padrao para envio com SSL
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    // propriedades especificas do servidor 
    props.put("mail.smtp.host", mailServer);
    props.put("mail.smtp.port", mailPort);
    props.put("mail.smtp.socketFactory.port", mailPort);
    props.put("mail.from", sender);
    /// autenticando no servidor de email
    Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(sender, password);
      }
    });
    return mailSession;
  }


}
