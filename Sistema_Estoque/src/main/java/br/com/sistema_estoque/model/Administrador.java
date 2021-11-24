
package br.com.sistema_estoque.model;

import br.com.sistema_estoque.annotation.LoginValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;

@Entity
@JsonIgnoreProperties(value = "senha", allowGetters = false, allowSetters = true)
public class Administrador extends Pessoa implements Serializable{
    @Column(nullable = false, length = 50, unique = true)
    @NotBlank(message = "Login deve ser preenchido")
    @LoginValidation(message = "Login invalido.")
    private String login;
    
    @Column(nullable = false, length = 50)
    @NotBlank(message = "senha deve ser preenchido.")
    @Length(max = 50, message = "senha pode ter no máximo 50 caracteres.")
    private String senha;
    
    
    @OneToMany(mappedBy = "administrador")
    @Size(min = 1, message = "deve conter 1 usuario.")
    @JsonIgnore
    @Valid
    private List<Usuario> usuarios = new ArrayList<>();
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 1, message = "precisa ter uma permissao ")
    private List<Permissao> permissoes = new ArrayList<>();   
    
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
 
    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }
    
}