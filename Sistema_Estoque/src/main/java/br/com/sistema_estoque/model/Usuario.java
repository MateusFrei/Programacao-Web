
package br.com.sistema_estoque.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Usuario extends Pessoa{
    @Column(nullable = false, length = 50, unique = true)
    private String login;
    @Column(nullable = false, length = 50, unique = true)
    private String senha;
    @Column(nullable = false, length = 14, unique = true, updatable = false)
    private String cpf; 
    
    private List<Fornecedor> fornecedores;
    private Administrador administrador;
    private List<Produto> produtos;
    
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
