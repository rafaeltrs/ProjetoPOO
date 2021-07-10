package pacote.dao;

import pacote.conexao.ConnectionFactory;
import pacote.projeto.Empregado;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

public class EmpregadoDAO {
	
	public void Cadastrar(Empregado empregado) {

		String sql = "INSERT INTO tb_empregado (cod_departamento, nm_nome, vl_salario, nm_endereco, vl_telefone) VALUES (?, ?, ?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			//Cria uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Cria um PreparedStatement para execução de uma Query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			//Adiciona os valores que são esperados pela Query
			pstm.setInt(1, empregado.getDepartamento());
			pstm.setString(2, empregado.getNome());
			pstm.setDouble(3, empregado.getSalario());
			pstm.setString(4, empregado.getEndereco());
			pstm.setInt(5, empregado.getTelefone());
			
			//Executar a Query
			pstm.execute();
			
			System.out.println("Empregado Registrado!");
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
	
	public void Atualizar(Empregado empregado) {
		String sql = 	"UPDATE tb_empregado SET cod_departamento = ?, nm_nome = ?, vl_salario = ?, nm_endereco = ?, vl_telefone = ? " +
						"WHERE cod_empregado = ?";
		
		Connection conn = null;
		PreparedStatement pstm =null;
		
		try {
			//Cria conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Cria a classe para executar a Query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar os valores que irão atualizar os antigos
			pstm.setInt(1, empregado.getDepartamento());
			pstm.setString(2, empregado.getNome());
			pstm.setDouble(3, empregado.getSalario());
			pstm.setString(4, empregado.getEndereco());
			pstm.setInt(5, empregado.getTelefone());
			//Parâmetro WHERE do UPDATE (Código do empregado a atualizar)
			pstm.setInt(6, empregado.getCod_empregado());
			
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

	public void DeletarPeloCod(int cod_empregado) {
		String sql = "DELETE FROM tb_empregado WHERE cod_empregado = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, cod_empregado);
			
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
	
	public List<Empregado> getEmpregados(){
		
		String sql = "SELECT * FROM tb_empregado";
		
		
		List<Empregado> empregados = new ArrayList<Empregado>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		//Classe que recupera os dados do SELECT
		ResultSet rset = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) {
				Empregado empregado = new Empregado();
				
				//Recuperar id
				empregado.setCod_empregado(rset.getInt("cod_empregado"));
				empregado.setDepartamento(rset.getInt("cod_departamento"));
				empregado.setNome(rset.getString("nm_nome"));
				empregado.setSalario(rset.getFloat("vl_salario"));
				empregado.setEndereco(rset.getString("nm_endereco"));
				empregado.setTelefone(rset.getInt("vl_telefone"));
				
				empregados.add(empregado);
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
		return empregados;
	}
}
