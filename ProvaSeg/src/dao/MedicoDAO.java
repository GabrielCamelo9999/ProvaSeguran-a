package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javaDB.FabricaDeConexoes;

public class MedicoDAO {
	
private Connection con;
	public MedicoDAO() throws SQLException{
		this.con =  FabricaDeConexoes.getConnection();
	}
	
	public boolean getMedicobyId(int id) throws SQLException {
		String sql = "SELECT * FROM medico WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rset = stmt.executeQuery();
		
		if (rset.next()) {
			return true;
		}
		return false;
	}
}
