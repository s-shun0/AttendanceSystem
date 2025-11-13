package Main;

import java.io.IOException;
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

public class PasswordResetSendExecuteAction {

    public void execute(HttpServletRequest req, HttpServletResponse res)
            throws Exception {

        // 入力されたメールアドレスを取得
        String toEmail = req.getParameter("email");

        // リセット用URLを作成（例）
        String resetLink = "http://localhost:8080/attendsystem/password_reset_form.jsp?email=" + toEmail;

        // メール送信設定
        final String fromEmail = "あなたの送信用メールアドレス@gmail.com"; // 差出人
        final String password = "アプリパスワード"; // Gmailのアプリパスワードを使う（普通のパスワードは不可）

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // 認証付きセッションを作成
        Session session = Session.getInstance(props,
            new jakarta.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(fromEmail, password);
                }
            });

        try {
            // メールの作成
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail, "出席管理システム"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject("【出席管理システム】パスワードリセットのご案内");

            String content = "以下のリンクからパスワードをリセットしてください。\n\n" + resetLink + "\n\n"
                           + "※このメールに心当たりがない場合は削除してください。";
            message.setText(content);

            // メール送信
            Transport.send(message);

            // 送信成功 → 完了画面へフォワード
//            req.setAttribute("message", "リセットメールを送信しました。");
//            req.getRequestDispatcher("password_reset_complete.jsp").forward(req, res);

        } catch (MessagingException e) {
            e.printStackTrace();
            req.setAttribute("error", "メール送信に失敗しました。");
            req.getRequestDispatcher("password_reset_send.jsp").forward(req, res);
        } catch (IOException e) {
            e.printStackTrace();
            req.setAttribute("error", "メール送信に失敗しました。");
            req.getRequestDispatcher("password_reset_send.jsp").forward(req, res);
        }
    }
}
