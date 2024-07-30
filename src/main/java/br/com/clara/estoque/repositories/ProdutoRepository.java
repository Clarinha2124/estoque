package br.com.clara.estoque.repositories;

import br.com.clara.estoque.model.Categoria;
import br.com.clara.estoque.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ProdutoRepository extends JpaRepository <Produto, Integer> {
}
