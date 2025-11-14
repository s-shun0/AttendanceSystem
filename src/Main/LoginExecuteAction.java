package Main;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.User;
import dao.UserDao;
import tool.Action;




public class LoginExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		//ローカル変数の宣言 1
		String url = "";
		int id;
		String password = "";


		//リクエストパラメータ―の取得 2
		String idstr = req.getParameter("id");// 教員ID
		id = Integer.parseInt(idstr);
		password = req.getParameter("password");//パスワード

		//DBからデータ取得 3
		UserDao uDao = new UserDao();
		User user = uDao.login(id,password);

		//ビジネスロジック 4
		//DBへデータ保存 5
		//レスポンス値をセット 6
		//フォワード 7
		//条件で手順4~7の内容が分岐
		if (user != null) {// 認証成功の場合
			// セッション情報を取得
			HttpSession session = req.getSession(true);
			// 認証済みフラグを立てる
			user.setAuthenticated(true);
			// セッションにログイン情報を保存
			session.setAttribute("user", user);

			//リダイレクト
			url = "main/Menu.action";
			res.sendRedirect(url);
		} else {
			// 認証失敗の場合
			// エラーメッセージをセット
			List<String> errors = new ArrayList<String>();
			errors.add("IDまたはパスワードが確認できませんでした");
			req.setAttribute("errors", errors);
			// 入力された教員IDをセット
			req.setAttribute("id", id);

			//フォワード
			url = "login.jsp";
			req.getRequestDispatcher(url).forward(req, res);
		}

	}

}

