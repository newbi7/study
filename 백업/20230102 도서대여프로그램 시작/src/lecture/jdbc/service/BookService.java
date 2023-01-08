package lecture.jdbc.service;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import lecture.jdbc.dao.BookDAO;
import lecture.jdbc.dao.BookInfDAO;
import lecture.jdbc.dao.DBCPConnectionPool;
import lecture.jdbc.dao.PeopleDAO;
import lecture.jdbc.vo.BookInfVO;
import lecture.jdbc.vo.BookVO;
import lecture.jdbc.vo.PeopleVO;

public class BookService {

	public ObservableList<BookVO> selectBooksByKeyword(String text) {
		
		Connection con = null;
		
		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		BookDAO dao = new BookDAO(con);
	
		ObservableList<BookVO> list = dao.select(text);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ObservableList<BookVO> selectdetailBooksByKeyword(String text) {
		
		Connection con = null;
		
		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		BookDAO dao = new BookDAO(con);
	
		ObservableList<BookVO> detaillist = dao.detailselect(text);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return detaillist;
	}
	

	public ObservableList<BookVO> Bookinfadd(String text1, String text2, String text3, String text4, String text5, 
			String text6, String text7, String text8, String text9, String text10) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		BookDAO dao = new BookDAO(con);

		ObservableList<BookVO> list = dao.BookNewAssign(text1, text2, text3, text4, text5, text6, text7, text8, text9, text10);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

	public BookVO DeleteBook(String text1) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		BookDAO dao = new BookDAO(con);

		BookVO books = dao.DeleteBooks(text1);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return books;
	}
	
	public BookVO Bookinfstyle(String text1) {
		
		Connection con = null;
		
		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		BookDAO dao = new BookDAO(con);
		
		BookVO books = dao.Bookinfstyle(text1);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return books;
	}
	
	public ObservableList<BookVO> Bookinfins(String text1, String text2, String text3, int text4, int text5, String text6
			, String text7, String text8, String text9, String text10, String text11) {

		Connection con = null;

		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		BookDAO dao = new BookDAO(con);

		ObservableList<BookVO> Bookinfinslist = dao.Bookinfins(text1, text2, text3, text4, text5, text6, text7, text8, text9, text10, text11);

		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return Bookinfinslist;
	}
	
	public ObservableList<BookVO> BookreturnVO(String text) {
		
		Connection con = null;
		
		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		BookDAO dao = new BookDAO(con);
	
		ObservableList<BookVO> list = dao.BookreturnVO(text);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ObservableList<BookVO> Bookborrow(String text) {
		
		Connection con = null;
		
		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		BookDAO dao = new BookDAO(con);
	
		ObservableList<BookVO> Bookborrowlist = dao.Bookborrow(text);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Bookborrowlist;
	}
	
	public BookVO Bookborrowcheck(String text1) {
		
		Connection con = null;
		
		try {
			con = (DBCPConnectionPool.getDataSource()).getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		BookDAO dao = new BookDAO(con);
		
		BookVO Bookborrowchecks = dao.Bookborrowcheck(text1);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return Bookborrowchecks;
	}
}






