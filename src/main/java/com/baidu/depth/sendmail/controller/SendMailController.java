package com.baidu.depth.sendmail.controller;

import com.baidu.depth.sendmail.utils.MailUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
public class SendMailController {

    @Resource
    private MailUtil mailUtil;

    @RequestMapping("/sendSimpleMail")
    public ResponseEntity<?> sendSimpleMail(@RequestParam("to") String[] to, @RequestParam("subject") String subject,
                                            @RequestParam("context") String context) {
        try {
            mailUtil.sendSimpleMail(to, subject, context);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/sendHtmlMail")
    public ResponseEntity<?> sendHtmlMail(@RequestParam("to") String[] to, @RequestParam("subject") String subject,
                                   @RequestParam("context") String context) {
        try {
            mailUtil.sendHtmlMail(to, subject, context);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping("/sendBlobMail")
    public ResponseEntity<?> sendBlobMail(@RequestParam("to") String[] to,
                                          @RequestParam("subject") String subject,
                                          @RequestParam("context") String context,
                                          MultipartFile file) {

        // 这里处理一个上传附件转化为inputstream的代码

        try {
            mailUtil.sendAttachmentsMail(to, subject, context, "");
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
