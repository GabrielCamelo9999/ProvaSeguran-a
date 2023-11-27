package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import javaDB.FabricaDeConexoes;
import modelo.Exame;
import modelo.Medico;
import modelo.Paciente;
import Seguro.SegurancaUtils;

public class ExameDAO {
	
	private Connection con;
	
	public ExameDAO() throws SQLException{
		this.con =  FabricaDeConexoes.getConnection();
	}
	
	public void adiciona(String resultado, int id_paciente, int id_medico , String chave) throws Exception {
		
		SecretKey chave2 = SegurancaUtils.criarChaveSecreta(chave);
		
		String sql = "INSERT INTO sodio (resultado, "
				+ "id_paciente, id_medico) VALUES (?,?,?)";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, SegurancaUtils.criptografa(resultado, chave2));
		stmt.setInt(2, id_paciente);
		stmt.setInt(3, id_medico);
				
		stmt.execute();
		stmt.close();
	}
	
	public List<Exame> getLista() throws SQLException{
		String sql = "SELECT a.id, a.resultado, m.id, m.nome, m.cpf, m.especialidade,"
				+ "p.id, p.nome, p.cpf FROM sodio a "
				+ "JOIN medico m ON a.id_medico = m.id "
				+ "JOIN paciente p ON a.id_paciente = p.id";
		PreparedStatement stmt = con.prepareStatement(sql);
		ResultSet rset = stmt.executeQuery();
		
		List<Exame> exames = new ArrayList<Exame>();
		
		while (rset.next()) {
			Medico medico = new Medico(rset.getInt("m.id"),rset.getString("m.nome") ,
					rset.getString("m.cpf"), rset.getString("m.especialidade"));
			Paciente paciente = new Paciente(rset.getInt("p.id"),rset.getString("p.nome") ,
					rset.getString("p.cpf"));
			Exame exame = new Exame(rset.getInt("a.id"), rset.getString("a.resultado"), paciente, medico);
			exames.add(exame);
			
		}
		rset.close();
		stmt.close();
		
		return exames;
	}
	
	public Exame getResultadobyId(int id) throws SQLException {
		String sql = "SELECT * FROM sodio WHERE id = ?";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rset = stmt.executeQuery();
		
		if (rset.next()) {
			Exame exameResult = new Exame();
			exameResult.setResultado(rset.getString("resultado"));
			return exameResult;
		}
		return null;
	}

}