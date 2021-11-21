
package br.com.sistema_estoque.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Fornecedor extends Pessoa{
    @Column(nullable = false, length = 50)
    @NotBlank(message = "empresa deve ser preenchido.")
    @Length(max = 50, message = "nome da epresa pode ter no máximo 50 caracteres.")
    private String empresa;
    
    @Column(nullable = false, length = 50, unique = true, updatable = true)
    @CNPJ(message = "CNPJ - invalido.")
    private String cnpj;
    
    @JsonIgnore
    @OneToMany (mappedBy = "fornecedor")
    @Size(min = 0, message = "deve conter 1 produto.")
    @Valid
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

    public void setId(Object object) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}

