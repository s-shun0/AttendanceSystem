package Main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassNum;
import dao.ClassNumDao;
import tool.Action;

public class ClassSelectExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//ローカル変数の宣言 1
		int classnum;

		//リクエストパラメータ―の取得 2
		String classnumstr = req.getParameter("classnum");// クラス番号
		classnum = Integer.parseInt(classnumstr);

		//DBからクラスに基づいたデータの取得
		ClassNumDao classnumDao = new ClassNumDao();
		ClassNum classnum = classnumDao.get(classnum);
	}

}