package com.base.utils;

import org.apache.commons.mail.HtmlEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName  SendMailUtils
 * Description    邮件发送工具类
 * Author F
 * Date   2021/6/4 9:24
 * Version    1.0
 */
@Component
public class SendMailUtils {

    @Value("${spring.mail.fromName}")
    private String fromName;

    @Value("${spring.mail.charSet}")
    private String charSet;

    @Value("${spring.mail.from}")
    private String from;

    @Value("${spring.mail.password}")
    private String password;

    private static Map<String, String> hostMap = new HashMap<>();

    static {
        hostMap.put("smtp.126", "smtp.126.com");
        hostMap.put("smtp.qq", "smtp.qq.com");
        hostMap.put("smtp.163", "smtp.163.com");
        hostMap.put("smtp.sina", "smtp.sina.com.cn");
        hostMap.put("smtp.tom", "smtp.tom.com");
        hostMap.put("smtp.263", "smtp.263.net");
        hostMap.put("smtp.yahoo", "smtp.mail.yahoo.com");
        hostMap.put("smtp.hotmail", "smtp.live.com");
        hostMap.put("smtp.gmail", "smtp.gmail.com");
        hostMap.put("smtp.port.gmail", "465");
        hostMap.put("smtp.weichai", "mail.weichai.com");
        hostMap.put("smtp.port.weichai", "25");
    }

    private String getHost(String email) throws Exception {
        Pattern pattern = Pattern.compile("\\w+@(\\w+)(\\.\\w+){1,2}");
        Matcher matcher = pattern.matcher(email);
        String key = "unSupportEmail";
        if (matcher.find()) {
            key = "smtp." + matcher.group(1);
        }
        if (hostMap.containsKey(key)) {
            return hostMap.get(key);
        } else {
            throw new Exception("unSupportEmail");
        }
    }

    public int getSmtpPort(String email) {
        Pattern pattern = Pattern.compile("\\w+@(\\w+)(\\.\\w+){1,2}");
        Matcher matcher = pattern.matcher(email);
        String key = "unSupportEmail";
        if (matcher.find()) {
            key = "smtp.port." + matcher.group(1);
        }
        if (hostMap.containsKey(key)) {
            return Integer.parseInt(hostMap.get(key));
        } else {
            return 25;
        }
    }


    /**
     * @dscription 发送普通邮件
     */
    public void sendCommonMail(String targetAddress, String subject, String innerMessage) throws Exception {
        HtmlEmail htmlEmail = new HtmlEmail();
        htmlEmail.setHostName(getHost(from));
        htmlEmail.setSmtpPort(getSmtpPort(from));
        htmlEmail.setCharset(charSet);
        htmlEmail.addTo(targetAddress);
        htmlEmail.setFrom(from, fromName);
        htmlEmail.setAuthentication(from, password);
        htmlEmail.setSubject(subject);
        htmlEmail.setMsg(innerMessage);
        htmlEmail.send();
    }

    /**
     * @dscription 带附件发送邮件
     **/
    public void sendMailWithAttachment(String targetAddress, String subject, String innerMessage, List<String> attachmentList) throws Exception{
        Properties properties = new Properties();

        properties.put("mail.smtp.host", getHost(from));
        properties.put("mail.smtp.auth", "true");
        MimeMessage message = new MimeMessage(Session.getInstance(properties,
                new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                from, password);
                    }
                }));
        message.setFrom(new InternetAddress(from, fromName, charSet));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(
                targetAddress));

        message.setSubject(subject);
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(innerMessage, "text/html;charset=utf-8");

        // MimeMultipart类是一个容器类，包含MimeBodyPart类型的对象
        Multipart mainPart = new MimeMultipart();
        mainPart.addBodyPart(messageBodyPart);

        // 存在附件
        for (String s : attachmentList) {
            messageBodyPart = new MimeBodyPart();
            File file = new File(s);
            if (file.exists()) {
                // 得到数据源
                FileDataSource fileDataSource = new FileDataSource(file);
                // 得到附件本身并至入BodyPart
                messageBodyPart.setDataHandler(new DataHandler(fileDataSource));
                // 得到文件名同样至入BodyPart
                messageBodyPart.setFileName(MimeUtility.encodeText(file.getName()));
                mainPart.addBodyPart(messageBodyPart);
            }
        }

        // 将MimeMultipart对象设置为邮件内容
        message.setContent(mainPart);
        // 发送邮件
        Transport.send(message);
    }

}

