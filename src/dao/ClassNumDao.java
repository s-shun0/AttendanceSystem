package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.ClassNum;

public class ClassNumDao extends Dao{


	//クラスを選択後、クラス情報の取得
	public ArrayList<ClassNum> get(int classnum) throws Exception{
		ArrayList<ClassNum> num = new ArrayList();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		try{
			statement = connection.prepareStatement("");

		}catch(Exception e){
			throw e;
		}finally{
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

		return num;
	}
}
