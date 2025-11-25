package Main;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.UserDao;
import tool.Action;

public class ClassSelectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
<<<<<<< HEAD
	    try {
	        // リクエストパラメータ―の取得
	        int classnums = Integer.parseInt(req.getParameter("classnums"));
=======
		//ローカル変数の宣言 1
>>>>>>> branch 'master' of https://github.com/s-shun0/AttendanceSystem.git

<<<<<<< HEAD
	        // DBからクラスに基づいたデータの取得
	        UserDao userDao = new UserDao();
	        List<User> classnum = userDao.class_(classnums);
=======
		//リクエストパラメータ―の取得 2
		String classnum = req.getParameter("classnum");// クラス番号
>>>>>>> branch 'master' of https://github.com/s-shun0/AttendanceSystem.git

<<<<<<< HEAD
	        // リクエストスコープに格納して JSP にフォワード
	        req.setAttribute("students", classnum);
	        req.getRequestDispatcher("attendance_tracker.jsp").forward(req, res);

	    } catch (Exception e) {
	        // 何らかのエラーが発生したら error.jsp に飛ばす
	        req.getRequestDispatcher("error.jsp").forward(req, res);
	    }
=======
		//DBからクラスに基づいたデータの取得
		ClassNumDao classnumDao = new ClassNumDao();
		ClassNum num = classnumDao.get(classnum);
>>>>>>> branch 'master' of https://github.com/s-shun0/AttendanceSystem.git
	}
}