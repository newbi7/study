package liverary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import liverary.vo.AccountVO;

public class AccountDAO {
	
	private Connection con;
	
	public AccountDAO() {
	}
	
	public AccountDAO(Connection con) {
		super();
		this.con = con;
	}

	public AccountVO selectByUsername(String username) {
		String sql = "SELECT * FROM accountsTBL WHERE ausername = ?";
		
		AccountVO account = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.isBeforeFirst() ) {    
				return null;
			} else {
				rs.next();
				account = new AccountVO(rs.getInt("ano"), rs.getString("aname"), rs.getString("adepartment"), rs.getString("abirth"), rs.getString("acreatedAt"), rs.getString("aphone"),
						rs.getString("aemail"), rs.getString("aaddr"), rs.getInt("apoint"), rs.getInt("alevel"), rs.getString("ausername"), rs.getString("apassword"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
		
	}
	
	public AccountVO selectByNo(int ano) {
		String sql = "SELECT * FROM accountsTBL WHERE ano = ?";
		
		AccountVO account = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ano);
			ResultSet rs = pstmt.executeQuery();
			
			rs.next();
			account = new AccountVO(rs.getInt("ano"), rs.getString("aname"), rs.getString("adepartment"), rs.getString("abirth"), rs.getString("acreatedAt"), rs.getString("aphone"),
									rs.getString("aemail"), rs.getString("aaddr"), rs.getInt("apoint"), rs.getInt("alevel"), rs.getString("ausername"), rs.getString("apassword"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}

	public int updatePoint(int ano, int newPoint) {
		int affectedRows = 0;
		try {
			String sql = "UPDATE accountsTBL SET apoint = ? WHERE ano = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, newPoint);
			pstmt.setInt(2, ano);
			
			affectedRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}

	public int insert(AccountVO account) {
		int affectedRows = 0;
		try {
			String sql = "INSERT INTO `accountsTBL`(aname, adepartment, abirth, acreatedAt, "
					+ "aphone, aemail, aaddr, apoint, alevel, ausername, apassword) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, account.getAname());
			pstmt.setString(2, account.getAdepartment());
			pstmt.setString(3, account.getAbirth());
			pstmt.setString(4, account.getAcreatedAt());
			pstmt.setString(5, account.getAphone());
			pstmt.setString(6, account.getAemail());
			pstmt.setString(7, account.getAaddr());
			pstmt.setInt(8, account.getApoint());
			pstmt.setInt(9, account.getAlevel());
			pstmt.setString(10, account.getAusername());
			pstmt.setString(11, account.getApassword());
			
			affectedRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}

	public int update(AccountVO account) {
		int affectedRows = 0;
		try {
			String sql = "UPDATE `accountsTBL` "
					+ "SET aname = ?, adepartment = ?, aphone = ?, aemail = ?, aaddr = ?, apassword = ?, "
					+ "adepartment = ?, abirth = ?, acreatedAt = ?, apoint = ?, ausername = ? "
					+ "WHERE ano = ?";

			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account.getAname());
			pstmt.setString(2, account.getAdepartment());
			pstmt.setString(3, account.getAphone());
			pstmt.setString(4, account.getAemail());
			pstmt.setString(5, account.getAaddr());
			pstmt.setString(6, account.getApassword());
			pstmt.setString(7, account.getAdepartment());
			pstmt.setString(8, account.getAbirth());
			pstmt.setString(9, account.getAcreatedAt());
			pstmt.setInt(10, account.getApoint());
			pstmt.setString(11, account.getAusername());
			pstmt.setInt(12, account.getAno());

			affectedRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}

	public ObservableList<AccountVO> selectByName(String name) {
		String sql = "SELECT * FROM accountsTBL WHERE aname LIKE ?";
		
		ObservableList<AccountVO> list = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();
			
			list = FXCollections.observableArrayList();
			while (rs.next()) {
				AccountVO account = new AccountVO(rs.getInt("ano"), rs.getString("aname"), rs.getString("adepartment"), rs.getString("abirth"), rs.getString("acreatedAt"), rs.getString("aphone"),
						rs.getString("aemail"), rs.getString("aaddr"), rs.getInt("apoint"), rs.getInt("alevel"), rs.getString("ausername"), rs.getString("apassword"));
				list.add(account);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public AccountVO selectStaffByUsername(String username) {
		String sql = "SELECT * FROM accountsTBL WHERE ausername = ? AND alevel > 0";
		
		AccountVO account = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.isBeforeFirst() ) {    
				return null;
			} else {
				rs.next();
				account = new AccountVO(rs.getInt("ano"), rs.getString("aname"), rs.getString("adepartment"), rs.getString("abirth"), rs.getString("acreatedAt"), rs.getString("aphone"),
						rs.getString("aemail"), rs.getString("aaddr"), rs.getInt("apoint"), rs.getInt("alevel"), rs.getString("ausername"), rs.getString("apassword"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}
	
	public ObservableList<AccountVO> selectStaffByName(String name) {
		String sql = "SELECT * FROM accountsTBL WHERE aname LIKE ? AND alevel > 0";
		
		ObservableList<AccountVO> list = null;
		PreparedStatement pstmt = null;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + name + "%");
			ResultSet rs = pstmt.executeQuery();
			
			list = FXCollections.observableArrayList();
			while (rs.next()) {
				AccountVO account = new AccountVO(rs.getInt("ano"), rs.getString("aname"), rs.getString("adepartment"), rs.getString("abirth"), rs.getString("acreatedAt"), rs.getString("aphone"),
						rs.getString("aemail"), rs.getString("aaddr"), rs.getInt("apoint"), rs.getInt("alevel"), rs.getString("ausername"), rs.getString("apassword"));
				list.add(account);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
