package com.example.dxnima.zhidao.util;


import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * javamail 发送邮件
 * @author Champion Wong
 * Message.addRecipient(Message.Recipient recipient, Address address); 发邮件的时候指定收件人和收件人的角色
 * Message.RecipientType.TO 收件人
 * Message.RecipientType.CC 抄送，即发邮件的时候顺便给另一个人抄一份，不用回复！但是，上边的收件人可以看到你都抄送给了谁
 * Message.RecipientType.BCC 暗送，也是发邮件的时候顺便给另一个人暗发一份，但是，不同于上边的是，收件人不能看到你都暗送给了谁
 * Created by DXnima on 2019/4/21.
 */
public class EmailUtil {
    private static final String sendUserName = "2698849664@qq.com";// 发送邮件需要连接的服务器的用户名

    private static final String sendPassword = "oaqczqlvsqzmdgdg";// 发送邮件需要连接的服务器的密码

    private static final String sendProtocol = "smtp";// 发送邮件使用的端口

    private static final String sendHostAddress = "smtp.qq.com";// 发送邮件使用的服务器的地址

    public void test() throws MessagingException {

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");// 服务器需要认证
        properties.setProperty("mail.transport.protocol", sendProtocol);// 声明发送邮件使用的端口
        properties.setProperty("mail.host", sendHostAddress);// 发送邮件的服务器地址

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sendUserName, sendPassword);
            }
        });
        session.setDebug(true);//在后台打印发送邮件的实时信息

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(sendUserName));
        message.setSubject("Demo2JavaCode发送邮件测试，采用Authenticator");// 设置主题
        message.setRecipients(Message.RecipientType.TO, InternetAddress
                .parse("1370131288@qq.com,2698849664@qq.com"));// 发送
        message
                .setContent(
                        "<span style=\"font-size:20px; color:#FFCCFF\" mce_style=\"font-size:20px; color:#FFCCFF\">如果您看到，证明测试成功了！</span>",
                "text/html;charset=gbk");

        Transport.send(message);//发送邮件
    }

    public  void test1() throws MessagingException {

        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "ture");//服务器需要认证
        properties.setProperty("mail.transport.protocol", sendProtocol);//声明发送邮件使用的端口

        Session session = Session.getInstance(properties);
        session.setDebug(true);//同意在当前线程的控制台打印与服务器对话信息

        Message message = new MimeMessage(session);//构建发送的信息
        message.setSubject("你好，我是Champion.Wong！");//信息内容
        message.setFrom(new InternetAddress(sendUserName));//发件人

        Transport transport = session.getTransport();
        transport.connect(sendHostAddress,25, sendUserName, sendPassword);//连接发件人使用发件的服务器
        transport.sendMessage(message, new Address[]{new InternetAddress("1370131288@qq.com")});//接受邮件
        transport.close();
    }
}