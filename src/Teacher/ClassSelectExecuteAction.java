package Teacher;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;
import tool.Action;

public class ClassSelectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	    try {
	        // リクエストパラメータ―の取得
	        int classnums = Integer.parseInt(req.getParameter("classnums"));

	        // DBからクラスに基づいたデータの取得
	        UserDao userDao = new UserDao();
	        List<User> classnum = userDao.class_(classnums);

	        // リクエストスコープに格納して JSP にフォワード
	        req.setAttribute("students", classnum);
	        req.getRequestDispatcher("attendance_tracker.jsp").forward(req, res);

	    } catch (Exception e) {
	        // 何らかのエラーが発生したら error.jsp に飛ばす
	        req.getRequestDispatcher("error.jsp").forward(req, res);
	    }
	}
}