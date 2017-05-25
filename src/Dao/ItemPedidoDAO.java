package Dao;

public class ItemPedidoDAO {
	      
		 	private double precoUnitario;
		 	private int quantidade;
		 	private double precoTotal;
		 	private PedidoDAO pedido;
		 
		 	public double getPrecoUnitario() {
		 		return precoUnitario;
		 	}
		 
		 	public void setPrecoUnitario(double precoUnitario) {
		 		this.precoUnitario = precoUnitario;
		 	}
		 
		 	public int getQuantidade() {
		 		return quantidade;
		 	}
		 
		 	public void setQuantidade(int quantidade) {
		 		this.quantidade = quantidade;
		 	}
		 
		 	public double getPrecoTotal() {
		 		return precoTotal;
		 	}
		 
		 	public void setPrecoTotal(double precoTotal) {
		 		this.precoTotal = precoTotal;
		 	}
		 
		 	public PedidoDAO getPedido() {
		 		return pedido;
		 	}
		 
		 	public void setPedido (PedidoDAO pedido) {
		 		this.pedido = pedido;
		 	}
		 
		 }

