package liverary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import liverary.vo.AccountVO;
import liverary.vo.BookVO;

public class BookDAO {
	private Connection con;
	
	public BookDAO() {
	}
	
	public BookDAO(Connection con) {
		this.con = con;
	}

	public ObservableList<BookVO> selectByISBN(String isbn) {
		ObservableList<BookVO> list = null;

		try {
			String sql = "SELECT * "
					+ "FROM booksTBL "
					+ "WHERE bisbn = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			ResultSet rs = pstmt.executeQuery();
			list = FXCollections.observableArrayList();
			while(rs.next()) {
				BookVO book = new BookVO(rs.getString("bisbn"), rs.getString("btitle"), 
						rs.getInt("bprice"), rs.getString("bauthor"), rs.getString("btranslator"),
						rs.getString("bpublisher"), rs.getString("bdate"), rs.getInt("bpage"), 
						rs.getString("bsupplement"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ObservableList<BookVO> selectByKeyword(String keyword) {
ObservableList<BookVO> list = null;
		
		try {
			String sql = "SELECT * "
					+ "FROM booksTBL "
					+ "WHERE btitle LIKE ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			ResultSet rs = pstmt.executeQuery();
			list = FXCollections.observableArrayList();
			while(rs.next()) {
				BookVO book = new BookVO(rs.getString("bisbn"), rs.getString("btitle"), 
						rs.getInt("bprice"), rs.getString("bauthor"), rs.getString("btranslator"),
						rs.getString("bpublisher"), rs.getString("bdate"), rs.getInt("bpage"), 
						rs.getString("bsupplement"));
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int insert(BookVO book) {
		int affectedRows = 0;
		try {
			String sql = "INSERT INTO `booksTBL`(bisbn, btitle, bdate, bpage, "
					+ "bprice, bauthor, btranslator, bsupplement, bpublisher) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, book.getBisbn());
			pstmt.setString(2, book.getBtitle());
			pstmt.setString(3, book.getBdate());
			pstmt.setInt(4, book.getBpage());
			pstmt.setInt(5, book.getBprice());
			pstmt.setString(6, book.getBauthor());
			pstmt.setString(7, book.getBtranslator());
			pstmt.setString(8, book.getBsupplement());
			pstmt.setString(9, book.getBpublisher());
			
			affectedRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}

	public BookVO selectOneByISBN(String isbn) {
		BookVO book = null;
	
		try {
			String sql = "SELECT * "
					+ "FROM booksTBL "
					+ "WHERE bisbn = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);

			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.isBeforeFirst() ) {    
				return null;
			} else {
				rs.next();
				book = new BookVO(rs.getString("bisbn"), rs.getString("btitle"), 
						rs.getInt("bprice"), rs.getString("bauthor"), rs.getString("btranslator"),
						rs.getString("bpublisher"), rs.getString("bdate"), rs.getInt("bpage"), 
						rs.getString("bsupplement"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return book;
	}
	
	public int update(BookVO book) {
		int affectedRows = 0;
		try {
			String sql = "UPDATE `booksTBL` SET btitle = ?, bdate = ?, bpage = ?, "
					+ "bprice = ?, bauthor = ?, btranslator = ?, bsupplement = ?, bpublisher = ? "
					+ "WHERE bisbn = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, book.getBtitle());
			pstmt.setString(2, book.getBdate());
			pstmt.setInt(3, book.getBpage());
			pstmt.setInt(4, book.getBprice());
			pstmt.setString(5, book.getBauthor());
			pstmt.setString(6, book.getBtranslator());
			pstmt.setString(7, book.getBsupplement());
			pstmt.setString(8, book.getBpublisher());
			pstmt.setString(9, book.getBisbn());

			affectedRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}

	public int delete(String isbn) {
		int affectedRows = 0;
		try {
			String sql = "DELETE FROM `booksTBL`"
					+ "WHERE bisbn = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);

			affectedRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}
}
