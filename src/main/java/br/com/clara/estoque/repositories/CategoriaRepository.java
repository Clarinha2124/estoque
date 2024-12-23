package br.com.clara.estoque.repositories;

import br.com.clara.estoque.model.Categoria;
import br.com.clara.estoque.repositories.categoria.CategoriaRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository <Categoria, Integer>, CategoriaRepositoryQuery {

}
