package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import bean.Attendance;
import bean.User;


public class AttendanceDao extends Dao{

	private String baseSql="select * from attendance where id=? ";

	public boolean attend(int id,String password, ) throws Exception{

		Date date = new Date();
		//データベース用
		SimpleDateFormat  fm1 = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat  fm2 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		//遅刻・欠席の判定用
		SimpleDateFormat  check = new SimpleDateFormat(" hhmm");

		Connection connection = getConnection();
		PreparedStatement statement = null;



		try{
			//プリペアードスタートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql + conddition );
			//プリペアードスタートメントに学校コードをバインド
			statement.setInt(1,id);
			statement.setString(2,password);

			Attendance attend = new Attendance();
			User user = new User();
			ResultSet rSet = statement.executeQuery();
			if (rSet.next()){
				//学生インスタンスに検索結果をセット
				user.setId(rSet.getInt("id"));
				user.setPass(rSet.getString("password"));
				
			} else{
				attend = null;
			}

		} catch (Exception e) {
			throw e;
		} finally {
			if (statement != null) {
				try {
					statement.close();
				} catch (SQLException sqle) {
					throw sqle;
				}
			}
			//コネクションを閉じる
			if (connection != null){
				try {
					connection.close();
				} catch (SQLException sqle){
					throw sqle;
				}
			}
		}


		return true;
	}





}
