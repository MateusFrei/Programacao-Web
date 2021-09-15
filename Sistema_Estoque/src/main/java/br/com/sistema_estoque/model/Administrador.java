
package br.com.sistema_estoque.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
public class Administrador extends Pessoa{
    @Column(nullable = false, length = 50, unique = true)
    private String login;
    
    @Column(nullable = false, length = 50, unique = true)
    private String senha;
    
    @JsonBackReference
    @OneToMany(mappedBy = "administrador")
    @ElementCollection(fetch = FetchType.EAGER)
    private List<Usuario> usuarios = new ArrayList<>();
    
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
    
    
}