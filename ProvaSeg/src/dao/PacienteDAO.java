package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javaDB.FabricaDeConexoes;

public class PacienteDAO {
	
private Connection con;
	public PacienteDAO() throws SQLException{
		this.con =  FabricaDeConexoes.getConnection();
	}
	
	public boolean getPacientebyId(int id) throws SQLException {
		String sql = "SELECT * FROM paciente WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rset = stmt.executeQuery();
		
		if (rset.next()) {
			return true;
		}
		return false;
	}
}