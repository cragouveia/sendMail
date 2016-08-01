package br.com.quantati.sendmail.bean;

import br.com.quantati.sendmail.factory.Mail;
import java.io.Serializable;
import javax.enterprise.inject.Model;
import javax.inject.Inject;

/**
 *
 * @author carlos
 */
@Model
public class MailBean implements Serializable {
  
  @Inject
  private Mail mail;
  
  public String sendMail() {
    mail.sendMessage("Teste de envio de email", "Carlos", "carlos@quantati.com.br", "Enviando email de teste");
    return "mail-send?faces-redirect=true";
  }
  
  public String sendMailComAnexo() {
    String[] files = new String[] {"/Users/carlos/Documents/Font Awesome Cheatsheet.pdf"};
    mail.sendMessageWithAttachment("Teste de envio de email", "Carlos", "carlos@quantati.com.br", 
            "Enviando email de teste", files);
    return "mail-send?faces-redirect=true";
  }
  
}
