package lecture.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lecture.jdbc.vo.BookInfVO;
import lecture.jdbc.vo.BookVO;
import lecture.jdbc.vo.PeopleVO;


public class BookDAO {

	Connection con;

	public BookDAO() {	
	}

	public BookDAO(Connection con) {
		super();
		this.con = con;
	}

	public ObservableList<BookVO> select(String text) {

		ObservableList<BookVO> list = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bisbn, btitle, bdate, bpage, bprice, bauthor, bhave ");
		sql.append("FROM book ");
		sql.append("WHERE btitle like ?");


		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + text + "%");
			ResultSet rs = pstmt.executeQuery();
			list = FXCollections.observableArrayList();
			while(rs.next()) {
				BookVO book = new BookVO(rs.getString("bisbn"),
						rs.getString("btitle"),
						rs.getString("bdate"),
						rs.getInt("bpage"),
						rs.getInt("bprice"),
						rs.getString("bauthor"),
						rs.getString("bhave"));
				list.add(book);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}

		return list;
	}

	public ObservableList<BookVO> detailselect(String text) {

		ObservableList<BookVO> detaillist = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT bisbn, btitle, bdate, bpage, bprice, bauthor, btranslator, bsupplement, bpublisher, bimgurl ");
		sql.append("FROM book ");
		sql.append("WHERE btitle like ?");


		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + text + "%");
			ResultSet rs = pstmt.executeQuery();
			detaillist = FXCollections.observableArrayList();
			while(rs.next()) {
				BookVO book = new BookVO(rs.getString("bisbn"),
						rs.getString("btitle"),
						rs.getString("bdate"),
						rs.getInt("bpage"),
						rs.getInt("bprice"),
						rs.getString("bauthor"),
						rs.getString("btranslator"),
						rs.getString("bsupplement"),
						rs.getString("bpublisher"),
						rs.getString("bimgurl")
						);
				detaillist.add(book);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}

		return detaillist;
	}

	public ObservableList<BookVO> BookNewAssign(String text1, String text2, String text3, String text4, String text5,
			String text6, String text7, String text8, String text9, String text10) {

		ObservableList<BookVO> BookNewAssignlist = null;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO book (bisbn, btitle, bdate, bpage, bprice, bauthor, btranslator, bsupplement, bpublisher, bimgurl, bhave) "
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, '보유중') ");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, text1);
			pstmt.setString(2, text2);
			pstmt.setString(3, text3);
			pstmt.setString(4, text4);
			pstmt.setString(5, text5);
			pstmt.setString(6, text6);
			pstmt.setString(7, text7);
			pstmt.setString(8, text8);
			pstmt.setString(9, text9);
			pstmt.setString(10, text10);

			int count = 0;
			count = pstmt.executeUpdate();

			if (count>1) {
			}
			else {
			}

			pstmt.close();
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("e12");
		}

		return BookNewAssignlist;
	}

	public BookVO DeleteBooks(String text1) {

		BookVO DeleteBooks = null;

		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM book ");
		sql.append("WHERE bisbn = ? ");

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

		return DeleteBooks;
	}

	public BookVO Bookinfstyle(String text1) {

		BookVO  Bookinfstyles = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append("FROM book ");
		sql.append("WHERE bisbn = ? ");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, text1);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Bookinfstyles = new BookVO(rs.getString("bisbn"),
						rs.getString("btitle"),
						rs.getString("bdate"),
						rs.getInt("bpage"),
						rs.getInt("bprice"),
						rs.getString("bauthor"),
						rs.getString("btranslator"),
						rs.getString("bsupplement"),
						rs.getString("bpublisher"),
						rs.getString("bimgurl"));
				
			rs.close();
			pstmt.close();

		} catch (Exception e1) {
		}

		return Bookinfstyles;
	}

	public ObservableList<BookVO> Bookinfins(String text1, String text2, String text3, int text4, int text5, String text6,
			String text7, String text8, String text9, String text10, String text11) {

		ObservableList<BookVO> Bookinfinslist = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE book " + " SET  bisbn = ?, btitle = ?, bdate = ?, "
				+ "bpage = ?, bprice = ?, bauthor = ?, "
				+ "btranslator = ?, bsupplement = ?, bpublisher = ?, bimgurl = ? " + " WHERE bisbn = ? ");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, text1);
			pstmt.setString(2, text2);
			pstmt.setString(3, text3);
			pstmt.setInt(4, text4);
			pstmt.setInt(5, text5);
			pstmt.setString(6, text6);
			pstmt.setString(7, text7);
			pstmt.setString(8, text8);
			pstmt.setString(9, text9);
			pstmt.setString(10, text10);
			pstmt.setString(11, text11);

			int count = 0;

			count = pstmt.executeUpdate();
			if(count > 0) {
			}else {
			}

			pstmt.close();
		} catch (Exception e1) {
			System.out.println("e12");
		}


		return Bookinfinslist;
	}
	
	public ObservableList<BookVO> BookreturnVO(String text1) {

		ObservableList<BookVO> BookreturnVOlist = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE book " + " SET  bhave = '보유중' " + " WHERE bisbn = ? ");

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
			e1.printStackTrace();
			System.out.println("e12");
		}

		return BookreturnVOlist;
	}

	public ObservableList<BookVO> Bookborrow(String text1) {

		ObservableList<BookVO> Bookborrowlist = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE book " + " SET  bhave = '대여중' " + " WHERE bisbn = ? ");

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
			e1.printStackTrace();
			System.out.println("e12");
		}

		return Bookborrowlist;
	}
	
	public BookVO Bookborrowcheck(String text1) {

		BookVO Bookborrowchecks = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ");
		sql.append("FROM book ");
		sql.append("WHERE bisbn = ? ");

		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, text1);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			Bookborrowchecks = new BookVO(rs.getString("bisbn"),
						rs.getString("btitle"),
						rs.getString("bdate"),
						rs.getInt("bpage"),
						rs.getInt("bprice"),
						rs.getString("bauthor"),
						rs.getString("bhave"),
						rs.getString("btranslator"),
						rs.getString("bsupplement"),
						rs.getString("bpublisher"),
						rs.getString("bimgurl"));
				
			rs.close();
			pstmt.close();

		} catch (Exception e1) {
		}

		return Bookborrowchecks;
	}
	
}
