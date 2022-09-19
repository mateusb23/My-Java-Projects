package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {
	// Aqui nós vamos passar os parâmetros para acessar o nosso banco MySQL

	// NOME DO USUÁRIO
	private static final String USERNAME = "administrador";

	// SENHA DO BANCO DE DADOS
	private static final String PASSWORD = "server2021";

	// CAMINHO DO BANCO DE DADOS, PORTA, NOME DO BANCO DE DADOS
	private static final String DATABASE_URL = "jdbc:mysql://192.168.0.122:3306/agenda";

	/*
	 *  CONEXÃO COM O BANCO DE DADOS
	 */
	public static Connection createConnectionToMysql() throws Exception {
		// FAZ COM QUE A CLASSE SEJA CARREGADA PELA JVM
		Class.forName("com.mysql.jdbc.Driver");
		
		// CRIA A CONEXÃO COM O BANCO DE DADOS
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
		
		return connection;
	}

	public static void main(String[] args) throws Exception {
		
		// RECUPERAR UMA CONEXÃO COM O BANCO DE DADOS
		Connection con = createConnectionToMysql();
		
		// TESTAR SE A CONEXÃO É NULA
		if(con != null) {
			System.out.println("Conexão obtida com sucesso!");
			con.close();
		}
		
	}
	
}
