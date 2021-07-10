package pacote.projeto;

import java.util.Date;

public class Projeto {
	
	private int cod_projeto;
	private String nm_projeto;
	private Date dt_inicio;
	private Date dt_fim;
	
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
	public Date getDt_inicio() {
		return dt_inicio;
	}
	public void setDt_inicio(Date dt_inicio) {
		this.dt_inicio = dt_inicio;
	}
	public Date getDt_fim() {
		return dt_fim;
	}
	public void setDt_fim(Date dt_fim) {
		this.dt_fim = dt_fim;
	}
	
}
