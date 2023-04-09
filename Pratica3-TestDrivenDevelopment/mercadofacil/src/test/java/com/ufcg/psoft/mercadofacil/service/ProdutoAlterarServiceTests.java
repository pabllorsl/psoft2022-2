package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayName("Testes do Servico de alteracao do produto")
public class ProdutoAlterarServiceTests {

    @Autowired
    ProdutoAlterarService driver;

    @MockBean
    ProdutoRepository<Produto, Long> produtoRepository;

    Produto produto;

    @BeforeEach
    void setup() {
        Mockito.when(produtoRepository.find(10L))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7898357417892")
                        .nome("Produto Dez")
                        .fabricante("Empresa Dez")
                        .preco(450.00)
                        .build()
                );
        produto = produtoRepository.find(10L);
    }

    @Test
    @DisplayName("Quando um novo nome for fornecido para o produto")
    void quandoNovoNome() {
        quandoNovoNomeValido();
        quandoNovoNomeNulo();
        quandoNovoNomeVazio();
        quandoNovoNomeEspacoEmBranco();
    }

    @DisplayName("Quando um novo nome valido for fornecido para o produto")
    private void quandoNovoNomeValido() {
        // Arrange
        produto.setNome("Produto Dez Atualizado");
        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7898357417892")
                        .nome("Produto Dez Atualizado")
                        .fabricante("Empresa Dez")
                        .preco(450.00)
                        .build()
                );

        // Act
        Produto resultado = driver.alterar(produto);

        // Assert
        assertEquals("Produto Dez Atualizado", resultado.getNome());
    }

    @DisplayName("Quando um novo nome nulo for fornecido para o produto")
    private void quandoNovoNomeNulo() {
        //Arrange
        produto.setNome(null);

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Nome invalido", thrown.getMessage());
    }

    @DisplayName("Quando um novo nome vazio for fornecido para o produto")
    private void quandoNovoNomeVazio() {
        //Arrange
        produto.setNome("");

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Nome invalido", thrown.getMessage());
    }

    @DisplayName("Quando um novo nome com espaco em branco for fornecido para o produto")
    private void quandoNovoNomeEspacoEmBranco() {
        //Arrange
        produto.setNome("       ");

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Nome invalido", thrown.getMessage());
    }


    @Test
    @DisplayName("Quando um novo fabricante for fornecido para o produto")
    void quandoNovoFabricante() {
        quandoNovoFabricanteValido();
        quandoNovoFabricanteNulo();
        quandoNovoFabricanteVazio();
        quandoNovoFabricanteEspacoEmBranco();
    }

    @DisplayName("Quando um novo fabricante valido for fornecido para o produto")
    private void quandoNovoFabricanteValido() {
        // Arrange
        produto.setFabricante("Empresa Dez Atualizado");
        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7898357417892")
                        .nome("Produto Dez")
                        .fabricante("Empresa Dez Atualizado")
                        .preco(450.00)
                        .build()
                );

        // Act
        Produto resultado = driver.alterar(produto);

        // Assert
        assertEquals("Empresa Dez Atualizado", resultado.getFabricante());
    }

    @DisplayName("Quando um novo fabricante nulo for fornecido para o produto")
    private void quandoNovoFabricanteNulo() {
        //Arrange
        produto.setFabricante(null);

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Fabricante invalido", thrown.getMessage());
    }

    @DisplayName("Quando um novo fabricante vazio for fornecido para o produto")
    private void quandoNovoFabricanteVazio() {
        //Arrange
        produto.setFabricante("");

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Fabricante invalido", thrown.getMessage());
    }

    @DisplayName("Quando um novo fabricante com espaço em branco for fornecido para o produto")
    private void quandoNovoFabricanteEspacoEmBranco() {
        //Arrange
        produto.setFabricante("       ");

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Fabricante invalido", thrown.getMessage());
    }


    @Test
    @DisplayName("Quando um novo preco for fornecido para o produto")
    void quandoNovoPreco() {
        quandoNovoPrecoValido();
        quandoNovoPrecoNegativo();
        quandoNovoPrecoIgualAZero();
    }

    @DisplayName("Quando um novo preco valido for fornecido para o produto")
    private void quandoNovoPrecoValido() {
        // Arrange
        produto.setPreco(1000.0);
        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7898357417892")
                        .nome("Produto Dez")
                        .fabricante("Empresa Dez")
                        .preco(1000.0)
                        .build()
                );

        // Act
        Produto resultado = driver.alterar(produto);

        // Assert
        assertEquals(1000.0, resultado.getPreco());
    }

    @DisplayName("Quando um novo preco negativo for fornecido para o produto")
    private void quandoNovoPrecoNegativo() {
        //Arrange
        produto.setPreco(-450.0);

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Preco invalido", thrown.getMessage());
    }

    @DisplayName("Quando um novo preco igual a zero for fornecido para o produto")
    private void quandoNovoPrecoIgualAZero() {
        //Arrange
        produto.setPreco(0.0);

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Preco invalido", thrown.getMessage());
    }

    @Test
    @DisplayName("Quando um novo codigo de barra for fornecido para o produto")
    void quandoNovoCodigoBarra() {
        quandoNovoCodigoBarraValido();
        quandoNovoCodigoBarraTamanhoMaior13Digitos();
        quandoNovoCodigoBarraTamanhoMenor13Digitos();
        quandoNovoCodigoBarraNulo();
        quandoNovoCodigoBarraVazio();
        quandoNovoCodigoBarraEspacoEmBranco();
        quandoNovoCodigoBarraDigitoVerificadorInvalido();
    }

    @DisplayName("Quando um novo codigo de barra valido for fornecido para o produto")
    private void quandoNovoCodigoBarraValido() {
        // Arrange
        produto.setCodigoBarra("7895489713236");
        Mockito.when(produtoRepository.update(produto))
                .thenReturn(Produto.builder()
                        .id(10L)
                        .codigoBarra("7895489713236")
                        .nome("Produto Dez")
                        .fabricante("Empresa Dez")
                        .preco(450.00)
                        .build()
                );

        // Act
        Produto resultado = driver.alterar(produto);

        // Assert
        assertEquals("7895489713236", resultado.getCodigoBarra());
    }

    @DisplayName("Quando um novo codigo de barra com tamanho maior que 13 digitos for fornecido para o produto")
    private void quandoNovoCodigoBarraTamanhoMaior13Digitos() {
        //Arrange
        produto.setCodigoBarra("78954897132369");

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Codigo de barra invalido", thrown.getMessage());
    }

    @DisplayName("Quando um novo codigo de barra com tamanho menor que 13 digitos for fornecido para o produto")
    private void quandoNovoCodigoBarraTamanhoMenor13Digitos() {
        //Arrange
        produto.setCodigoBarra("789548971323");

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Codigo de barra invalido", thrown.getMessage());
    }

    @DisplayName("Quando um novo codigo de barra nulo for fornecido para o produto")
    private void quandoNovoCodigoBarraNulo() {
        //Arrange
        produto.setCodigoBarra(null);

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Codigo de barra invalido", thrown.getMessage());
    }

    @DisplayName("Quando um novo codigo de barra vazio for fornecido para o produto")
    private void quandoNovoCodigoBarraVazio() {
        //Arrange
        produto.setCodigoBarra("");

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Codigo de barra invalido", thrown.getMessage());
    }

    @DisplayName("Quando um novo codigo de barra com espaco em branco for fornecido para o produto")
    private void quandoNovoCodigoBarraEspacoEmBranco() {
        //Arrange
        produto.setCodigoBarra("       ");

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Codigo de barra invalido", thrown.getMessage());
    }

    @DisplayName("Quando um novo codigo de barra com digito verificador invalido for fornecido para o produto")
    private void quandoNovoCodigoBarraDigitoVerificadorInvalido() {
        //Arrange
        produto.setCodigoBarra("7895489713239");

        //Act
        RuntimeException thrown = assertThrows(
                RuntimeException.class,
                () -> driver.alterar(produto)
        );

        //Assert
        assertEquals("Codigo de barra invalido", thrown.getMessage());
    }


}
