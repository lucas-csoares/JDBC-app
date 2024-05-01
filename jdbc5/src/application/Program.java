package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;
import db.DbIntegrityException;

public class Program {

	public static void main(String[] args) {
		
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement(
					"DELETE FROM department "
					+ "WHERE "
				    + "Id = ?"
					);
			
			//st.setInt(1, 5); //primeiro interrogação cujo valor é 5
			st.setInt(1, 2); // Caso tente apagar um id que tem vinculação com um empregado, a exceção é lançada indicando erro de integridade referencial
			
			int linhasAfetadas = st.executeUpdate();
			
			System.out.println("Pronto! Linhas afetadas: " + linhasAfetadas);
			
		}
		catch(SQLException e) {
			//e.printStackTrace(); //Impressão do stacktrace
			throw new DbIntegrityException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		

	}
	
}
