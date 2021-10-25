
package br.com.sistema_estoque.service;

import br.com.sistema_estoque.model.Fornecedor;
import br.com.sistema_estoque.model.Produto;
import br.com.sistema_estoque.model.Usuario;
import br.com.sistema_estoque.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository repo;
    
    public List<Fornecedor> findByCnpj(String cnpj){
        List<Fornecedor> result = repo.findByCnpj(cnpj);
        if (result.isEmpty()) {
            throw new RuntimeException("Fornecedor não encontrado");
        }
        return result;
    }
    
    public List<Produto> findByid(Long id){
        List<Produto> result = repo.findByid(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Produto não encontrado");
        }
        return result;        
    }
    
    public void delete(Long id){
        Usuario obj = (Usuario) findByid(id);
        
        verificaexlusao(obj);
        repo.delete(obj);
    }
    
    public void verificaexlusao(Usuario u){
        if (!u.getProdutos().isEmpty()) {
            throw new RuntimeException("O usuario possue produtos ainda");
        }
    }
    
    private Usuario save(Usuario u){
        
        try {
           return repo.save(u);
        } catch (Exception e) {
            throw new RuntimeException("erro ao salvar fonecedor ");
        }
        
    }

    public List<Usuario> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<Usuario> findAl(){
        return repo.findAll();
    }      
    
    public Usuario update(Usuario u, String senhaAtual, String novaSenha, String confirmaSenha){
        Usuario obj = (Usuario) findByid(u.getId());
         alterarSenha(u, senhaAtual, novaSenha, confirmaSenha);
         try {
             u.setEmail(obj.getEmail());
             u.setNome(obj.getNome());
             u.setSenha(novaSenha);
             return repo.save(u);
         } catch (Exception e) {
             throw new RuntimeException("Falha na atualizaçao");
         }
    }

    private void alterarSenha(Usuario u, String senhaAtual, String novaSenha, String confirmaSenha){
        if (!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmaSenha.isBlank()) {
            if (!senhaAtual.equals(u.getSenha())){
               throw new RuntimeException("SENHA ATUAL INCORRETA");
            }
            
        }
        if (!novaSenha.equals(confirmaSenha)) {
            throw new RuntimeException("Nova sneha e confimação nao conferem");
        }
        
        u.setSenha(novaSenha);
    }   

    public Usuario save(Usuario u, Object object) {
                
        try {
           return repo.save(u);
        } catch (Exception e) {
            throw new RuntimeException("erro ao salvar fonecedor ");
        }
        
    }
}
