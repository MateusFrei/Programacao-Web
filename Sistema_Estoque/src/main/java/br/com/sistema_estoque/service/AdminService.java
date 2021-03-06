
package br.com.sistema_estoque.service;

import br.com.sistema_estoque.exception.NotFoundException;
import br.com.sistema_estoque.model.Administrador;
import br.com.sistema_estoque.model.Permissao;
import br.com.sistema_estoque.model.Usuario;
import br.com.sistema_estoque.repository.AdministradorRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdministradorRepository repo;
    
    public List<Administrador> findAll(){
        return repo.findAll();
    }
    
    public List<Usuario> findByCpf(int cpf){
        List<Usuario> result = repo.findByCpf(cpf);
        try {
            return result;
        } catch (Exception e) {
            Throwable t = e;
            while(t.getCause() !=null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException){
                    throw ((ConstraintViolationException)t);
                }
            }
            throw new RuntimeException("falha ao buscar usuario pelo cpf.");
        }
        
    }
    
    public Administrador save(Administrador a){
        
         removePermissoesNulas(a);       
        try {
            a.setSenha(new BCryptPasswordEncoder().encode(a.getSenha()));
            return repo.save(a);
        } catch (Exception e) {
            Throwable t = e;
            while(t.getCause() !=null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException){
                    throw ((ConstraintViolationException)t);
                }
            }            
            throw new RuntimeException("erro ao salvar");
        }
    }    
    
    public Administrador findById(Long id){
        Optional<Administrador> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Administrador n??o encontrado.");
        }
        return result.get();
    }
    
    public void delete(Long id){
        Administrador obj = (Administrador) findById(id);
   
        verificaexlusao(obj);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            Throwable t = e;
            while(t.getCause() !=null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException){
                    throw ((ConstraintViolationException)t);
                }
            }            
            throw new RuntimeException("erro ao deletar");
        } 
    }
    
    public void verificaexlusao(Administrador a){
        if (!a.getUsuarios().isEmpty()) {
            
            throw new RuntimeException("Ainda possue usuarios cadastrados");
        }
    }

     public Administrador update(Administrador a, String senhaAtual, String novaSenha, String confirmaSenha){
        Administrador obj = findById(a.getId());
        alterarSenha(a, senhaAtual, novaSenha, confirmaSenha);
         removePermissoesNulas(a);       
        try {
            a.setEmail(obj.getEmail());
            a.setNome(obj.getNome());
            a.setSenha(novaSenha);
            return repo.save(a);             
         } catch (Exception e) {
            Throwable t = e;
            while(t.getCause() !=null){
                t = t.getCause();
                if(t instanceof ConstraintViolationException){
                    throw ((ConstraintViolationException)t);
                }
            }            
            throw new RuntimeException("erro ao salvar");
        }
     
    }

    private void alterarSenha(Administrador a, String senhaAtual, String novaSenha, String confirmaSenha){
        if (!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmaSenha.isBlank()) {
            if (!senhaAtual.equals(a.getSenha())){
               throw new RuntimeException("SENHA ATUAL INCORRETA");
            }
            
        }
        if (!novaSenha.equals(confirmaSenha)) {
            throw new RuntimeException("Nova sneha e confima????o nao conferem");
        }
        
        a.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
    }
    
    public List<Administrador> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<Administrador> findAl(){
        return repo.findAll();
    }
     public void removePermissoesNulas(Administrador f){
        f.getPermissoes().removeIf( (Permissao p) -> {
            return p.getId()==null;
        });
        if(f.getPermissoes().isEmpty()){
            throw new RuntimeException("ADM deve conter no m??nimo 1 permiss??o.");
        }
    }   
 
}
