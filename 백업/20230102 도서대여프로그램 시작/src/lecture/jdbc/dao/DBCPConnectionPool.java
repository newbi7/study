package lecture.jdbc.dao;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPConnectionPool {

	private static BasicDataSource basicDSBook;

	static {
		basicDSBook = new BasicDataSource();
		basicDSBook.setDriverClassName("com.mysql.cj.jdbc.Driver");
		basicDSBook.setUrl("jdbc:mysql://127.0.0.1:3306/library?characterEncoding=UTF-8&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
		basicDSBook.setUsername("root");
		basicDSBook.setPassword("test1234");
		basicDSBook.setInitialSize(10);
		basicDSBook.setMaxTotal(20);		
	}

	public static DataSource getDataSource() {
		return basicDSBook;
	}
}