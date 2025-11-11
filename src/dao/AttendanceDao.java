package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.Attendance;
import bean.TotalAbsences;


public class AttendanceDao extends Dao{

	private String baseSql="select * from attendance where id=? ";

	//qrコードで出席
	public boolean attend(int id,String password) throws Exception{


		Date date = new Date();
		//データベース用
		SimpleDateFormat  fm1 = new SimpleDateFormat("yyyy-mm-dd");
		SimpleDateFormat  fm2 = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

		String FM1 =	fm1.format(date);
		String FM2 =	fm2.format(date);

		//遅刻・欠席の判定用
		SimpleDateFormat  check = new SimpleDateFormat("hhmm");
		String c =	check.format(date);
		int C = Integer.parseInt(c);

		Connection connection = getConnection();
		PreparedStatement statement = null;
		PreparedStatement Statement = null;

		String status="";
		if (C > 1240 ){
			status ="absent";
		}else if(C >915){
			status ="late_early";
		}else{
			status = "present";
		}
		int count=0;
		try{

			statement = connection.prepareStatement(baseSql);

			statement.setInt(1,id);
			ResultSet rSet = statement.executeQuery();
			if (rSet != null){
				Statement = connection.prepareStatement("insert into attendance (student_id,date,status,updatetime) values(?,?,?,?)");
				Statement.setInt(1, id);
				Statement.setString(2,FM1);
				Statement.setString(3, status);
				Statement.setString(4, FM2);
			}
		count = Statement.executeUpdate();
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
		if (count > 0){
			return true;
		} else{
			return false;
		}
	}

	public List<Attendance> all(int id)throws Exception{
		List<TotalAbsences> list = new ArrayList();
		Connection connection = getConnection();
		PreparedStatement statement = null;

		//今年
		LocalDateTime nowDate = LocalDateTime.now();
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy"); // ①

		String month="";
		String year = dtf1.format(nowDate);

		int tmp = Integer.parseInt(year) + 1;
		String next = String.valueOf(tmp);
		String base="select * attendance where id=? ";
		String condition="order by date asc";
		try{
			for(int i=1;i<=12;i++){
				month=
				if (i<10){
					statement = connection.prepareStatement(base+"and date like %"+year+"-"+"%" +condition);
				}else{
					statement = connection.prepareStatement(base+"and date like %"+next+"-"+"%" +condition);

				}
				statement.setInt(1,id);
			}
			ResultSet rSet = statement.executeQuery();
			if (rSet != null){

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
	}



	//主席編集の追加

}
