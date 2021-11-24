
package br.com.sistema_estoque.security;

import br.com.sistema_estoque.model.Administrador;
import br.com.sistema_estoque.model.Permissao;
import br.com.sistema_estoque.repository.AdministradorRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AdministradorDetailsService implements UserDetailsService{

    @Autowired
    private AdministradorRepository repo;
    
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Administrador funcionario = repo.findByLogin(login);
        if(funcionario==null){
            throw new UsernameNotFoundException("Funcionário não encontrado com esse email: "+login);
        }
        return new User(funcionario.getLogin(), funcionario.getSenha(), getAuthorities(funcionario.getPermissoes()));
    }
    
    private List<GrantedAuthority> getAuthorities(List<Permissao> lista){
        List<GrantedAuthority> l = new ArrayList<>();
        for(Permissao p : lista){
            l.add(new SimpleGrantedAuthority("ROLE_"+p.getNome()));
        }
        return l;
    }
    
}