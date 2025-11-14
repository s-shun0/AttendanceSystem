package Main;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import tool.Action; // ← 必須

public class PasswordResetSendExecuteAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        // 入力されたメールアドレス
        String toEmail = req.getParameter("email");

        // リセットURL（例）
        String resetLink = "http://localhost:8080/attendsystem/password_reset_form.jsp?email=" + toEmail;

        // メール送信処理
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props,
                new jakarta.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("あなたのメールアドレス", "アプリパスワード");
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("あなたのメールアドレス"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("パスワードリセット");
            message.setText("以下のURLからパスワードを再設定してください：\n" + resetLink);

            Transport.send(message);

            req.setAttribute("message", "メールを送信しました。");
            req.getRequestDispatcher("main/common/password_reset_send.jsp").forward(req, res);

        } catch (MessagingException e) {
            e.printStackTrace();
            req.setAttribute("error", "メール送信に失敗しました。");
            req.getRequestDispatcher("main/common/password_reset_send.jsp").forward(req, res);
        }
    }
}
