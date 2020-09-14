package dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.UserInfo;
import util.DBUtil;

public class UserDAO {
	
	public boolean queryUser(UserInfo userinfo){
		Connection conn = DBUtil.getConn();
		String sql="select * from accountdb where username=? and password=?";
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, userinfo.getUsername());
			ps.setString(2, userinfo.getPassword());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}else{
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}finally{
			DBUtil.closeConn();
		}
		
	}
}
