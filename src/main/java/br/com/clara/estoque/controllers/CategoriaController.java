package br.com.clara.estoque.controllers;

import br.com.clara.estoque.model.Categoria;
import br.com.clara.estoque.repositories.CategoriaRepository;
import br.com.clara.estoque.repositories.filter.CategoriaFilter;
import br.com.clara.estoque.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categorias")


public class CategoriaController {


    @Autowired
    private CategoriaService categoriaService;

@Autowired
    private CategoriaRepository categoriaRepository;

@GetMapping()
public Page<Categoria> pesquisar(CategoriaFilter categoriaFilter, Pageable pageable){
    return categoriaRepository.filtrar(categoriaFilter, pageable);
}

    @GetMapping("/todas")
    public List<Categoria>listarTodasCategorias(){
        return categoriaRepository.findAll(Sort.by("nome").ascending());
    }

@GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarPeloCodigo(@PathVariable int id){
     Optional<Categoria> categoria = categoriaRepository.findById(id);
     return categoria.isPresent() ? ResponseEntity.ok(categoria.get()): ResponseEntity.notFound().build();
    }
    @PostMapping()
    public ResponseEntity<Categoria> Inserir(@RequestBody Categoria categoria){
        Categoria categoriaSalva= categoriaService.salvar(categoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable int id){
        categoriaRepository.deleteById(id);
    }

@PutMapping("/id")
    public ResponseEntity<Categoria> atualizar(@PathVariable int id, @RequestBody Categoria categoria) {
    Categoria categoriaSalva = categoriaService.atualizar(id, categoria);
    return ResponseEntity.ok(categoriaSalva);
}

}
