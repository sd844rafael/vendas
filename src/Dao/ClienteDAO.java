package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import basicas.Cliente;

import util.ConnectionFactory;

public class ClienteDAO {
	private Connection connection;

	public ClienteDAO() {

		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
		
		public List<Cliente> listar() {

			try {
				List<Cliente> listaCliente = new ArrayList<Cliente>();
				PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM produto ORDER BY nome");

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {

					Cliente cliente = new Cliente();
					cliente.setId(rs.getInt("id"));
					cliente.setNome(rs.getString("Nome"));
					cliente.setEmail(rs.getString("Email"));
					cliente.setSenha(rs.getString("Senha"));
					

					listaCliente.add(cliente);
				}

				rs.close();
				stmt.close();
				connection.close();

				return listaCliente;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
			
		}public void inserir(Cliente cliente) {

			String sql = "INSERT INTO produto (nome,email,senha) VALUES (?,?,?)";
			PreparedStatement stmt;
			try {
				stmt = connection.prepareStatement(sql);

				stmt.setString(1, cliente.getNome());
				stmt.setString(2, cliente.getEmail());
				stmt.setString(3, cliente.getSenha());
				
				
				stmt.execute();
				connection.close();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);

			}
			
			}
		
		public void LimparTabela(int id){
			try{
				PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM cliente where id = ?");
				stmt.execute();
				connection.close();
			} catch (SQLException e){
				throw new RuntimeException(e);
			}
		}
		
		
		 
		 }

