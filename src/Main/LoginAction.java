package src.Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import src.tool.Action;

public class LoginAction extends Action {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
        // login.jsp にフォワード
        req.getRequestDispatcher("/login.jsp").forward(req, res);
    }
}
