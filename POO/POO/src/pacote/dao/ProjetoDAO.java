package pacote.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import pacote.conexao.ConnectionFactory;
import pacote.projeto.Projeto;

public class ProjetoDAO {
	public void Cadastrar(Projeto projeto) {

		String sql = "INSERT INTO tb_projeto (nm_projeto, dt_inicio, dt_fim) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Cria uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Cria um PreparedStatement para execução de uma Query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			//Adiciona os valores que são esperados pela Query
			pstm.setString(1, projeto.getNm_projeto());
			pstm.setDate(2, new java.sql.Date(projeto.getDt_inicio().getTime()));
			pstm.setDate(3, new java.sql.Date(projeto.getDt_fim().getTime()));
			
			//Executar a Query
			pstm.execute();
			
			System.out.println("Projeto Registrado!");
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			//Fechar as conexões
			try {
				if(pstm!=null) {
					pstm.close();
				}
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void Atualizar(Projeto projeto) {
		String sql = 	"UPDATE tb_projeto SET nm_projeto = ?, dt_inicio = ?, dt_fim = ?" +
						"WHERE cod_projeto = ?";
		
		Connection conn = null;
		PreparedStatement pstm =null;
		
		try {
			//Cria conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Cria a classe para executar a Query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores que irão atualizar os antigos
			pstm.setString(1, projeto.getNm_projeto());
			pstm.setDate(2, new java.sql.Date(projeto.getDt_inicio().getTime()));
			pstm.setDate(3, new java.sql.Date(projeto.getDt_fim().getTime()));
			//Parâmetro WHERE do UPDATE (Código do empregado a atualizar)
			pstm.setInt(4, projeto.getCod_projeto());
			
			//Executar a Query
			pstm.execute();

		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
	}

	public void DeletarProjeto(int cod_projeto) {
		String sql = "DELETE FROM tb_projeto WHERE cod_projeto = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, cod_projeto);
			
			pstm.execute();			
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstm!=null) {
					pstm.close();
				}
				
				if(conn!=null) {
					conn.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public List<Projeto> getProjetos(){
		
		String sql = "SELECT * FROM tb_projeto";
		
		
		List<Projeto> projetos = new ArrayList<Projeto>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que recupera os dados do SELECT
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Projeto projeto = new Projeto();
				
				//Recuperar id
				projeto.setCod_projeto(rset.getInt("cod_projeto"));
				projeto.setNm_projeto(rset.getString("nm_projeto"));
				projeto.setDt_inicio(rset.getDate("dt_inicio"));
				projeto.setDt_fim(rset.getDate("dt_fim"));
				
				projetos.add(projeto);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(rset!=null) {
					rset.close();
				}				
				if(conn!=null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
		return projetos;
	}
}
