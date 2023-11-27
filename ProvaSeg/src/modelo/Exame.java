package modelo;

public class Exame {
	private int id;
	private String resultado;
	
	private Paciente paciente;
	private Medico medico;
	
	public Exame() {
		
	}
	
	public Exame(int id, String resultado, Paciente paciente, Medico medico) {
		this.id = id;
		this.resultado = resultado;
		this.paciente = paciente;
		this.medico = medico;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getResultado() {
		return resultado;
	}
	public void setResultado(String resultado) {
		this.resultado = resultado;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	


    

}
