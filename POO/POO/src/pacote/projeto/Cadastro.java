package pacote.projeto;

public class Cadastro {
	
	private int cod_cadastro;
	private int cod_empregado;
	private String nm_nome;
	private int cod_projeto;
	private String nm_projeto;
	private int id_cargo;
	
	public int getCod_cadastro() {
		return cod_cadastro;
	}
	public void setCod_cadastro(int cod_cadastro) {
		this.cod_cadastro = cod_cadastro;
	}
	public int getCod_empregado() {
		return cod_empregado;
	}
	public void setCod_empregado(int cod_empregado) {
		this.cod_empregado = cod_empregado;
	}
	public String getNm_nome() {
		return nm_nome;
	}
	public void setNm_nome(String nm_nome) {
		this.nm_nome = nm_nome;
	}
	public int getCod_projeto() {
		return cod_projeto;
	}
	public void setCod_projeto(int cod_projeto) {
		this.cod_projeto = cod_projeto;
	}
	public String getNm_projeto() {
		return nm_projeto;
	}
	public void setNm_projeto(String nm_projeto) {
		this.nm_projeto = nm_projeto;
	}
	public int getId_cargo() {
		return id_cargo;
	}
	public void setId_cargo(int id_cargo) {
		this.id_cargo = id_cargo;
	}
	public String getNm_cargo() {
		return nm_cargo;
	}
	public void setNm_cargo(String nm_cargo) {
		this.nm_cargo = nm_cargo;
	}
	private String nm_cargo;
	
	
}
