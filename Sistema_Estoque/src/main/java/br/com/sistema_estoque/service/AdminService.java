
package br.com.sistema_estoque.service;

import br.com.sistema_estoque.model.Administrador;
import br.com.sistema_estoque.model.Usuario;
import br.com.sistema_estoque.repository.AdministradorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
        if (result.isEmpty()) {
            throw new RuntimeException("Clinete não encontrado");
        }
        return result;
    }
    
    private Administrador save(Administrador a){

        return repo.save(a);
     
        
    }    
    
    public Administrador findById(Long id){
        Optional<Administrador> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Fornecedor não encontrado.");
        }
        return result.get();
    }
    
    public void delete(int cpf){
        Administrador obj = (Administrador) findByCpf(cpf);
        
        verificaexlusao(obj);
        repo.delete(obj);
    }
    
    public void verificaexlusao(Administrador a){
        if (!a.getUsuarios().isEmpty()) {
            throw new RuntimeException("Ainda possue usuarios cadastrados");
        }
    }

     public Administrador update(Administrador a, String senhaAtual, String novaSenha, String confirmaSenha){
        Administrador obj = findById(a.getId());
        alterarSenha(a, senhaAtual, novaSenha, confirmaSenha);
         
        a.setEmail(obj.getEmail());
        a.setNome(obj.getNome());
        return repo.save(a);

    }

    private void alterarSenha(Administrador a, String senhaAtual, String novaSenha, String confirmaSenha){
        if (!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmaSenha.isBlank()) {
            if (!senhaAtual.equals(a.getSenha())){
               throw new RuntimeException("SENHA ATUAL INCORRETA");
            }
            
        }
        if (!novaSenha.equals(confirmaSenha)) {
            throw new RuntimeException("Nova sneha e confimação nao conferem");
        }
        
        a.setSenha(novaSenha);
    }
    
    public List<Administrador> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<Administrador> findAl(){
        return repo.findAll();
    }
}
