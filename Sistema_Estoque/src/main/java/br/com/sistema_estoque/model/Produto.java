
package br.com.sistema_estoque.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

@Entity
public class Produto implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column(nullable = false, length = 50, unique = true)
    @NotNull(message = "produto deve ser preenchido.")
    @Length(max = 50, message = "nome do produto pode ter no máximo 50 caracteres.")
    private String produto;
    
    @Column(nullable = false)
    @NotNull(message = "qtd_produto deve ser especificada.")
    @Digits(integer = 2, fraction = 0, message = "Quantidade nao pode ser fracionada.")
    @Min(0) @Max(99)
    private int qtd_produto;
    
    @Column(nullable = false)
    @NotNull(message = "valor_produto deve ser preenchido.")
    @Digits(integer = 2, fraction = 0, message = "Quantidade nao pode ser fracionada.")
    private float valor_produto;
    
    @Column(nullable = false)
    @NotNull(message = "valor_total deve ser preenchido.")
    private float valor_total;
    
    
    @ManyToOne
    @NotNull(message = "Campo de fornecedor precisa ser preenchido.")
    private Fornecedor fornecedor;
    
    
    @ManyToOne
    private Usuario usuario;
    


 
    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public int getQtd_produto() {
        return qtd_produto;
    }

    public void setQtd_produto(int qtd_produto) {
        this.qtd_produto = qtd_produto;
    }

    public float getValor_total() {
        return valor_total;
    }

    public void setValor_total(float valor_total) {
        this.valor_total = valor_total;
    }

    public float getValor_produto() {
        return valor_produto;
    }

    public void setValor_produto(float valor_produto) {
        this.valor_produto = valor_produto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Produto other = (Produto) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + (int) (this.id ^ (this.id >>> 32));
        return hash;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
}
