package br.com.clara.estoque.repositories.produto;

import br.com.clara.estoque.dto.ProdutoDto;
import br.com.clara.estoque.repositories.filter.ProdutoFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdutoRepositoryQuery {
    public Page<ProdutoDto> filtrar(ProdutoFilter produtoFilter, Pageable pageable);
}
