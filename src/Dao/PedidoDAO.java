package Dao;

import java.util.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import basicas.*;
import util.ConnectionFactory;

public class PedidoDAO {

	private Connection connection;

	public PedidoDAO() {

		try {
			this.connection = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public List<Pedido> listar() {

		try {
			List<Pedido> listaPedido = new ArrayList<Pedido>();
			PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM pedido ORDER BY id");

			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {

				Pedido pedido = new Pedido();
				pedido.setId(rs.getInt("id"));
				pedido.setValorTotal(rs.getDouble("ValorTotal"));
				pedido.setDataPedido(rs.getDate("DataPedido"));

				listaPedido.add(pedido);
			}

			rs.close();
			stmt.close();
			connection.close();

			return listaPedido;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}

	public void inserir(Pedido pedido) {

		String sql = "INSERT INTO produto (valorTotal,dataPedido) VALUES (?,?)";
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);

	
			
			stmt.setDouble(1, pedido.getValorTotal());
			stmt.setDate(2, new java.sql.Date(pedido.getDataPedido().getTime()));
			
			stmt.execute();
			connection.close();

		} catch (SQLException e) {
			throw new RuntimeException(e);

		}

	}
	
	public void alterar(Pedido pedido) {

		try {

		    String sql = "UPDATE contato SET DataPedido = ?, ValorTotal = ? WHERE id = ?";
		    PreparedStatement stmt = connection.prepareStatement(sql);

		    stmt.setDate(1, new java.sql.Date(pedido.getDataPedido().getTime()));
		    stmt.setDouble(2, pedido.getValorTotal());
		    stmt.setLong(3, pedido.getId());

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
