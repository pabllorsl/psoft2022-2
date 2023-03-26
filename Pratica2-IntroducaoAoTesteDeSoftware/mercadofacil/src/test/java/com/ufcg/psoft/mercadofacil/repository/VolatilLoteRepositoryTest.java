package com.ufcg.psoft.mercadofacil.repository;

import com.ufcg.psoft.mercadofacil.model.Lote;
import com.ufcg.psoft.mercadofacil.model.Produto;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class VolatilLoteRepositoryTest {

	@Autowired
	VolatilLoteRepository driver;

	Lote lote;
	Lote resultado;
	Produto produto;

	@BeforeEach
	void setup() {
		produto = Produto.builder()
				.id(1L)
				.nome("Produto Base")
				.codigoBarra("123456789")
				.fabricante("Fabricante Base")
				.preco(125.36)
				.build();
		lote = Lote.builder()
				.id(1L)
				.numeroDeItens(100)
				.produto(produto)
				.build();
	}

	@AfterEach
	void tearDown() {
		produto = null;
		driver.deleteAll();
	}

	@Test
	@DisplayName("Adicionar o primeiro Lote no repositorio de dados")
	void salvarPrimeiroLote() {
		resultado = driver.save(lote);

		assertEquals(driver.findAll().size(), 1);
		assertEquals(resultado.getId().longValue(), lote.getId().longValue());
		assertEquals(resultado.getProduto(), produto);
	}

	@Test
	@DisplayName("Adicionar o segundo Lote (ou posterior) no repositorio de dados")
	void salvarSegundoLoteOuPosterior() {
		Produto produtoExtra = Produto.builder()
				.id(2L)
				.nome("Produto Extra")
				.codigoBarra("987654321")
				.fabricante("Fabricante Extra")
				.preco(125.36)
				.build();
		Lote loteExtra = Lote.builder()
				.id(2L)
				.numeroDeItens(100)
				.produto(produtoExtra)
				.build();

		driver.save(lote);
		resultado = driver.save(loteExtra);

		assertEquals(driver.findAll().size(), 2);
		assertEquals(resultado.getId().longValue(), loteExtra.getId().longValue());
		assertEquals(resultado.getProduto(), produtoExtra);
	}

	@Test
	@DisplayName("Encontrar lote por id no repositorio de dados")
	void encontrarLotePorId() {
		encontrarLotePorIdRDNenhumLote();
		encontrarLotePorIdRDUmLote();
		encontrarLotePorIdRDVariosLotes();
	}

	@Test
	@DisplayName("Encontrar lote por id no repositorio de dados com nenhum lote")
	private void encontrarLotePorIdRDNenhumLote() {
		assertEquals(resultado, driver.find(lote.getId().longValue()));
	}

	@Test
	@DisplayName("Encontrar lote por id no repositorio de dados com um lote")
	private void encontrarLotePorIdRDUmLote() {
		resultado = driver.save(lote);

		assertEquals(lote, driver.find(resultado.getId().longValue()));
	}

	@Test
	@DisplayName("Encontrar lote por id no repositorio de dados com varios lotes")
	private void encontrarLotePorIdRDVariosLotes() {
		Produto produtoExtra = Produto.builder()
				.id(2L)
				.nome("Produto Extra")
				.codigoBarra("987654321")
				.fabricante("Fabricante Extra")
				.preco(125.36)
				.build();
		Lote loteExtra = Lote.builder()
				.id(2L)
				.numeroDeItens(100)
				.produto(produtoExtra)
				.build();

		driver.save(lote);
		resultado = driver.save(loteExtra);

		assertEquals(loteExtra, driver.find(resultado.getId().longValue()));
	}

	@Test
	@DisplayName("Encontrar todos os lotes no repositorio de dados")
	void encontrarTodosOsLotes() {
		encontrarTodosOsLotesRDNenhumLote();
		encontrarTodosOsLotesRDUmLote();
		encontrarTodosOsLotesRDVariosLotes();
	}

	@Test
	@DisplayName("Encontrar todos os lotes no repositorio de dados com nenhum lote")
	private void encontrarTodosOsLotesRDNenhumLote() {
		List<Lote> lotes = new ArrayList<>();

		lotes = driver.findAll();

		assertTrue(lotes.isEmpty());
	}

	@Test
	@DisplayName("Encontrar todos os lotes no repositorio de dados com um lote")
	private void encontrarTodosOsLotesRDUmLote() {
		List<Lote> lotes = new ArrayList<>();

		driver.save(lote);
		lotes = driver.findAll();

		assertEquals(1, lotes.size());
	}

	@Test
	@DisplayName("Encontrar todos os lotes no repositorio de dados com varios lotes")
	private void encontrarTodosOsLotesRDVariosLotes() {
		List<Lote> lotes = new ArrayList<>();

		Produto produtoExtra = Produto.builder()
				.id(2L)
				.nome("Produto Extra")
				.codigoBarra("987654321")
				.fabricante("Fabricante Extra")
				.preco(125.36)
				.build();
		Lote loteExtra = Lote.builder()
				.id(2L)
				.numeroDeItens(100)
				.produto(produtoExtra)
				.build();

		driver.save(lote);
		driver.save(loteExtra);

		lotes = driver.findAll();

		assertEquals(3, lotes.size()); // 3 lotes porque persiste a adicao de um
									   // lote no metodo encontrarTodosOsLotesRDUmLote()
									   // que foi executado anteriormente a este
	}

	@Test
	@DisplayName("Atualizar lote no repositorio de dados")
	void atualizarLote() {
		atualizarLoteRDNenhumlote();
		atualizarLoteRDUmlote();
		atualizarLoteRDVarioslotes();
	}

	@Test
	@DisplayName("Atualizar lote no repositorio de dados com nenhum lote")
	private void atualizarLoteRDNenhumlote() {
		lote.setNumeroDeItens(0);
		driver.update(lote);

		assertEquals(0, lote.getNumeroDeItens());
		assertEquals(0, driver.findAll().size());
	}

	@Test
	@DisplayName("Atualizar lote no repositorio de dados com um lote")
	private void atualizarLoteRDUmlote() {
		resultado = driver.save(lote);
		resultado.setNumeroDeItens(0);
		driver.update(resultado);

		assertEquals(0, resultado.getNumeroDeItens());
		assertEquals(1, driver.findAll().size());
		assertEquals(0, driver.find(resultado.getId().longValue()).getNumeroDeItens());
	}

	@Test
	@DisplayName("Atualizar lote no repositorio de dados com varios lotes")
	private void atualizarLoteRDVarioslotes() {
		Produto produtoExtra = Produto.builder()
				.id(2L)
				.nome("Produto Extra")
				.codigoBarra("987654321")
				.fabricante("Fabricante Extra")
				.preco(125.36)
				.build();
		Lote loteExtra = Lote.builder()
				.id(2L)
				.numeroDeItens(100)
				.produto(produtoExtra)
				.build();

		driver.save(lote);
		resultado = driver.save(loteExtra);
		resultado.setNumeroDeItens(0);
		driver.update(resultado);

		assertEquals(0, resultado.getNumeroDeItens());
		assertEquals(2, driver.findAll().size());
		assertEquals(0, driver.find(resultado.getId().longValue()).getNumeroDeItens());
	}

	@Test
	@DisplayName("Remover lote do repositorio de dados")
	void removerLote() {
		removerLoteRDNenhumLote();
		removerLoteRDUmLote();
		removerLoteRDVariosLotes();
	}

	@Test
	@DisplayName("Remover lote do repositorio de dados com nenhum lote")
	private void removerLoteRDNenhumLote() {
		driver.delete(lote);

		assertEquals(0, driver.findAll().size());
	}

	@Test
	@DisplayName("Remover lote do repositorio de dados com um lote")
	private void removerLoteRDUmLote() {
		resultado = driver.save(lote);
		driver.delete(resultado);

		assertEquals(0, driver.findAll().size());
	}

	@Test
	@DisplayName("Remover lote do repositorio de dados com varios lotes")
	private void removerLoteRDVariosLotes() {
		Produto produtoExtra = Produto.builder()
				.id(2L)
				.nome("Produto Extra")
				.codigoBarra("987654321")
				.fabricante("Fabricante Extra")
				.preco(125.36)
				.build();
		Lote loteExtra = Lote.builder()
				.id(2L)
				.numeroDeItens(100)
				.produto(produtoExtra)
				.build();

		driver.save(lote);
		resultado = driver.save(loteExtra);
		driver.delete(resultado);

		assertEquals(1, driver.findAll().size());
	}

	@Test
	@DisplayName("Remover todos os lotes do repositorio de dados")
	void removerTodosOsLotes() {
		removerTodosOsLotesRDNenhumLote();
		removerTodosOsLotesRDUmLote();
		removerTodosOsLotesRDVariosLotes();
	}

	@Test
	@DisplayName("Remover todos os lotes do repositorio de dados com nenhum lote")
	private void removerTodosOsLotesRDNenhumLote() {
		driver.deleteAll();

		assertEquals(0, driver.findAll().size());
	}

	@Test
	@DisplayName("Remover todos os lotes do repositorio de dados com um lote")
	private void removerTodosOsLotesRDUmLote() {
		driver.save(lote);
		driver.deleteAll();

		assertEquals(0, driver.findAll().size());
	}

	@Test
	@DisplayName("Remover todos os lotes do repositorio de dados com varios lotes")
	private void removerTodosOsLotesRDVariosLotes() {
		Produto produtoExtra = Produto.builder()
				.id(2L)
				.nome("Produto Extra")
				.codigoBarra("987654321")
				.fabricante("Fabricante Extra")
				.preco(125.36)
				.build();
		Lote loteExtra = Lote.builder()
				.id(2L)
				.numeroDeItens(100)
				.produto(produtoExtra)
				.build();

		driver.save(lote);
		driver.save(loteExtra);
		driver.deleteAll();

		assertEquals(0, driver.findAll().size());
	}

}
