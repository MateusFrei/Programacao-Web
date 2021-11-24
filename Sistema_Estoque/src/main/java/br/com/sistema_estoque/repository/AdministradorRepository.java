
package br.com.sistema_estoque.repository;

import br.com.sistema_estoque.model.Administrador;
import br.com.sistema_estoque.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AdministradorRepository extends JpaRepository<Administrador, Long>{
    @Query("SELECT p FROM Usuario p WHERE p.cpf = :cpf")
    public List<Usuario> findByCpf(@Param("cpf") int cpf);

    public Administrador findByLogin(String login);

  
}
