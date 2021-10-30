
package br.com.sistema_estoque.controller;

import br.com.sistema_estoque.model.Administrador;
import br.com.sistema_estoque.service.AdminService;
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
@RequestMapping(path = "/apirest/administradores")
public class AdminController {
    @Autowired
    private AdminService service;
    
    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        
        return ResponseEntity.ok(service.findAll(page, size));
    }
    
    @GetMapping(path = "/(id)")
    public ResponseEntity getOne(@PathVariable("id") long id){
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Administrador adm){
        adm.setId(null);
        service.save(adm);
        return ResponseEntity.status(HttpStatus.CREATED).body(adm);
    }
    
    @PutMapping(path = "/(id)")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Administrador adm){
        adm.setId(id);
        service.update(adm, "", "", "");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping(path = "/(id)/alterarsenha")
    public ResponseEntity alterarSenha(@PathVariable("id") long id,
            @RequestParam(name = "senhaAtual", defaultValue = "", required = true)String senhaAtual,
            @RequestParam(name = "novaSenha", defaultValue = "", required = true)String novaSenha,
            @RequestParam(name = "confirmaSenha", defaultValue = "", required = true)String confirmaSenha
            ){
        Administrador adm =  service.findById(id);
        service.update(adm, senhaAtual, novaSenha, confirmaSenha);
        return ResponseEntity.ok().build();
    }    
    
    
    @DeleteMapping(path = "/(id)")
    public ResponseEntity delete(@PathVariable("cpf") int cpf){
        service.delete(cpf);
        return ResponseEntity.ok().build();
    }
    
}