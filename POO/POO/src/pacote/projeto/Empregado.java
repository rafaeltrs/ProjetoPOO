package pacote.projeto;

public class Empregado {
	
	private int cod_empregado;
	private int departamento;
	private String nome;
	private double salario;
	private String endereco;
	private int telefone;
	
	public int getCod_empregado() {
		return cod_empregado;
	}
	public void setCod_empregado(int cod_empregrado) {
		this.cod_empregado = cod_empregrado;
	}
	public int getDepartamento() {
		return departamento;
	}
	public void setDepartamento(int departamento) {
		this.departamento = departamento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getEndereco() {
		return endereco;
	}
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	public int getTelefone() {
		return telefone;
	}
	public void setTelefone(int telefone) {
		this.telefone = telefone;
	}
}
