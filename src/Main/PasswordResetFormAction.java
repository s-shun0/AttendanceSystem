package src.Main;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Main/PasswordResetForm.action")
public class PasswordResetFormAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String token = request.getParameter("token");

        // DBに保存した token が正しいかチェック（ここでは省略）
        boolean isValid = true; // 本来は DBで token が存在するか判定

        if (!isValid || token == null) {
            request.setAttribute("error", "無効なURLです。");
            request.getRequestDispatcher("/main/common/error.jsp").forward(request, response);
            return;
        }

        // JSPに token を渡す
        request.setAttribute("token", token);

        // パスワード再設定画面へ遷移
        request.getRequestDispatcher("/main/common/password_reset_form.jsp")
                .forward(request, response);
    }
}
