package br.com.clara.estoque.controllers;

import br.com.clara.estoque.model.Categoria;
import br.com.clara.estoque.repositories.CategoriaRepository;
import br.com.clara.estoque.services.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {


    @Autowired
    private CategoriaService categoriaService;

@Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping()
    public List<Categoria>listarTodasCategorias(){
        return categoriaRepository.findAll(Sort.by("nome").ascending());
    }

    @PostMapping()

    public ResponseEntity<Categoria> Inserir(@RequestBody Categoria categoria){
        Categoria categoriaSalva= categoriaService.salvar(categoria);
return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
    }
}
