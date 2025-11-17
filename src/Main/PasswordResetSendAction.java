package src.Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class PasswordResetSendAction {
	public void execute(HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		req.getRequestDispatcher("password_reset_send.jsp").forward(req, res);
	}
}
