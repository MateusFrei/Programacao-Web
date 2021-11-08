
package br.com.sistema_estoque.controller;

import br.com.sistema_estoque.model.Fornecedor;
import br.com.sistema_estoque.service.FornecedorService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/apirest/fornecedores")
public class FornecedorController {
    @Autowired
    private FornecedorService service;
    
    @GetMapping
    public ResponseEntity getAll(
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        
        return ResponseEntity.ok(service.findAll(page, size));
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") long id){
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Fornecedor forne){
        forne.setId(null);
        service.save(forne);
        return ResponseEntity.status(HttpStatus.CREATED).body(forne);
    }
    
   
    
    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
 
}
