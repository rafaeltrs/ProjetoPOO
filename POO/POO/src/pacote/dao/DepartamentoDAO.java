package pacote.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import pacote.conexao.ConnectionFactory;
import pacote.projeto.Departamento;

public class DepartamentoDAO {
	public void Cadastrar(Departamento departamento) {

		String sql = "INSERT INTO tb_departamento (nm_departamento) VALUES (?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Cria uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Cria um PreparedStatement para execução de uma Query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			//Adiciona os valores que são esperados pela Query
			pstm.setString(1, departamento.getNm_departamento());

			
			//Executar a Query
			pstm.execute();
			
			System.out.println("Departamento Registrado!");
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
	
	public void Atualizar(Departamento departamento) {
		String sql = 	"UPDATE tb_departamento SET nm_departamento = ?" +
						"WHERE cod_departamento = ?";
		
		Connection conn = null;
		PreparedStatement pstm =null;
		
		try {
			//Cria conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Cria a classe para executar a Query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores que irão atualizar os antigos
			pstm.setString(1, departamento.getNm_departamento());
			//Parâmetro WHERE do UPDATE (Código do departamento a atualizar)
			pstm.setInt(2, departamento.getCod_departamento());
			
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

	public void DeletarDepto(int cod_departamento) {
		String sql = "DELETE FROM tb_departamento WHERE cod_departamento = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, cod_departamento);
			
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
	
	public List<Departamento> getDepartamentos(){
		
		String sql = "SELECT * FROM tb_departamento";
		
		
		List<Departamento> departamentos = new ArrayList<Departamento>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que recupera os dados do SELECT
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Departamento departamento = new Departamento();
				
				//Recuperar id
				departamento.setCod_departamento(rset.getInt("cod_departamento"));
				departamento.setNm_departamento(rset.getString("nm_departamento"));
				
				departamentos.add(departamento);
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
		return departamentos;
	}
}
