package com.sakura.sendmail.main;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * 发送邮件工具类
 */
public class DoMailBusiness {

    public static void main(String[] args) throws MessagingException {
        String tto = "v_wangjiqing@baidu.com";
        String title = "这是一封测试邮件";
        String tcontent = "<font color='red'>这是一封测试邮件</font>";

        Properties props = new Properties();
        props.put("mail.smtp.host", "127.0.0.1");
        props.put("mail.smtp.auth", true);
        Session session = Session.getInstance(props);
        session.setDebug(true);

        MimeMessage message = new MimeMessage(session);
        InternetAddress from = new InternetAddress("baidu.depth.logger.warn");
        message.setFrom(from);

        InternetAddress to = new InternetAddress(tto);
        message.setRecipient(Message.RecipientType.TO, to);

        message.setSubject(title);
        message.setSentDate(new Date());

        // 给消息设置内容
        BodyPart mdp = new MimeBodyPart();
        mdp.setContent(tcontent, "text/html;chartset=utf-8");
        Multipart test = new MimeMultipart();
        test.addBodyPart(mdp);
        message.setContent(test);

        message.saveChanges();
        Transport transport = session.getTransport("smtp");
        transport.connect("127.0.0.1", "", "");

        MailcapCommandMap mc = (MailcapCommandMap)CommandMap.getDefaultCommandMap();
        mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
        mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
        mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
        mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
        mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
        CommandMap.setDefaultCommandMap(mc);
        Thread.currentThread().setContextClassLoader(DoMailBusiness.class.getClassLoader());

        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
