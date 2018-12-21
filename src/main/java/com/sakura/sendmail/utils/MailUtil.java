package com.sakura.sendmail.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.util.Date;

@Slf4j
@Service
public class MailUtil {

    @Resource
    private JavaMailSender mailSender;

    /**
     *  发送普通邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String[] to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("sakura.warn");
        simpleMailMessage.setSentDate(new Date());
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        try {
            log.info("开始发送普通邮件，收件人：" + to);
            mailSender.send(simpleMailMessage);
            log.info("普通邮件发送" + to + "成功....");
        } catch (Exception ex) {
            log.error("邮件发送" + to + "失败！失败原因：" + ex);
        }
    }

    /**
     * 发送HTML格式的邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String[] to, String subject, String content) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            log.info("开始发送带有Html格式的邮件，收件人：" + to);
            // true表示构建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("sakura.warn");
            helper.setSentDate(new Date());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            mailSender.send(message);
            log.info("带有Html格式邮件发送" + to + "成功....");
        } catch (Exception ex) {
            log.error("带有Html格式邮件发送" + to + "失败！失败原因：" + ex);
        }
    }

    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param path
     */
    public void sendAttachmentsMail(String[] to, String subject, String content, String path) {
        MimeMessage message = mailSender.createMimeMessage();
        try {
            log.info("开始发送带有附件格式的邮件，收件人：" + to);
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("sakura.warn");
            helper.setSentDate(new Date());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            FileSystemResource fileSystemResource = new FileSystemResource(new File(path));

            String fileName = path.substring(path.lastIndexOf(File.separator));
            helper.addAttachment(fileName, fileSystemResource);
            mailSender.send(message);
            log.info("带有附件邮件发送" + to + "成功....");
        } catch (Exception ex) {
            log.error("带有附件邮件发送" + to + "失败！失败原因：" + ex);
        }
    }
}
