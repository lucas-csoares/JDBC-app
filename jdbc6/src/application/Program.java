package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbException;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		
		try {
			conn = DB.getConnection();
			
			conn.setAutoCommit(false); // Todas as operações ficarão pendentes de uma confirmação explícita do programa
			
			st = conn.createStatement();
			
			
			int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");//Número de linhas afetas
			
			//Gerando uma exceção no meio do caminho para simular falha na transação
			//int x = 1;
			//if(x < 2) {
			//	throw new SQLException("Fake error ");
			//}
			// Mesmo após a exceção, o primeiro comando foi executado na base de dados
			
			int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");//Número de linhas afetas
			
			
			conn.commit(); //confirmação da operação (agora sim a operação termina)
			
			System.out.println("Rows1: " + rows1);
			System.out.println("Rows2: " + rows2);
		}
		catch(SQLException e) {
			try {
				conn.rollback();
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			} catch (SQLException e1) {
				throw new DbException("Transaction rolled back! Caused by: " + e.getMessage());
			}
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		

	}
	
}
