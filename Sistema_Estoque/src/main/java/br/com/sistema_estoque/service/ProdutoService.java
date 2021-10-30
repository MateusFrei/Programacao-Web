
package br.com.sistema_estoque.service;

import br.com.sistema_estoque.model.Produto;
import br.com.sistema_estoque.repository.ProdutoRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository repo;
    
    public Produto save(Produto p){
        try {
            return repo.save(p);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao Salvar produtos.");
            
        }
    }
    
    public Produto findById(long id){
        Optional<Produto> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Fornecedor não encontrado.");
        }
        return result.get();
    }
        
    
    
    public List<Produto> findAll(int page, int size){
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }
    
    public List<Produto> findAl(){
        return repo.findAll();
    }    

    public void update(Produto p, String prodAtual) {
        Produto obj = findById(p.getId());
        prodAtual = obj.getProduto();
        if (!prodAtual.isBlank()) {
            throw new RuntimeException("nao foi possivel.");
        }
        
    }

    public void delete(int id) {
        Produto obj = findById(id);
        String prodAtual = obj.getProduto();
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao deletear");
        }
    } 
    
}
