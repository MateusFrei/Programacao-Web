
package br.com.sistema_estoque.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Fornecedor extends Pessoa{
    @Column(nullable = false, length = 50)
    private String empresa;
    @Column(nullable = false, length = 50, unique = true, updatable = false)
    private String cnpj;
    
    private List<Produto> produtos;
    private Usuario usuario;
    
    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }



    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

}
