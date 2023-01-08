package lecture.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lecture.jdbc.vo.BookInfVO;
import lecture.jdbc.vo.BookVO;


public class BookInfDAO {

	Connection con;

	public BookInfDAO() {	
	}

	public BookInfDAO(Connection con) {
		super();
		this.con = con;
	}

	public ObservableList<BookInfVO> select(String text) {

		ObservableList<BookInfVO> list = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pid, bisbn, btitle, bdate, bpage, bprice, bauthor, bborrowdate, bpoint, bfuturepoint, bhave ");
		sql.append("FROM bookinf ");
		sql.append("WHERE pid like ?");
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			pstmt.setString(1, "%" + text + "%");
			ResultSet rs = pstmt.executeQuery();
			list = FXCollections.observableArrayList();
			while(rs.next()) {
				BookInfVO bookinf = new BookInfVO (rs.getString("pid"),
						rs.getString("bisbn"),
						rs.getString("btitle"),
						rs.getString("bdate"),
						rs.getInt("bpage"),
						rs.getInt("bprice"),
						rs.getString("bauthor"),
						rs.getInt("bborrowdate"),
						rs.getInt("bpoint"),
						rs.getInt("bfuturepoint"),
						rs.getString("bhave"));
				list.add(bookinf);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}

		
		return list;
	}
	
	public ObservableList<BookInfVO> select() {

		ObservableList<BookInfVO> list = null;

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT pid, bisbn, btitle, bdate, bpage, bprice, bauthor, bborrowdate, bpoint, bfuturepoint, bhave ");
		sql.append("FROM bookinf ");
		sql.append("WHERE bhave like '대여중' ");
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			list = FXCollections.observableArrayList();
			while(rs.next()) {
				BookInfVO bookinf = new BookInfVO (rs.getString("pid"),
						rs.getString("bisbn"),
						rs.getString("btitle"),
						rs.getString("bdate"),
						rs.getInt("bpage"),
						rs.getInt("bprice"),
						rs.getString("bauthor"),
						rs.getInt("bborrowdate"),
						rs.getInt("bpoint"),
						rs.getInt("bfuturepoint"),
						rs.getString("bhave"));
				list.add(bookinf);
			}
			rs.close();
			pstmt.close();
		} catch (Exception e1) {
			System.out.println(e1);
		}

		
		return list;
	}
	
	public ObservableList<BookInfVO> Bookreturn(String text1) {

		ObservableList<BookInfVO> Bookreturnlist = null;
		
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE bookinf " + " SET  bhave = '보유중' " + " WHERE bisbn = ? ");

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

		return Bookreturnlist;
	}
	
	public ObservableList<BookInfVO> Bookborrowinfvo(String text1, String text2, String text3, int text4, int text5,
			String text6, String text7, String text8, String text9, String text10, String text11) {

		ObservableList<BookInfVO> Bookborrowlist = null;

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO bookinf (bnumber, pid, bisbn, btitle, bdate, bpage, "
				+ "bprice, bauthor, btranslator, bsupplement, bpublisher, bimgurl, "
				+ "bborrowdate, breturndate, bpoint, bfuturepoint, bhave ) "
				+ " VALUES ('1', ?, ?, ?, ?, ?, "
				+ " ?, ?, ?, ?, ?, ?,"
				+ "'2', '3', '4', '5', '대여중') ");

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

			if (count>1) {
			}
			else {
			}

			pstmt.close();
		} catch (Exception e1) {
			e1.printStackTrace();
			System.out.println("e12");
		}

		return Bookborrowlist;
	}
	
}
