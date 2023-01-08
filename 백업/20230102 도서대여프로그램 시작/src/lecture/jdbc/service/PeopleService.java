package lecture.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import lecture.jdbc.dao.DBCPConnectionPool;
import lecture.jdbc.dao.PeopleDAO;
import lecture.jdbc.vo.PeopleVO;

public class PeopleService {

	
	
	public ObservableList<PeopleVO> selectPeopleByKeyword(String text) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PeopleDAO dao = new PeopleDAO(con);

		ObservableList<PeopleVO> list = dao.select(text);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ObservableList<PeopleVO> loginservice(String text1, String text2) {
		
		Connection con = null;
		
		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		PeopleDAO dao = new PeopleDAO(con);
		
		ObservableList<PeopleVO> loginpagelist = dao.loginpage(text1, text2);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loginpagelist;
	}

	public ObservableList<PeopleVO> IDFindpssnumber(String text1, String text2) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PeopleDAO dao = new PeopleDAO(con);

		ObservableList<PeopleVO> list = dao.IDfind(text1, text2);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ObservableList<PeopleVO> PWFindpssnumber(String text1, String text2, String text3) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PeopleDAO dao = new PeopleDAO(con);

		ObservableList<PeopleVO> list = dao.PWfind(text1, text2, text3);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ObservableList<PeopleVO> Newassign(String text1, String text2, String text3, String text4, String text5) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PeopleDAO dao = new PeopleDAO(con);

		ObservableList<PeopleVO> list = dao.NewAssign(text1, text2, text3, text4, text5);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}


	public PeopleVO Peopleinf(String text1) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PeopleDAO dao = new PeopleDAO(con);

		PeopleVO person = dao.PeopleInf(text1);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return person;
	}

	public PeopleVO DeletePeople(String text1) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PeopleDAO dao = new PeopleDAO(con);

		PeopleVO person = dao.DeletePeople(text1);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return person;
	}
	
	public ObservableList<PeopleVO> IDMatch(String text1) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PeopleDAO dao = new PeopleDAO(con);

		ObservableList<PeopleVO> list = dao.IDMatch(text1);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
	public ObservableList<PeopleVO> Peopleinfins(String text1, String text2, String text3, String text4, String text5, String text6) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		PeopleDAO dao = new PeopleDAO(con);

		ObservableList<PeopleVO> list = dao.Peopleinfins(text1, text2, text3, text4, text5, text6);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}
	
}
