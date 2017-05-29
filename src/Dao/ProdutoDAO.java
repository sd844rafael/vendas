package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basicas.Pedido;
import basicas.Produto;
import util.ConnectionFactory;

public class ProdutoDAO {

	private Connection connection;

	public ProdutoDAO() {

		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void inserir(Produto produto) {

		String sql = "INSERT INTO produto (titulo, descricao, preco, nomeImagem) VALUES (?,?,?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);

			stmt.setString(1, produto.getTitulo());
			stmt.setString(2, produto.getDescricao());
			stmt.setDouble(3, produto.getPreco());
			stmt.setString(4, produto.getNomeImagem());
			
			stmt.execute();
			connection.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);

		}
		
		}
	
	public List<Produto> listar() {

		try {
			List<Produto> listaProduto = new ArrayList<Produto>();
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM produto ORDER BY titulo");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setTitulo(rs.getString("Titulo"));
				produto.setDescricao(rs.getString("Descrição"));
				produto.setPreco(rs.getDouble("Preco"));
				

				listaProduto.add(produto);
			}

			rs.close();
			stmt.close();
			connection.close();

			return listaProduto;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	public void alterar(Produto produto) {

		try {

		    String sql = "UPDATE contato SET titulo= ? , descricao= ? , preco= ? , nomeImagem = ? WHERE id = ?";
		    PreparedStatement stmt = connection.prepareStatement(sql);
            
		    stmt.setString(2, produto.getTitulo());
		    stmt.setString(2, produto.getDescricao());
		    stmt.setDouble(2, produto.getPreco());
		    stmt.setString(2, produto.getNomeImagem());
		    stmt.setInt(3, produto.getId());

		    stmt.execute();
		    connection.close();

		} catch (SQLException e) {
		    throw new RuntimeException(e);
		}
	    }   
	
	public void LimparTabela(int id){
		try{
			PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM produto where id = ?");
			stmt.execute();
			connection.close();
		} catch (SQLException e){
			throw new RuntimeException(e);
		}
	}
}  
