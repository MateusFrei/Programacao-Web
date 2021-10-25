
package br.com.sistema_estoque.controller;

import br.com.sistema_estoque.model.Produto;
import br.com.sistema_estoque.service.ProdutoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/apirest/produto")
public class ProdutoController {
    @Autowired
    private ProdutoService service;
    
    @GetMapping
    public ResponseEntity getall(
            @RequestParam(name = "page", defaultValue = "0", required = false)int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size){
            return ResponseEntity.status(HttpStatus.OK).body(service.findAll(page, size));
    }
    
    @GetMapping(path = "/(id)")
    public ResponseEntity getOne(@PathVariable("id") int id){
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Produto produto){
        produto.setId(0);
        service.save(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produto);
    }
    
    @PutMapping("/(id)")
    public ResponseEntity update(@PathVariable("id") int id, @Valid @RequestBody Produto produto){
     produto.setId(id);
     service.update(produto);
     return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/(id)")
    public ResponseEntity delete(@PathVariable("id") int id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
}
