package br.com.clara.estoque.repositories.categoria;

import br.com.clara.estoque.model.Categoria;
import br.com.clara.estoque.repositories.filter.CategoriaFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;
import java.util.List;

public class CategoriaRepositoryimpl  implements CategoriaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Categoria> filtrar(CategoriaFilter categoriaFilter, Pageable pageable) {

        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Categoria> criteria = builder.createQuery(Categoria.class);
        Root<Categoria> root = criteria.from(Categoria.class);
        Predicate[] predicates = criarRestricoes(categoriaFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nome")));


        TypedQuery<Categoria> query=manager.createQuery(criteria);
        adicionarRestricoesPaginaacao(query,pageable);
        return null;
    }

    private void adicionarRestricoesPaginaacao(TypedQuery<Categoria> query, Pageable pageable) {
    }

    private Predicate[] criarRestricoes(CategoriaFilter categoriaFilter, CriteriaBuilder builder, Root<Categoria> root) {
        List<Predicate> predicate = new ArrayList<>();

        if (!StringUtils.isEmpty(categoriaFilter.getNome())) {
            predicate.add(builder.like(builder.lower(root.get("nome")),
                    "%" + categoriaFilter.getNome().toLowerCase() + "%"));
        }
        return predicate.toArray(new Predicate[predicate.size()]);
    }
}