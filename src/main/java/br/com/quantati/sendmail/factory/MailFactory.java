package br.com.quantati.sendmail.factory;

import java.io.Serializable;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.mail.Session;

/**
 *
 * @author carlos
 */
public class MailFactory {
  
  @Inject
  private Session mailSession;
  

  @Produces
  public Mail createMail() {
    Mail mail = new Mail(mailSession);
    return mail;
  }
}
