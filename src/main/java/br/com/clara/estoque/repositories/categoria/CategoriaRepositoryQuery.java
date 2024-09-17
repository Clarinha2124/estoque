package br.com.clara.estoque.repositories.categoria;

import br.com.clara.estoque.model.Categoria;
import br.com.clara.estoque.repositories.filter.CategoriaFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoriaRepositoryQuery {
    public Page <Categoria> filtrar (CategoriaFilter categoriaFilter,Pageable pageable);
}
