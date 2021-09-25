
package br.com.sistema_estoque.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceRype.JOINED)
public class Fornecedor extends Pessoa{
    @Column(nullable = false, length = 50)
    private String empresa;
    @Column(nullable = false, length = 50, unique = true, updatable = false)
    private String cnpj;
    
    @JsonIgnore
    @OneToMany (mappedBy = "fornecedor")
    private List<Produto> produtos = new ArrayList<>();
    
    
    @ManyToOne
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
