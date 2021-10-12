
package br.com.sistema_estoque.repository;

import br.com.sistema_estoque.model.Fornecedor;
import br.com.sistema_estoque.model.Produto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long>{
    
    @Query("SELECT p FROM Produto p WHERE p.id = :id")
    public List<Produto> findByid(@Param("id") Long id);
    
}
