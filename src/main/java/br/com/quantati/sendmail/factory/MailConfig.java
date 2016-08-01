/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quantati.sendmail.factory;

import br.com.quantati.sendmail.annotations.MailPassword;
import br.com.quantati.sendmail.annotations.MailPort;
import br.com.quantati.sendmail.annotations.MailSender;
import br.com.quantati.sendmail.annotations.MailServer;
import javax.enterprise.inject.Produces;

/**
 *
 * @author carlos
 */
public class MailConfig {
  
  @Produces @MailServer
  private String mailServer = "smtp.gmail.com";
  
  @Produces @MailPort
  private String mailPort = "587";
  
  @Produces @MailSender
  private String sender = "seuemail@gmail.com";
 
  @Produces @MailPassword
  private String password = "suasenhaaqui";
 
  
}
