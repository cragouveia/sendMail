/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.quantati.sendmail.factory;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author carlos
 */
public class Mail implements Serializable {

  private Session mailSession;
  private String userNameCopy;
  private String userNameCopyHidden;

  public Mail(Session mailSession) {
    this.mailSession = mailSession;
  }

  public String getUserNameCopy() {
    return userNameCopy;
  }

  public void setUserNameCopy(String userNameCopy) {
    this.userNameCopy = userNameCopy;
  }

  public String getUserNameCopyHidden() {
    return userNameCopyHidden;
  }

  public void setUserNameCopyHidden(String userNameCopyHidden) {
    this.userNameCopyHidden = userNameCopyHidden;
  }
  
  public void sendMessage(String subject, String userName, String userMail, String message) {
    Message msg = new MimeMessage(this.mailSession);
    try {
      String encoding = "text/html;charset=utf-8";
      msg.setHeader("Content-Type", encoding);
      msg.setSubject(subject);
      msg.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
      if (userMail.contains(",")) {
        msg.setRecipients(RecipientType.TO, InternetAddress.parse(userMail));
      } else {
        msg.setRecipient(RecipientType.TO, new InternetAddress(userMail, userName));
      }
      if (userNameCopy != null && !userNameCopy.equals("")) {
        if (userNameCopy.contains(",")) {
          msg.setRecipients(RecipientType.CC, InternetAddress.parse(userNameCopy));
        } else {
          msg.setRecipient(RecipientType.CC, new InternetAddress(userNameCopy, userNameCopy));
        }
      }
      if (userNameCopyHidden != null && !userNameCopyHidden.equals("")) {
        if (userNameCopyHidden.contains(",")) {
          msg.setRecipients(RecipientType.BCC, InternetAddress.parse(userNameCopyHidden));
        } else {
          msg.setRecipient(RecipientType.BCC, new InternetAddress(userNameCopyHidden, userNameCopyHidden));
        }
      }
      msg.setContent(message, encoding);
      Transport.send(msg);
    } catch (MessagingException me) {
      me.printStackTrace();
    } catch (UnsupportedEncodingException uee) {
      uee.printStackTrace();
    }
  }

  public void sendMessageWithAttachment(String subject, String userName, String userMail, String message, String[] files) {
    Message msg = new MimeMessage(this.mailSession);
    try {
      String encoding = "text/html;charset=utf-8";
      msg.setHeader("Content-Type", encoding);
      msg.setSubject(subject);
      msg.setFrom(new InternetAddress(mailSession.getProperty("mail.from")));
      if (userMail.contains(",")) {
        msg.setRecipients(RecipientType.TO, InternetAddress.parse(userMail));
      } else {
        msg.setRecipient(RecipientType.TO, new InternetAddress(userMail, userName));
      }
      if (userNameCopy != null && !userNameCopy.equals("")) {
        if (userNameCopy.contains(",")) {
          msg.setRecipients(RecipientType.CC, InternetAddress.parse(userNameCopy));
        } else {
          msg.setRecipient(RecipientType.CC, new InternetAddress(userNameCopy, userNameCopy));
        }
      }
      if (userNameCopyHidden != null && !userNameCopyHidden.equals("")) {
        if (userNameCopyHidden.contains(",")) {
          msg.setRecipients(RecipientType.BCC, InternetAddress.parse(userNameCopyHidden));
        } else {
          msg.setRecipient(RecipientType.BCC, new InternetAddress(userNameCopyHidden, userNameCopyHidden));
        }
      }
      //
      MimeBodyPart textPart = new MimeBodyPart();
      textPart.setContent(message, "text/html");
      //
      Multipart mp = new MimeMultipart();
      mp.addBodyPart(textPart);
      //
      for (String file : files) {
        MimeBodyPart attachFilePart = new MimeBodyPart();
        FileDataSource fds = new FileDataSource(file);
        attachFilePart.setDataHandler(new DataHandler(fds));
        attachFilePart.setFileName(fds.getName());
        mp.addBodyPart(attachFilePart);
      }
      //
      msg.setContent(mp, encoding);
      msg.saveChanges();
      //
      Transport.send(msg);
    } catch (MessagingException me) {
      me.printStackTrace();
    } catch (UnsupportedEncodingException uee) {
      uee.printStackTrace();
    }
  }

}
