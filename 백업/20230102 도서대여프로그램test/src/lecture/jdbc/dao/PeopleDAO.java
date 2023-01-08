package lecture.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lecture.jdbc.vo.PeopleVO;


public class PeopleDAO {

	Connection con;

	public PeopleDAO() {	
	}
	
	public PeopleDAO(Connection con) {
		super();
		this.con = con;
	}
	
	public ObservableList<PeopleVO> select(String text) {

		ObservableList<PeopleVO> list = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pid, ppw, pname, pssnumber, pphone, pbnumber, ppoint ");
		sql.append("FROM user ");
		sql.append("WHERE pname like ?");


		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + text + "%");
			ResultSet rs = pstmt.executeQuery();
			list = FXCollections.observableArrayList();
			while(rs.next()) {
				PeopleVO people = new PeopleVO (rs.getString("pid"),
						rs.getString("ppw"),
						rs.getString("pname"),
						rs.getString("pssnumber"),
						rs.getString("pphone"),
						rs.getInt("pbnumber"),
						rs.getInt("ppoint"));
				list.add(people);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}

		return list;
	}

	public ObservableList<PeopleVO> loginpage(String text1, String text2) {

		ObservableList<PeopleVO> loginpagelist = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pid, ppw, pname, pssnumber, pphone, pbnumber, ppoint ");
		sql.append("FROM user ");
		sql.append("WHERE pid like ? and ppw like ? ");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + text1 + "%");
			pstmt.setString(2, "%" + text2 + "%");
			ResultSet rs = pstmt.executeQuery();
			loginpagelist = FXCollections.observableArrayList();
			while(rs.next()) {
				PeopleVO people = new PeopleVO (rs.getString("pid"),
						rs.getString("ppw"),
						rs.getString("pname"),
						rs.getString("pssnumber"),
						rs.getString("pphone"),
						rs.getInt("pbnumber"),
						rs.getInt("ppoint"));
				loginpagelist.add(people);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}

		return loginpagelist;
	}

	public ObservableList<PeopleVO> IDfind(String text1, String text2) {

		ObservableList<PeopleVO> IDfindlist = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pid, ppw, pname, pssnumber ");
		sql.append("FROM user ");
		sql.append("WHERE pname like ? and pssnumber like ? ");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + text1 + "%");
			pstmt.setString(2, "%" + text2 + "%");
			ResultSet rs = pstmt.executeQuery();
			IDfindlist = FXCollections.observableArrayList();
			while(rs.next()) {
				PeopleVO people = new PeopleVO (rs.getString("pid"),
						rs.getString("ppw"),
						rs.getString("pname"),
						rs.getString("pssnumber"));
				IDfindlist.add(people);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}

		return IDfindlist;
	}

	public ObservableList<PeopleVO> PWfind(String text1, String text2, String text3) {

		ObservableList<PeopleVO> PWfindlist = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pid, ppw, pname, pssnumber ");
		sql.append("FROM user ");
		sql.append("WHERE pname like ? and pssnumber like ? and pid like ?");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + text1 + "%");
			pstmt.setString(2, "%" + text2 + "%");
			pstmt.setString(3, "%" + text3 + "%");
			ResultSet rs = pstmt.executeQuery();
			PWfindlist = FXCollections.observableArrayList();
			while(rs.next()) {
				PeopleVO people = new PeopleVO (rs.getString("pid"),
						rs.getString("ppw"),
						rs.getString("pname"),
						rs.getString("pssnumber"));
				PWfindlist.add(people);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}

		return PWfindlist;
	}


	public ObservableList<PeopleVO> NewAssign(String text1, String text2, String text3, String text4, String text5) {

		ObservableList<PeopleVO> NewAssignlist = null;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO user (pid, ppw, pname, pssnumber, pphone) " + " VALUES (?, ?, ?, ?, ?) ");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, text1);
			pstmt.setString(2, text2);
			pstmt.setString(3, text3);
			pstmt.setString(4, text4);
			pstmt.setString(5, text5);

			int count = 0;

			count = pstmt.executeUpdate();
	
			if (count>1) {}
			else {}

			pstmt.close();
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("e12");
		}

		return NewAssignlist;
	}
	
	public PeopleVO PeopleInf(String id) {

		PeopleVO people=null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pid, ppw, pname, pssnumber, pphone ");
		sql.append("FROM user ");
		sql.append("WHERE pid = ? ");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			
			people = new PeopleVO (rs.getString("pid"),
					rs.getString("ppw"),
					rs.getString("pname"),
					rs.getString("pssnumber"),
					rs.getString("pphone"));

			
			rs.close();
			pstmt.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}

		return people;
	}
	
	public PeopleVO DeletePeople(String text1) {

		PeopleVO DeletePeoples = null;

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM user ");
		sql.append("WHERE pid = ? ");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, text1);

			int count = 0;

			count = pstmt.executeUpdate();
			if(count > 0) {
				
			}else {
			}

			pstmt.close();
		} catch (Exception e1) {
			System.out.println("e1");
			e1.printStackTrace();
		}

		return DeletePeoples;
	}
	
	public ObservableList<PeopleVO> IDMatch(String text1) {

		ObservableList<PeopleVO> IDMatchpagelist = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pid, ppw, pname, pssnumber ");
		sql.append("FROM user ");
		sql.append("WHERE pid like ? ");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + text1 + "%");
			ResultSet rs = pstmt.executeQuery();
			IDMatchpagelist = FXCollections.observableArrayList();
			while(rs.next()) {
				PeopleVO people = new PeopleVO (rs.getString("pid"),
						rs.getString("ppw"),
						rs.getString("pname"),
						rs.getString("pssnumber"));
				IDMatchpagelist.add(people);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}

		return IDMatchpagelist;
	}

	
	public ObservableList<PeopleVO> Peopleinfins(String text1, String text2, String text3, String text4, String text5, String text6) {

		ObservableList<PeopleVO> Peopleinfinslist = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE user " + " SET  pid = ?, ppw = ?, pname = ?, pssnumber = ?, pphone = ? " + " WHERE pid = ? ");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, text1);
			pstmt.setString(2, text2);
			pstmt.setString(3, text3);
			pstmt.setString(4, text4);
			pstmt.setString(5, text5);
			pstmt.setString(6, text6);

			int count = 0;

			count = pstmt.executeUpdate();
			if(count > 0) {
			}else {
			}

			pstmt.close();
		} catch (Exception e1) {
			System.out.println("e12");
		}


		return Peopleinfinslist;
	}

}
