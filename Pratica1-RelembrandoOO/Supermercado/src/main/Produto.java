package main;

import java.util.Objects;
import java.util.UUID;

public class Produto {

	private String uniqueID;
	private String nome;
	private String fabricante;
	private double preco;

	public Produto(String nome, String fabricante, double preco) {
		uniqueID = UUID.randomUUID().toString();
		this.nome = nome;
		this.fabricante = fabricante;
		this.preco = preco;
	}

	public String getUniqueID() {
		return uniqueID;
	}

	public void setUniqueID(String uniqueID) {
		this.uniqueID = uniqueID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	@Override
	public int hashCode() {
		return Objects.hash(uniqueID);
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Produto)) {
			return false;
		}
		
		Produto produto = (Produto) obj;
		
		return this.getUniqueID().equals(produto.getUniqueID());
	}
	
	@Override
	public String toString() {
		return "Produto [uniqueID=" + uniqueID + ", nome=" + nome + ", fabricante=" + fabricante + ", preco=" + preco
				+ "]";
	}

}