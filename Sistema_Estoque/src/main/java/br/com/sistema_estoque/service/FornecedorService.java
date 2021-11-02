
package br.com.sistema_estoque.service;

import br.com.sistema_estoque.exception.NotFoundException;
import br.com.sistema_estoque.model.Fornecedor;
import br.com.sistema_estoque.repository.FornecedorRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
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
            throw new NotFoundException("Fornecedor não encontrado.");
        }
        return result.get();
    }
    
    public void delete(Long id){
        
        Fornecedor obj = findById(id);
        
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
            throw new RuntimeException("erro ao salvar");
        }
     
        
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
    
    


    public void save(Fornecedor forne) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

