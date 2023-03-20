package main;

public class Main {
	
	public static void main(String[] args) {
		Produto produto1 = new Produto ("Leite", "Parmalat", 8.24);
		Produto produto2 = new Produto ("Leite", "Italac", 5.39);
		System.out.println(produto1);
		System.out.println(produto2);
		System.out.println(produto1.equals(produto2));
	}
	
}