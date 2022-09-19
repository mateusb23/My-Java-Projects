package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO {

	/*
	 * É AQUI ONDE SERÁ FEITA TODA A PARAMETRIZAÇÃO DO JAVA COM O BANCO, POIS
	 * A ESTRUTURA QUE ESTAMOS FAZENDO É ORIENTADA A OBJETOS E O BANCO DE DADOS
	 * É UMA ESTRUTURA RELACIONAL. 
	 */

	/*
	 * CRUD
	 * C: CREATE
	 * R: READ
	 * U: UPDATE
	 * D: DELETE
	 */

	public void save(Contato contato) {

		String sql = "INSERT INTO contatos(nome, idade, dataCadastro) VALUES (?, ?, ?)";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {	
			// CRIAR UMA CONEXÃO COM O BANCO DE DADOS
			conn = ConnectionFactory.createConnectionToMysql();

			// CRIAMOS UMA PrepareStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// EXECUTAR A QUERY
			pstm.execute();

			System.out.println("Contato salco com sucesso!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			// FECHAR AS CONEXÕES
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (pstm != null) {
						pstm.close();
					}
					
					if (conn != null) {
						conn.close();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void update(Contato contato) {
		
		String sql = "UPDATE contatos SET nome = ?, idade = ?, dataCadastro = ?" + 
		"WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try {
			// Criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMysql();
			
			// Criar a classe para executar a query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			// Adicionar os valores para atualizar
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getId());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			// Qual o id do registro que deseja atualizar?
			pstm.setInt(4, contato.getId());
			
			// Executar a query
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void deleteByID (int id) {
		
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conn = null;
		
		PreparedStatement pstm = null;
		
		try {
			conn = ConnectionFactory.createConnectionToMysql();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

	public List<Contato> getContatos() {

		String sql = "SELECT * FROM contatos";

		List<Contato> contatos = new ArrayList<Contato>();

		Connection conn = null;
		PreparedStatement pstm = null;
		// CLASSE QUE VAI RECUPERAR OS DADOS DO BANCO. ***SELECT***
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMysql();

			pstm = (PreparedStatement) conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Contato contato = new Contato();

				//Recuperar o id
				contato.setId(rset.getInt("id"));
				//Recuperar o nome
				contato.setNome(rset.getString("nome"));
				//Recuperar a idade
				contato.setIdade(rset.getInt("idade"));
				//Recuperar a data de cadastro
				contato.setDataCadastro(rset.getDate("dataCadastro"));

				contatos.add(contato);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}

				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return contatos;
	}

}

