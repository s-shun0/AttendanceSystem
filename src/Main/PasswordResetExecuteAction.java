package src.Main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Main/PasswordResetExecute.action")
public class PasswordResetExecuteAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = request.getParameter("token");
        String newPassword = request.getParameter("password");

        // token に対応するメールアドレスを DB から取得（本来は必要）
        // email = DBからtokenで検索 ...

        boolean isValidToken = true; // 本来はtoken照合

        if (!isValidToken || token == null) {
            request.setAttribute("error", "無効なリセットURLです。");
            request.getRequestDispatcher("/main/common/error.jsp").forward(request, response);
            return;
        }

        // ★DBでパスワード更新（本来の処理を書く）
        // UPDATE users SET password = ? WHERE email = ?

        request.setAttribute("message", "パスワードを更新しました。");
        request.getRequestDispatcher("/main/common/reset_complete.jsp")
                .forward(request, response);
    }
}
