package src.Main;

import java.io.IOException;
import java.util.Properties;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@WebServlet("/Main/PasswordResetSendExecute.action")
public class PasswordResetSendExecuteAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String messageText = "";

        // DBでユーザー確認（簡略化）
        boolean userExists = true; // 実際はDB照合してください

        if (userExists) {
            String token = UUID.randomUUID().toString();

            // DBにトークンを保存する処理（省略）
            String resetUrl = "https://yourdomain.com/attendsystem/Main/PasswordResetForm.action?token=" + token;

            // Gmail SMTP設定
            final String from = "your@gmail.com";               // Gmailアドレス
            final String password = "生成されたアプリパスワード"; // アプリパスワード

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, password);
                }
            });

            try {
                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress(from));
                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
                message.setSubject("パスワードリセットのご案内");
                message.setText("以下のURLからパスワードをリセットしてください。\n" + resetUrl);

                Transport.send(message);
                messageText = "メールを送信しました。";

            } catch (MessagingException e) {
                e.printStackTrace();
                messageText = "メール送信に失敗しました。";
            }
        } else {
            messageText = "指定されたメールアドレスは存在しません。";
        }

        // JSPにメッセージを渡す
        request.setAttribute("message", messageText);
        request.getRequestDispatcher("/main/common/password_reset_send.jsp").forward(request, response);
    }
}
