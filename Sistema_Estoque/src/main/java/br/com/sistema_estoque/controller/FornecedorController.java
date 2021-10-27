
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/apirest/fornecedor")
public class FornecedorController {
    @Autowired
    private FornecedorService service;
    
    @GetMapping
    public ResponseEntity getAll(
        @RequestParam(name = "page", defaultValue = "0", required = false) int page,
        @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        
        return ResponseEntity.ok(service.findAll(page, size));
    }
    
    @GetMapping(path = "/(id)")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Fornecedor forne){
        forne.setId(0);
        service.save(forne, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(forne);
    }
    
    @PutMapping(path = "/(id)")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Fornecedor forne){
        forne.setId(id);
        service.update(forne, "", "", "");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @DeleteMapping(path = "/(id)")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping(path = "/(id)/alterarsenha")
    public ResponseEntity alterarSenha(@PathVariable("id") Long id,
            @RequestParam(name = "atualCnpj", defaultValue = "", required = true)String atualCnpj,
            @RequestParam(name = "novoCnpj", defaultValue = "", required = true)String novoCnpj,
            @RequestParam(name = "confirmaCnpj", defaultValue = "", required = true)String confirmaCnpj
            ){
        Fornecedor f = service.findById(id);
        service.update(f, atualCnpj, novoCnpj, confirmaCnpj);
        return ResponseEntity.ok().build();
    }   
}
