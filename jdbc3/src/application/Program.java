package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null; 
		PreparedStatement ps = null; 

		//Atalho para comentar várias linhas: CTRL + SHIFT + C
		try {
			conn = DB.getConnection(); // Conectar ao banco
//			
//			ps = conn.prepareStatement(
//					"INSERT INTO seller "
//					+ "(Name, Email, BirthDate, BaseSalary, DepartmentId) "
//					+ "VALUES "
//					+ "(?, ?, ?, ?, ?)", //placeHolder (lugar onde depois você coloca valor)
//					Statement.RETURN_GENERATED_KEYS
//					); 
//			
//			ps.setString(1, "Lucas Soares");
//			ps.setString(2, "lucas@gmail.com");
//			ps.setDate(3, new java.sql.Date(sdf.parse("19/04/1991").getTime()));
//			ps.setDouble(4, 1500.00);
//			ps.setInt(5, 4);
		
			ps = conn.prepareStatement(
					"insert into department (Name) values ('D1'), ('D2')",
					Statement.RETURN_GENERATED_KEYS
					);
			
			int rowsAffected = ps.executeUpdate();
			
			if(rowsAffected > 0) {
				ResultSet rs = ps.getGeneratedKeys(); // Uma tabela contendo Ids
				while(rs.next()) {
					int id = rs.getInt(1); //valor da primeira (1) coluna
					System.out.println("Pronto! " + "id = " + id);
				}
			}
			else {
				System.out.println("No rown affected!");
			}
			
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
//		catch(ParseException e) {
//			e.printStackTrace(); 
//		}
		finally {
			DB.closeStatement(ps);
			DB.closeConnection();
		}
	}

}
