
package br.com.sistema_estoque.controller;

import br.com.sistema_estoque.model.Usuario;
import br.com.sistema_estoque.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/apirest/user")
public class UserController {
    @Autowired
    private UserService service;
    
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size){
        
        return ResponseEntity.ok(service.findAll(page, size));
    }
    
    @RequestMapping(path = "/(id)")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok(service.findByid(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Usuario user){
        user.setId(0);
        service.save(user, null);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }
    
    @PutMapping(path = "/(id)")
    public ResponseEntity update(@PathVariable("id") Long id, @Valid @RequestBody Usuario user){
        user.setId(id);
        service.update(user, "", "", "");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @PutMapping(path = "/(id)/alterarsenha")
    public ResponseEntity alterarSenha(@PathVariable("id") Long id,
            @RequestParam(name = "senhaAtual", defaultValue = "", required = true)String senhaAtual,
            @RequestParam(name = "novaSenha", defaultValue = "", required = true)String novaSenha,
            @RequestParam(name = "confirmaSenha", defaultValue = "", required = true)String confirmaSenha
            ){
        Usuario u = (Usuario) service.findByid(id);
        service.update(u, senhaAtual, novaSenha, confirmaSenha);
        return ResponseEntity.ok().build();
    }    
    
    
    @DeleteMapping(path = "/(id)")
    public ResponseEntity delete(@PathVariable("id") Long id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    
}
