package liverary.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import liverary.dao.AccountDAO;
import liverary.dao.DBCPConnectionPool;
import liverary.dao.LoanDAO;
import liverary.util.DateHelper;
import liverary.vo.AccountVO;
import liverary.vo.LoanByAccountVO;
import liverary.vo.LoanVO;

public class LoanService {

	public ObservableList<LoanVO> selectLoanRecordsByISBN(String isbn) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		LoanDAO dao = new LoanDAO(con);
		ObservableList<LoanVO> list = dao.selectByISBN(isbn);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ObservableList<LoanVO> selectLoanRecordsByKeyword(String keyword) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		LoanDAO dao = new LoanDAO(con);
		ObservableList<LoanVO> list = dao.selectByKeyword(keyword);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public boolean insertLoanRecord(LoanVO row) {
		
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		boolean sucess = false;
		
		LoanDAO dao = new LoanDAO(con);
		int affectedRows = dao.insert(row);
		
		try {
			if (affectedRows == 1) {
				sucess = true;
				con.commit();
			} else {
				con.rollback();
				sucess = false;
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sucess;
	}

	public Boolean updateLoanRecord(LoanVO row) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 연체도서 확인
		boolean penalty = false;
		LocalDate dueDate = LocalDate.parse(row.getLduedate(), DateTimeFormatter.ISO_DATE);
		if (DateHelper.getDifferenceByToday(dueDate) > 0) {
			penalty = true;
		}
		
		// 반납처리
		boolean sucess = false;
		
		LoanDAO loanDao = new LoanDAO(con);
		int updateLoanAffectedRows = loanDao.update(row);
		
		AccountDAO accountDao = new AccountDAO(con);
		AccountVO account = accountDao.selectByNo(row.getAno());

		int updateAccountAffectedRows = 0;
		if (!penalty) { // 정상 반납
			updateAccountAffectedRows = accountDao.updatePoint(row.getAno(), account.getApoint() + 100);			
		} else {
			updateAccountAffectedRows = accountDao.updatePoint(row.getAno(), account.getApoint() - 100);
		}
		
		try {
			if (updateLoanAffectedRows == 1 && updateAccountAffectedRows == 1) {
				sucess = true;
				con.commit();
			} else {
				sucess = false;
				con.rollback();
			}
			
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return sucess;
	}

	public boolean getIsNeededPenalty(LoanVO record) {
		boolean penalty = false;
		LocalDate dueDate = LocalDate.parse(record.getLduedate(), DateTimeFormatter.ISO_DATE);
		System.out.println(DateHelper.getDifferenceByToday(dueDate));
		if (DateHelper.getDifferenceByToday(dueDate) > 0) {
			penalty = true;
		}
		return penalty;
	}
	
	public ObservableList<LoanByAccountVO> selectLoanRowOfAccount(int ano) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		LoanDAO dao = new LoanDAO(con);
		ObservableList<LoanByAccountVO> list = dao.selectByAno(ano);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public HashMap<String, Integer> selectLoanStatusOfAccount(int ano) {
		ObservableList<LoanByAccountVO> list = selectLoanRowOfAccount(ano);
		
		int total = 0;
		int penalty = 0;
		int normal = 0;
		if (list != null) {
			for (LoanByAccountVO row : list) {
				if (row.getLcreatedat() != null && row.getLreturnedAt() == null) { // 대출 기록이 있지만 반납되지 않은 경우
					total++;
					LocalDate dueDate = LocalDate.parse(row.getLduedate(), DateTimeFormatter.ISO_DATE);
					if (DateHelper.getDifferenceByToday(dueDate) > 0) {
						penalty++;
					}
				}
			}
			normal = total - penalty;
		}
		
		HashMap<String, Integer> result = new HashMap<>();
		result.put("TOTAL", total);
		result.put("PENALTY", penalty);
		result.put("NORMAL", normal);
		
		return result;
	}

	public ObservableList<LoanVO> selectLoanBookRowsOfAccount(int ano) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		LoanDAO dao = new LoanDAO(con);
		ObservableList<LoanVO> listBeforeProcessed = dao.selectBookByAno(ano);
		ObservableList<LoanVO> list = FXCollections.observableArrayList();
		for (LoanVO row : listBeforeProcessed) {
			if (row.getAvailable_kor().equals("반납완료")) {
				LocalDate returnedDate = LocalDate.parse(row.getLreturnedAt(), DateTimeFormatter.ISO_DATE);
				LocalDate dueDate = LocalDate.parse(row.getLduedate(), DateTimeFormatter.ISO_DATE);
				if (DateHelper.getDifferenceBetween(returnedDate, dueDate) > 0) {
					row.setAvailable_kor("반납완료 (연체)");
				} else {
					row.setAvailable_kor("반납완료 (정상)");
				}
			}
			list.add(row);
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ObservableList<LoanVO> selectLoanBookRowsByKeywordWithDates(String keyword, String startDate, String endDate) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		LoanDAO dao = new LoanDAO(con);
		ObservableList<LoanVO> listBeforeProcessed = dao.selectBooksByKeyword(keyword, startDate, endDate);
		ObservableList<LoanVO> list = FXCollections.observableArrayList();
		for (LoanVO row : listBeforeProcessed) {
			if (row.getAvailable_kor().equals("반납완료")) {
				LocalDate returnedDate = LocalDate.parse(row.getLreturnedAt(), DateTimeFormatter.ISO_DATE);
				LocalDate dueDate = LocalDate.parse(row.getLduedate(), DateTimeFormatter.ISO_DATE);
				if (DateHelper.getDifferenceBetween(returnedDate, dueDate) > 0) {
					row.setAvailable_kor("반납완료 (연체)");
				} else {
					row.setAvailable_kor("반납완료 (정상)");
				}
			}
			list.add(row);
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ObservableList<LoanVO> selectLoanBookRowsByISBNWithDates(String isbn, String startDate, String endDate) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		LoanDAO dao = new LoanDAO(con);
		ObservableList<LoanVO> listBeforeProcessed = dao.selectBooksByISBN(isbn, startDate, endDate);
		ObservableList<LoanVO> list = FXCollections.observableArrayList();
		for (LoanVO row : listBeforeProcessed) {
			if (row.getAvailable_kor().equals("반납완료")) {
				LocalDate returnedDate = LocalDate.parse(row.getLreturnedAt(), DateTimeFormatter.ISO_DATE);
				LocalDate dueDate = LocalDate.parse(row.getLduedate(), DateTimeFormatter.ISO_DATE);
				if (DateHelper.getDifferenceBetween(returnedDate, dueDate) > 0) {
					row.setAvailable_kor("반납완료 (연체)");
				} else {
					row.setAvailable_kor("반납완료 (정상)");
				}
			}
			list.add(row);
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public ObservableList<LoanVO> selectNoReturnedBookRowsByKeywordWithDates(String keyword, String startDate,
			String endDate) {
		
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		LoanDAO dao = new LoanDAO(con);
		ObservableList<LoanVO> listOfAll = dao.selectBooksByKeyword(keyword, startDate, endDate);
		ObservableList<LoanVO> list = FXCollections.observableArrayList();
		for (LoanVO row : listOfAll) {
			if (row.getAvailable_kor().equals("대출중")) {
				list.add(row);
			}
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	public ObservableList<LoanVO> selectNoReturnedBookRowsByISBNWithDates(String keyword, String startDate,
			String endDate) {
		
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		LoanDAO dao = new LoanDAO(con);
		ObservableList<LoanVO> listOfAll = dao.selectBooksByISBN(keyword, startDate, endDate);
		ObservableList<LoanVO> list = FXCollections.observableArrayList();
		for (LoanVO row : listOfAll) {
			if (row.getAvailable_kor().equals("대출중")) {
				list.add(row);
			}
		}
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

}
