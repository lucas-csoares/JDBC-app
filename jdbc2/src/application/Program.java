package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		/**
		 * Bloco try é necessário pois estou acessando um recurso externo que pode gerar exceção
		 */
		try {
			conn = DB.getConnection();
			
			st = conn.createStatement(); 
			
			rs = st.executeQuery("select * from department"); // Resultado da consulta é atribuído a variável
			
			while(rs.next()) {
				System.out.println(rs.getInt("Id") + ", " + rs.getString("Name"));
			}
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
	}

}
