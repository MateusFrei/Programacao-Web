
package br.com.sistema_estoque.model;

import br.com.sistema_estoque.annotation.LoginValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

@Entity
public class Administrador extends Pessoa implements Serializable{
    @Column(nullable = false, length = 50, unique = true)
    @NotBlank
    @LoginValidation(message = "Login invalido.")
    private String login;
    
    @Column(nullable = false, length = 50, unique = false)
    @NotBlank(message = "senha deve ser preenchido.")
    @Length(max = 50, message = "senha pode ter no máximo 50 caracteres.")
    private String senha;
    
    @JsonIgnore
    @OneToMany(mappedBy = "administrador")
    @Size(min = 1, message = "deve conter 1 usuario.")
    @Valid
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