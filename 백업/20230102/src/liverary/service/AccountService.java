package liverary.service;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.collections.ObservableList;
import liverary.dao.AccountDAO;
import liverary.dao.DBCPConnectionPool;
import liverary.dao.LoanDAO;
import liverary.vo.AccountVO;
import liverary.vo.LoanVO;

public class AccountService {

	public AccountVO selectAccountbyUsername(String username) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
			// con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		AccountDAO dao = new AccountDAO(con);
		AccountVO account = dao.selectByUsername(username);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}

	public AccountVO selectAccountbyNO(int no) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
			// con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		AccountDAO dao = new AccountDAO(con);
		AccountVO account = dao.selectByNo(no);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}

	public boolean insertNewAccount(AccountVO newAccount) {
		boolean result = false;
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		AccountDAO dao = new AccountDAO(con);
		int affectedRows = dao.insert(newAccount);
		
		try {
			if (affectedRows == 1) {
				result = true;
				con.commit();
			} else {
				result = false;
				con.rollback();
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public boolean updateAccount(AccountVO account) {
		boolean result = false;
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		AccountDAO dao = new AccountDAO(con);
		int affectedRows = dao.update(account);
		
		try {
			if (affectedRows == 1) {
				result = true;
				con.commit();
			} else {
				result = false;
				con.rollback();
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	public ObservableList<AccountVO> selectAccountsByName(String name) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
			// con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		AccountDAO dao = new AccountDAO(con);
		ObservableList<AccountVO> list = dao.selectByName(name);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public AccountVO selectStaffAccountbyUsername(String username) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
			// con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		AccountDAO dao = new AccountDAO(con);
		AccountVO account = dao.selectStaffByUsername(username);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return account;
	}

	public ObservableList<AccountVO> selectStaffAccountsByName(String name) {
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
			// con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		AccountDAO dao = new AccountDAO(con);
		ObservableList<AccountVO> list = dao.selectStaffByName(name);
		
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}

	/*
	 * 기본값 0, 성공시 1, 실패시 -1, 대출도서가 있어서 불가시 -2
	 **/
	public int updateAccountToEmptyRow(int ano) {
		int result = 0;
		Connection con = null;
		try {
			con = DBCPConnectionPool.getDataSource().getConnection();
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		LoanDAO daoL = new LoanDAO(con);
		ObservableList<LoanVO> list = daoL.selectBookByAno(ano);
		System.out.println(list.get(0).getLno());
		for (LoanVO row : list) {
			if(row.getLreturnedAt().equals("")) {
				return -2;
			}
		}
		
		AccountDAO daoA = new AccountDAO(con);
		AccountVO account = new AccountVO(ano, "", "", "1970-01-01", "1970-01-01",
											"", "", "", -1, -1, "", "");
		
		int affectedRows = daoA.update(account);
		
		try {
			if (affectedRows == 1) {
				result = 1;
				con.commit();
			} else {
				result = -1;
				con.rollback();
			}
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

}
