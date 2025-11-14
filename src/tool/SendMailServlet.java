package tool;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sendMail")
public class SendMailServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String to = request.getParameter("to");
        String subject = request.getParameter("subject");
        String text = request.getParameter("text");

        try {
            MailSender.sendMail(to, subject, text);
            response.getWriter().println("送信成功！");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("送信失敗");
        }
    }
}
