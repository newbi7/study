package liverary.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import liverary.util.DateHelper;
import liverary.vo.LoanByAccountVO;
import liverary.vo.LoanVO;

public class LoanDAO {

	private Connection con;
	
	public LoanDAO() {
	}
	
	public LoanDAO(Connection con) {
		this.con = con;
	}

	public ObservableList<LoanVO> selectByISBN(String isbn) {
		ObservableList<LoanVO> list = null;
		
		try {
			String sql = "SELECT L.lno, L.lcreatedat, L.lduedate, L.lreturnedAt, L.ano, "
					+ "B.bisbn, B.btitle, B.bdate, B.bpage, B.bprice, B.bauthor, B.btranslator, B.bsupplement, B.bpublisher, "
					+ "IF(L.lno IS NOT NULL, IF(L.lreturnedAt IS NULL, false, true), true) AS available "
					+ "FROM BOOKSTBL B "
					+ "LEFT JOIN (SELECT L2.lno AS lno, bisbn, ano, lcreatedAt, ldueDate, lreturnedAt FROM LOANRECORDTBL L1 "
					+ "INNER JOIN (SELECT MAX(lno) AS lno FROM LOANRECORDTBL GROUP BY bisbn) L2 "
					+ "WHERE L1.lno = L2.lno) L "
					+ "ON B.BISBN = L.BISBN "
					+ "WHERE B.bisbn = ?"
					+ "ORDER BY B.btitle";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, isbn);
			
			ResultSet rs = pstmt.executeQuery();
			list = FXCollections.observableArrayList();
			
			while(rs.next()) {
				String available_kor = "대출불가";
				if (rs.getBoolean("available")) {
					available_kor = "대출가능";
				}
				
				LoanVO book = new LoanVO(rs.getInt("lno"),
											rs.getString("lcreatedat"),
											rs.getString("lduedate"),
											rs.getString("lreturnedAt"),
											rs.getString("bisbn"),
											rs.getString("btitle"),
											rs.getString("bdate"),
											rs.getInt("bpage"),
											rs.getInt("bprice"),
											rs.getString("bauthor"),
											rs.getString("btranslator"),
											rs.getString("bsupplement"),
											rs.getString("bpublisher"),
											rs.getBoolean("available"),
											rs.getInt("ano"),
											available_kor);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ObservableList<LoanVO> selectByKeyword(String keyword) {
		ObservableList<LoanVO> list = null;
		
		try {
			String sql = "SELECT L.lno, L.lcreatedat, L.lduedate, L.lreturnedAt, L.ano, "
					+ "B.bisbn, B.btitle, B.bdate, B.bpage, B.bprice, B.bauthor, B.btranslator, B.bsupplement, B.bpublisher, "
					+ "IF(L.lno IS NOT NULL, IF(L.lreturnedAt IS NULL, false, true), true) AS available "
					+ "FROM BOOKSTBL B "
					+ "LEFT JOIN (SELECT L2.lno AS lno, bisbn, ano, lcreatedAt, ldueDate, lreturnedAt FROM LOANRECORDTBL L1 "
					+ "INNER JOIN (SELECT MAX(lno) AS lno FROM LOANRECORDTBL GROUP BY bisbn) L2 "
					+ "WHERE L1.lno = L2.lno) L "
					+ "ON B.BISBN = L.BISBN "
					+ "WHERE B.btitle LIKE ?"
					+ "ORDER BY B.btitle";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			ResultSet rs = pstmt.executeQuery();
			list = FXCollections.observableArrayList();
			
			while(rs.next()) {
				String available_kor = "대출불가";
				if (rs.getBoolean("available")) {
					available_kor = "대출가능";
				}

				LoanVO book = new LoanVO(rs.getInt("lno"),
											rs.getString("lcreatedat"),
											rs.getString("lduedate"),
											rs.getString("lreturnedAt"),
											rs.getString("bisbn"),
											rs.getString("btitle"),
											rs.getString("bdate"),
											rs.getInt("bpage"),
											rs.getInt("bprice"),
											rs.getString("bauthor"),
											rs.getString("btranslator"),
											rs.getString("bsupplement"),
											rs.getString("bpublisher"),
											rs.getBoolean("available"),
											rs.getInt("ano"),
											available_kor);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ObservableList<LoanVO> selectBookByAno(int ano) {
		ObservableList<LoanVO> list = null;
		
		try {
			String sql = "SELECT L.lno, L.lcreatedAt, L.lduedate, L.lreturnedAt, L.ano, "
					+ "B.bisbn, B.btitle, B.bdate, B.bpage, B.bprice, B.bauthor, B.btranslator, B.bsupplement, B.bpublisher, "
					+ "IF(L.lno IS NOT NULL, IF(L.lreturnedAt IS NULL, false, true), true) AS available "
					+ "FROM BOOKSTBL B "
					+ "INNER JOIN LOANRECORDTBL L "
					+ "ON B.BISBN = L.BISBN "
					+ "WHERE L.ano = ? "
					+ "ORDER BY L.lno DESC";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ano);
			ResultSet rs = pstmt.executeQuery();
			list = FXCollections.observableArrayList();
			
			while(rs.next()) {
				String available_kor = "대출중";
				if (rs.getBoolean("available")) {
					available_kor = "반납완료";
				}
				
				LoanVO book = new LoanVO(rs.getInt("lno"),
											rs.getString("lcreatedat"),
											rs.getString("lduedate"),
											rs.getString("lreturnedAt"),
											rs.getString("bisbn"),
											rs.getString("btitle"),
											rs.getString("bdate"),
											rs.getInt("bpage"),
											rs.getInt("bprice"),
											rs.getString("bauthor"),
											rs.getString("btranslator"),
											rs.getString("bsupplement"),
											rs.getString("bpublisher"),
											rs.getBoolean("available"),
											rs.getInt("ano"),
											available_kor);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public int insert(LoanVO row) {
		int affectedRows = 0;
		try {
			String sql = "INSERT INTO loanrecordtbl(bisbn, ano, lcreatedAt, ldueDate, lreturnedAt) "
					+ "VALUES (?, ?, ?, ?, null)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, row.getBisbn());
			pstmt.setInt(2, row.getAno());
			pstmt.setString(3, DateHelper.todayDateStr());
			pstmt.setString(4, DateHelper.AddDaysToTodayDateStr(7));
			
			affectedRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}

	public int update(LoanVO row) {
		int affectedRows = 0;
		try {
			String sql = "UPDATE loanRecordTBL SET lreturnedAt = ? WHERE lno = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, DateHelper.todayDateStr());
			pstmt.setInt(2, row.getLno());
			
			affectedRows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return affectedRows;
	}

	public ObservableList<LoanByAccountVO> selectByAno(int ano) {
		ObservableList<LoanByAccountVO> list = FXCollections.observableArrayList();
		
		try {
			String sql = "SELECT A.ano, A.aname, A.abirth, A.acreatedAt, A.aphone, A.aemail, A.aaddr, A.apoint, A.alevel, A.ausername, "
					+ "L.lno, L.bisbn, L.lcreatedAt, L.lduedate, L.lreturnedAt  "
					+ "FROM ACCOUNTSTBL A LEFT JOIN LOANRECORDTBL L  "
					+ "ON A.ANO = L.ANO WHERE A.ano = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ano);
			ResultSet rs = pstmt.executeQuery();
			
			if (!rs.isBeforeFirst() ) {    
				return null;
			} else {
				while (rs.next()) {			
					LoanByAccountVO row = new LoanByAccountVO(rs.getInt("ano"), rs.getString("aname"), rs.getString("abirth"),
							rs.getString("acreatedAt"), rs.getString("aphone"), rs.getString("aemail"),
							rs.getString("aaddr"), rs.getInt("apoint"), rs.getInt("alevel"), rs.getString("ausername"),
							rs.getInt("lno"), rs.getString("bisbn"), rs.getString("lcreatedAt"),
							rs.getString("lduedate"), rs.getString("lreturnedAt"));	
					list.add(row);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ObservableList<LoanVO> selectBooksByKeyword(String keyword, String startDate, String endDate) {
		ObservableList<LoanVO> list = null;

		try {
			String sql = "SELECT L.lno, L.lcreatedAt, L.lduedate, L.lreturnedAt, L.ano, "
					+ "B.bisbn, B.btitle, B.bdate, B.bpage, B.bprice, B.bauthor, B.btranslator, B.bsupplement, B.bpublisher, "
					+ "IF(L.lno IS NOT NULL, IF(L.lreturnedAt IS NULL, false, true), true) AS available "
					+ "FROM BOOKSTBL B "
					+ "INNER JOIN LOANRECORDTBL L "
					+ "ON B.BISBN = L.BISBN "
					+ "WHERE B.btitle LIKE ?"
					+ "AND L.lcreatedAt BETWEEN ? AND ? "
					+ "ORDER BY L.lno DESC";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);
			ResultSet rs = pstmt.executeQuery();
			list = FXCollections.observableArrayList();
			
			while(rs.next()) {
				String available_kor = "대출중";
				if (rs.getBoolean("available")) {
					available_kor = "반납완료";
				}
				
				LoanVO book = new LoanVO(rs.getInt("lno"),
											rs.getString("lcreatedat"),
											rs.getString("lduedate"),
											rs.getString("lreturnedAt"),
											rs.getString("bisbn"),
											rs.getString("btitle"),
											rs.getString("bdate"),
											rs.getInt("bpage"),
											rs.getInt("bprice"),
											rs.getString("bauthor"),
											rs.getString("btranslator"),
											rs.getString("bsupplement"),
											rs.getString("bpublisher"),
											rs.getBoolean("available"),
											rs.getInt("ano"),
											available_kor);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ObservableList<LoanVO> selectBooksByISBN(String bisbn, String startDate, String endDate) {
		ObservableList<LoanVO> list = null;

		try {
			String sql = "SELECT L.lno, L.lcreatedAt, L.lduedate, L.lreturnedAt, L.ano, "
					+ "B.bisbn, B.btitle, B.bdate, B.bpage, B.bprice, B.bauthor, B.btranslator, B.bsupplement, B.bpublisher, "
					+ "IF(L.lno IS NOT NULL, IF(L.lreturnedAt IS NULL, false, true), true) AS available "
					+ "FROM BOOKSTBL B "
					+ "INNER JOIN LOANRECORDTBL L "
					+ "ON B.BISBN = L.BISBN "
					+ "WHERE B.bisbn = ?"
					+ "AND L.lcreatedAt BETWEEN ? AND ? "
					+ "ORDER BY L.lno DESC";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bisbn);
			pstmt.setString(2, startDate);
			pstmt.setString(3, endDate);
			ResultSet rs = pstmt.executeQuery();
			list = FXCollections.observableArrayList();
			
			while(rs.next()) {
				String available_kor = "대출중";
				if (rs.getBoolean("available")) {
					available_kor = "반납완료";
				}
				
				LoanVO book = new LoanVO(rs.getInt("lno"),
											rs.getString("lcreatedat"),
											rs.getString("lduedate"),
											rs.getString("lreturnedAt"),
											rs.getString("bisbn"),
											rs.getString("btitle"),
											rs.getString("bdate"),
											rs.getInt("bpage"),
											rs.getInt("bprice"),
											rs.getString("bauthor"),
											rs.getString("btranslator"),
											rs.getString("bsupplement"),
											rs.getString("bpublisher"),
											rs.getBoolean("available"),
											rs.getInt("ano"),
											available_kor);
				list.add(book);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
}
