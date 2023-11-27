package modelo;

public class Medico {
    private int id;
    private String nome;
    private String cpf;
    private String especialidade;

    public Medico(int id, String nome, String cpf, String especialidade) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.especialidade = especialidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", CPF: " + cpf + ", Especialidade: " + especialidade;
    }
}