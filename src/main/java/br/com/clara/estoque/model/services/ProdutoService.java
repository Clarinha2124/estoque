package br.com.clara.estoque.model.services;

import br.com.clara.estoque.model.Categoria;
import br.com.clara.estoque.model.Produto;
import br.com.clara.estoque.model.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto salvar(Produto produto){
        return produtoRepository.save(produto);
    }


}
