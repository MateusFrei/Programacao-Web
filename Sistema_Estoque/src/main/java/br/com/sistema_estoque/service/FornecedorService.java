
package br.com.sistema_estoque.service;

import br.com.sistema_estoque.model.Fornecedor;
import br.com.sistema_estoque.repository.FornecedorRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FornecedorService {
    @Autowired
    private FornecedorRepository repo;
    
    public Fornecedor findById(Long id){
        Optional<Fornecedor> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Fornecedor não encontrado.");
        }
        return result.get();
    }
    
    public void delete(Long id){
        
        Fornecedor obj = findById(id);
        
        verificaexlusao(obj);
        repo.delete(obj);
        
    }

    public List<Fornecedor> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<Fornecedor> findAl(){
        return repo.findAll();
    }      
       
    
    public void verificaexlusao (Fornecedor f){
        if (!f.getProdutos().isEmpty()) {
            throw new RuntimeException("Fornecedor possue produtos cadastrados.");
        }
    }
    
    private Fornecedor save(Fornecedor f){
        
        return repo.save(f);
     
    }
    
     public Fornecedor update(Fornecedor f, String atualCnpj, String novoCnpj, String confirmaCnpj){
        Fornecedor obj = findById(f.getId());
        alterarCnpj(f, atualCnpj,  novoCnpj, confirmaCnpj);
         
        f.setEmail(obj.getEmail());
        f.setNome(obj.getNome());
        return repo.save(f);

    }
    
    private void alterarCnpj(Fornecedor f, String atualCnpj, String novoCnpj, String confirmaCnpj){
        if (!atualCnpj.isBlank() && !novoCnpj.isBlank() && !confirmaCnpj.isBlank()) {
            if (!atualCnpj.equals(f.getCnpj())){
               throw new RuntimeException("SENHA ATUAL INCORRETA");
            }
            
        }
        if (!novoCnpj.equals(confirmaCnpj)) {
            throw new RuntimeException("Nova sneha e confimação nao conferem");
        }
        
        f.setCnpj(novoCnpj);
    }
     
}

