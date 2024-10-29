package br.com.clara.estoque.repositories.produto;

import br.com.clara.estoque.dto.ProdutoDto;
import br.com.clara.estoque.model.Produto;
import br.com.clara.estoque.repositories.filter.ProdutoFilter;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class ProdutoRepositoryimpl implements ProdutoRepositoryQuery {


    @PersistenceContext
    private EntityManager manager;


    @Override
    public Page<ProdutoDto> filtrar(ProdutoFilter produtoFilter, Pageable pageable){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ProdutoDto> criteria = builder.createQuery(ProdutoDto.class);
        Root<ProdutoDto> root = criteria.from(ProdutoDto.class);


        criteria.select(builder.construct(ProdutoDto.class
                ,root.get("id")
                ,root.get("nomeproduto")
                ,root.get("preco")
                ,root.get("categoria"). get("nome")));

        Predicate [] predicates = criarRestricoes(produtoFilter, builder, root);
        criteria.where(predicates);
        criteria.orderBy(builder.asc(root.get("nomeproduto")));


        TypedQuery<ProdutoDto> query = manager.createQuery(criteria);
        adicionarRestricoesDePaginacao (query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(produtoFilter));

    }
    private  Long total (ProdutoFilter produtoFilter){
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria=builder.createQuery(Long.class);
        Root<Produto> root = criteria.from(Produto.class);


        Predicate[] predicates = criarRestricoes (produtoFilter, builder, root);
        criteria.where(predicates);

        

    }







}
