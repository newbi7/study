package lecture.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import lecture.jdbc.dao.BookDAO;
import lecture.jdbc.dao.BookInfDAO;
import lecture.jdbc.dao.DBCPConnectionPool;
import lecture.jdbc.vo.BookInfVO;
import lecture.jdbc.vo.BookVO;

public class BookInfService {

	public ObservableList<BookInfVO> selectBooksInf(String text) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		BookInfDAO dao = new BookInfDAO(con);

		ObservableList<BookInfVO> list = dao.select(text);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ObservableList<BookInfVO> selectBooksInf() {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		BookInfDAO dao = new BookInfDAO(con);

		ObservableList<BookInfVO> list = dao.select();

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}


	public ObservableList<BookInfVO> Bookreturn(String text) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		BookInfDAO dao = new BookInfDAO(con);

		ObservableList<BookInfVO> list = dao.Bookreturn(text);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ObservableList<BookInfVO> Bookreturnrow(String text) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		BookInfDAO dao = new BookInfDAO(con);

		ObservableList<BookInfVO> list = dao.select(text);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public ObservableList<BookInfVO> Bookborrowinfvo(String text1, String text2, String text3, int text4, int text5, String text6
			, String text7, String text8, String text9, String text10, String text11) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		BookInfDAO dao = new BookInfDAO(con);

		ObservableList<BookInfVO> Bookborrowlist = dao.Bookborrowinfvo(text1, text2, text3, text4, text5, text6, 
				text7, text8, text9, text10, text11);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Bookborrowlist;
	}
	
}
