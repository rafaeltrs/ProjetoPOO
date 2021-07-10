package pacote.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import com.mysql.jdbc.PreparedStatement;
import pacote.conexao.ConnectionFactory;
import pacote.projeto.Cadastro;

public class CadastroDAO {
	
	public void Cadastrar(Cadastro cadastro) {

		String sql = "INSERT INTO tb_cadastro (cod_empregado, cod_projeto, id_cargo) VALUES"
					+ "	(?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Cria uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Cria um PreparedStatement para execução de uma Query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			//Adiciona os valores que são esperados pela Query
			pstm.setInt(1, cadastro.getCod_empregado());
			pstm.setInt(2, cadastro.getCod_projeto());
			pstm.setInt(3, cadastro.getId_cargo());
			
			//Executar a Query
			pstm.execute();
			
			System.out.println("Empregado Cadastrado no Projeto!");
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
	
	public void Atualizar(Cadastro cadastro) {
		String sql = 	"UPDATE tb_cadastro SET cod_empregado = ?, cod_projeto = ?, id_cargo = ? " +
						"WHERE cod_cadastro = ?";
		
		Connection conn = null;
		PreparedStatement pstm =null;
		
		try {
			//Cria conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Cria a classe para executar a Query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores que irão atualizar os antigos
			pstm.setInt(1, cadastro.getCod_empregado());
			pstm.setInt(2, cadastro.getCod_projeto());
			pstm.setInt(3, cadastro.getId_cargo());
			//Parâmetro WHERE do UPDATE (Código do empregado a atualizar)
			pstm.setInt(4, cadastro.getCod_cadastro());
			
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

	public void DeletarCadastro(int cod_cadastro) {
		String sql = "DELETE FROM tb_cadastro WHERE cod_cadastro = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, cod_cadastro);
			
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
	
	
	//VISUALIZA TODOS OS CADASTROS
	public List<Cadastro> getCadastros(){
		
		String sql = 	"SELECT 	c.cod_cadastro AS cod_cadastro,"
						+ "			e.nm_nome AS nm_nome,"
						+ "        	p.nm_projeto AS nm_projeto,"
						+ "        	f.nm_cargo AS nm_cargo "
						+ "FROM tb_cadastro AS c "
						+ "INNER JOIN tb_empregado AS e "
						+ "ON c.cod_empregado = e.cod_empregado "
						+ "INNER JOIN tb_projeto AS p "
						+ "ON p.cod_projeto = c.cod_projeto "
						+ "INNER JOIN tb_funcao AS f "
						+ "ON c.id_cargo = f.id_cargo;";
		
		
		List<Cadastro> cadastros = new ArrayList<Cadastro>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que recupera os dados do SELECT
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Cadastro cadastro = new Cadastro();
				
				//Recuperar id
				cadastro.setCod_cadastro(rset.getInt("cod_cadastro"));
				cadastro.setNm_nome(rset.getString("nm_nome"));
				cadastro.setNm_projeto(rset.getString("nm_projeto"));
				cadastro.setNm_cargo(rset.getString("nm_cargo"));
				
				cadastros.add(cadastro);
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
		return cadastros;
	}

	
	// VISUALIZA TODOS OS CADASTROS NO DETERMINADO PROJETO
	public List<Cadastro> VisualizaCadastroPorProjeto(int cod_projeto){
		
		String sql = 	"SELECT 	c.cod_cadastro AS cod_cadastro,"
						+ "			e.nm_nome AS nm_nome,"
						+ "        	p.nm_projeto AS nm_projeto,"
						+ "        	f.nm_cargo AS nm_cargo "
						+ "FROM tb_cadastro AS c "
						+ "INNER JOIN tb_empregado AS e "
						+ "ON c.cod_empregado = e.cod_empregado "
						+ "INNER JOIN tb_projeto AS p "
						+ "ON p.cod_projeto = c.cod_projeto "
						+ "INNER JOIN tb_funcao AS f "
						+ "ON c.id_cargo = f.id_cargo "
						+ "WHERE p.cod_projeto = ? ;";
		
		
		List<Cadastro> vw_cad = new ArrayList<Cadastro>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que recupera os dados do SELECT
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, cod_projeto);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Cadastro cadastro = new Cadastro();
				
				//Recuperar id
				cadastro.setCod_cadastro(rset.getInt("cod_cadastro"));
				cadastro.setNm_nome(rset.getString("nm_nome"));
				cadastro.setNm_projeto(rset.getString("nm_projeto"));
				cadastro.setNm_cargo(rset.getString("nm_cargo"));
				
				vw_cad.add(cadastro);
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
		return vw_cad;
	}
	
}
