package pacote.conexao;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	//Usu�rio MySQL
	private static final String USERNAME = "root";
	
	//Senha MySQL
	private static final String PASSWORD = "";
	
	//Caminho do Banco de Dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/db_projeto";
	
	/**
	 * 	Conex�o com o Banco de dados
	 * */
	public static Connection createConnectionToMySQL() throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}
	
	public static void main(String[] args) throws Exception {
		
		Connection con = createConnectionToMySQL();
		
		if(con != null) {
			System.out.println("Conex�o obtida com sucesso!");
			con.close();
		}
	}
}
