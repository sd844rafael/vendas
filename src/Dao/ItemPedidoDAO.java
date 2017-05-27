package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import basicas.Cliente;
import basicas.ItemPedido;
import util.ConnectionFactory;

public class ItemPedidoDAO {
	
		private Connection connection;

		public ItemPedidoDAO() {

			try {
				this.connection = new ConnectionFactory().getConnection();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}

		}
		

		public List<ItemPedido> listar() {

			try {
				List<ItemPedido> listaItemPedido = new ArrayList<ItemPedido>();
				PreparedStatement stmt = this.connection.prepareStatement("SELECT * FROM produto ORDER BY pedido");

				ResultSet rs = stmt.executeQuery();

				while (rs.next()) {

					ItemPedido itempedido = new ItemPedido();
					itempedido.setPrecoUnitario(rs.getDouble("PrecoUnitario"));
					itempedido.setQuantidade(rs.getInt("Quantidade"));
					itempedido.setPrecoTotal(rs.getDouble("PrecoTotal"));
				
					listaItemPedido.add(itempedido);
				}

				rs.close();
				stmt.close();
				connection.close();

				return listaItemPedido;
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			
			
		}
		
		public void inserir(ItemPedido itempedido) {

			String sql = "INSERT INTO produto (PrecoUnitario,Quantidade,PrecoTotal) VALUES (?,?,?)";
			PreparedStatement stmt;
			try {
				stmt = connection.prepareStatement(sql);

				stmt.setDouble(1, itempedido.getPrecoUnitario());
				stmt.setInt(2, itempedido.getQuantidade());
				stmt.setDouble(3, itempedido.getPrecoTotal());
				
				
				stmt.execute();
				connection.close();
				
			} catch (SQLException e) {
				throw new RuntimeException(e);

			}
			
			}
		
		public void LimparTabela(int id){
			try{
				PreparedStatement stmt = this.connection.prepareStatement("DELETE FROM pedido where id = ?");
				stmt.execute();
				connection.close();
			} catch (SQLException e){
				throw new RuntimeException(e);
			}
		}
		
		

		
	}


                  
		 	
		 

