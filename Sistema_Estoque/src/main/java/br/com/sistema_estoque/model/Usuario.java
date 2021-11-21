
package br.com.sistema_estoque.model;

import br.com.sistema_estoque.annotation.LoginValidation;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@JsonIgnoreProperties(value = "senha", allowGetters = false, allowSetters = true)
public class Usuario extends Pessoa{
    @Column(nullable = false, length = 50, unique = true)
    @NotBlank(message = "Login precisa ser preenchido.")
    @LoginValidation(message = "Login invalido.")
    private String login;
    
    @Column(nullable = false, length = 50)
    @NotBlank(message = "senha deve ser preenchido.")
    @Length(max = 50, message = "senha pode ter no máximo 50 caracteres.")
    private String senha;
    
    @Column(nullable = false, length = 14, unique = true, updatable = true)
    @CPF(message = "CPF - invalido.")
    @NotBlank(message = "CPF- deve ser preenchido.")
    @Length(max = 15, message = "nome da epresa pode ter no máximo 15 caracteres.")
    private String cpf; 
    
    @JsonIgnore
    @OneToMany (mappedBy = "usuario")
    private List<Fornecedor> fornecedores = new ArrayList<>();
    
    
    @ManyToOne
    private Administrador administrador;
    
    @JsonIgnore
    @OneToMany (mappedBy = "usuario")
    private List<Produto> produtos = new ArrayList<>();
    
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

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<Fornecedor> getFornecedores() {
        return fornecedores;
    }

    public void setFornecedores(List<Fornecedor> fornecedores) {
        this.fornecedores = fornecedores;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

}
