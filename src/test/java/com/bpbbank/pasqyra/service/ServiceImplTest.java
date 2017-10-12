package com.bpbbank.pasqyra.service;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import org.junit.Test;

import com.bpbbank.ClientReport;
import java.text.ParseException;

public class ServiceImplTest {

	ServiceImpl serviceImpl = new ServiceImpl();
	Connection connection = this.getConnection();

	@Test
	public void test() throws SQLException, ParseException {
		String periodStart = "2017.08.11", periodEnd = "2017.08.18", id = "70433736", account = "1300001001505494",
				currency = "978";
		ClientReport clientReport = serviceImpl.getClientMonthlyReport(periodStart, periodEnd, id, account, currency);
		assertEquals("70433736", clientReport.getCustomerId());
		assertEquals("TAHIR ZAJMI", clientReport.getAdresa());
		assertEquals("PRISHTINE", clientReport.getInfoLokacioni());
		assertEquals("1300001001505494", clientReport.getAccount());
		assertEquals("2.00", clientReport.getShumaEMbajtur());
		assertEquals("978", clientReport.getCurrency());
		// try {
		// CallableStatement callableStatement = connection.prepareCall("{call
		// pasqyrat_e_klienteve(?, ?, ?, ?, ?)}");
		// callableStatement.setString(1, "2017.08.11");
		// callableStatement.setString(2, "2017.08.18");
		// callableStatement.setString(4, "70433736");
		// callableStatement.setString(3, "1300001001505494");
		// callableStatement.setString(5, "978");
		// ResultSet resultSet = callableStatement.executeQuery();
		// System.out.println(resultSet);
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
                
	}

	public Connection getConnection() {
		Driver driver = new SQLServerDriver();
		try {
			DriverManager.registerDriver(driver);
			// return
			// DriverManager.getConnection(properties.getProperty("db.url"),
			// properties.getProperty("db.username"),
			// properties.getProperty("db.password"));
			return DriverManager.getConnection("jdbc:sqlserver://192.178.10.57:1433;databaseName=bpbtest", "pasqyrat",
					"krejt123456!");
		} catch (SQLException sqlException) {
			sqlException.printStackTrace();
			throw new RuntimeException(sqlException.getMessage());
		}
	}
        
    
}
