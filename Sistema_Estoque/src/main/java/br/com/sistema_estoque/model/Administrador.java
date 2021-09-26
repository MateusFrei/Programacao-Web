
package br.com.sistema_estoque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Administrador extends Pessoa implements Serializable{
    @Column(nullable = false, length = 50, unique = true)
    private String login;
    
    @Column(nullable = false, length = 50, unique = false)
    private String senha;
    
    @JsonIgnore
    @OneToMany(mappedBy = "administrador")
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