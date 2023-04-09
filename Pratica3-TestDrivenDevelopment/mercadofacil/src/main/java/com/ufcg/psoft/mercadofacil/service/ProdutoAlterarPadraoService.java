package com.ufcg.psoft.mercadofacil.service;

import com.ufcg.psoft.mercadofacil.model.Produto;
import com.ufcg.psoft.mercadofacil.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Service
public class ProdutoAlterarPadraoService implements ProdutoAlterarService {
    @Autowired
    ProdutoRepository<Produto, Long> produtoRepository;

    @Override
    public Produto alterar(Produto produto) {
        eanValido(produto);
        nomeValido(produto);
        fabricanteValido(produto);
        precoValido(produto);

        return produtoRepository.update(produto);
    }

    private void eanValido(Produto produto) {
        String ean = produto.getCodigoBarra();
        boolean eanValido;

        if (isNull(produto.getCodigoBarra()) || produto.getCodigoBarra().isBlank() || ean.length() != 13) {
            eanValido = false;
        } else {
            String[] arrayStringEan = ean.split("");
            int[] arrayNumEan = new int[arrayStringEan.length];
            for (int i = 0; i < arrayStringEan.length; i++) {
                arrayNumEan[i] = Integer.parseInt(arrayStringEan[i]);
            }

            int somaEan = 0;
            for (int i = arrayNumEan.length - 2; i > 0; i = i - 2) {
                somaEan += arrayNumEan[i] * 3;
                somaEan += arrayNumEan[i - 1];
            }

            eanValido = ((somaEan + arrayNumEan[arrayNumEan.length - 1]) % 10) == 0;
        }

        if (!eanValido) {
            throw new RuntimeException("Codigo de barra invalido");
        }
    }

    private void nomeValido(Produto produto) {
        if (isNull(produto.getNome()) || produto.getNome().isBlank()) {
            throw new RuntimeException("Nome invalido");
        }
    }

    private void fabricanteValido(Produto produto) {
        if (isNull(produto.getFabricante()) || produto.getFabricante().isBlank()) {
            throw new RuntimeException("Fabricante invalido");
        }
    }

    private void precoValido(Produto produto) {
        if (produto.getPreco() <= 0) {
            throw new RuntimeException("Preco invalido");
        }
    }
}
