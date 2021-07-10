package pacote.aplicacao;

import java.util.Date;

import pacote.dao.CadastroDAO;
import pacote.dao.DepartamentoDAO;
import pacote.dao.EmpregadoDAO;
import pacote.dao.ProjetoDAO;
import pacote.projeto.Cadastro;
import pacote.projeto.Departamento;
import pacote.projeto.Empregado;
import pacote.projeto.Projeto;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/**
		 * 
		 * EMPREGADO
		 * 
		 */
		
		EmpregadoDAO empregadoDAO = new EmpregadoDAO();
		
		Empregado empregado = new Empregado();
		
		//Inserção dos dados INSERT
		
		empregado.setDepartamento(1);
		empregado.setNome("Rafael Alves");
		empregado.setSalario(2295.22);
		empregado.setEndereco("Rua do Limoeiro, 230");
		empregado.setTelefone(1124608423);
		
		empregadoDAO.Cadastrar(empregado);
		
		//Atualização do empregado
		Empregado e1 = new Empregado();
		e1.setDepartamento(2);
		e1.setNome("Alexandre Guimaraes");
		e1.setSalario(1895.22);
		e1.setEndereco("Rua da Laranjeira, 129");
		e1.setTelefone(2143122384);	
		e1.setCod_empregado(1); //PK do Banco de dados | Para atualizar
		
		//empregadoDAO.Atualizar(e1);
		
		//Deletar o empregado pelo Código de Empregado
		empregadoDAO.DeletarPeloCod(3);
		
		//Visualização dos dados SELECT
		
		for(Empregado emp : empregadoDAO.getEmpregados()) {
			System.out.println("Cod_Emp: " + emp.getCod_empregado());
			System.out.println("Cod_Depto: " + emp.getDepartamento());
			System.out.println("Nome: " + emp.getNome());
			System.out.println("Salario: " + emp.getSalario());
			System.out.println("Endereço: " + emp.getEndereco());
			System.out.println("Telefone: " + emp.getTelefone());
			System.out.println("-----------------------");
		}
		
		
		 /* 
		 *  -------------------------------------------------------------------------------------------------------------
		 * DEPARTAMENTO
		 */
		 
		
		DepartamentoDAO departamentoDAO = new DepartamentoDAO();
		
		Departamento departamento = new Departamento();
		
		//INSERT Departamento
		departamento.setNm_departamento("Financeiro");
		
		departamentoDAO.Cadastrar(departamento);
		
		//UPDATE Departamento
		Departamento d1 = new Departamento();
		d1.setNm_departamento("Expedição");
		d1.setCod_departamento(2);//PK
		
		departamentoDAO.Atualizar(d1);
		
		//Deletar o empregado pelo Código de Empregado
		departamentoDAO.DeletarDepto(8);
		
		//Visualização dos dados SELECT
		
		for(Departamento depto : departamentoDAO.getDepartamentos()) {
			System.out.println("Cod_Emp: " + depto.getCod_departamento());
			System.out.println("Cod_Depto: " + depto.getNm_departamento());
			System.out.println("-----------------------");
		}
		
		
		/*
		* --------------------------------------------------------------------------------------------------
		* PROJETO
		 */
		
		
		ProjetoDAO projetoDAO = new ProjetoDAO();
		
		Projeto projeto = new Projeto();
		
		//INSERT Departamento
		projeto.setNm_projeto("Controle de Estoque");
		projeto.setDt_inicio(new Date(20210709));
		projeto.setDt_fim(new Date(20210731));
		
		projetoDAO.Cadastrar(projeto);
		
		//UPDATE Projeto
		Projeto p1 = new Projeto();
		p1.setNm_projeto("Plataforma Secundária");
		p1.setDt_inicio(new java.sql.Date(20210101));
		p1.setDt_fim(new java.sql.Date(20210201));
		
		p1.setCod_projeto(2); //PK Projeto
		
		projetoDAO.Atualizar(p1);
		
		//Deletar o Projeto pelo Código de Empregado
		projetoDAO.DeletarProjeto(5);
		
		//Visualização dos dados SELECT
		
		for(Projeto proj : projetoDAO.getProjetos()) {
			System.out.println("Cod_Proj: " + proj.getCod_projeto());
			System.out.println("Projeto: " + proj.getNm_projeto());
			System.out.println("Data Inicio: " + proj.getDt_inicio());
			System.out.println("Data Fim: " + proj.getDt_fim());
			System.out.println("-----------------------");
		}
		
		/*
		*
		*-------------------------------------------------------------------------------------------------
		*
		* CADASTRO
		
		*/
		
		CadastroDAO cadastroDAO = new CadastroDAO();
		
		//INSERT Cadastro
		Cadastro cadastro = new Cadastro();
		
		//Recuperar id
		cadastro.setCod_empregado(1);
		cadastro.setCod_projeto(1);
		cadastro.setId_cargo(1);
		
		cadastroDAO.Cadastrar(cadastro);
		
		//UPDATE Projeto
		Cadastro c1 = new Cadastro();
		
		c1.setCod_empregado(4);
		c1.setCod_projeto(1);;
		c1.setId_cargo(1);
		
		c1.setCod_cadastro(1); //PK Projeto
		
		cadastroDAO.Atualizar(c1);
		
		//Deletar o Projeto pelo Código de Empregado
		cadastroDAO.DeletarCadastro(10);
		
		//Visualização dos dados SELECT
		
		for(Cadastro cad : cadastroDAO.getCadastros()) {
			System.out.println("Cod_Cad: " + cad.getCod_cadastro());
			System.out.println("Nome: " + cad.getNm_nome());
			System.out.println("Projeto: " + cad.getNm_projeto());
			System.out.println("Cargo: " + cad.getNm_cargo());
			System.out.println("-----------------------");
		}
		
		
		
		
		
		for(Cadastro cad : cadastroDAO.VisualizaCadastroPorProjeto(2)) {
			System.out.println("Cod_Cad: " + cad.getCod_cadastro());
			System.out.println("Nome: " + cad.getNm_nome());
			System.out.println("Projeto: " + cad.getNm_projeto());
			System.out.println("Cargo: " + cad.getNm_cargo());
			System.out.println("-----------------------");
		}
		
		
		
	}

}
