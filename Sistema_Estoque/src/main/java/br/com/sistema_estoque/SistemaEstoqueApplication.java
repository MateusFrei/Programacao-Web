package br.com.sistema_estoque;

import br.com.sistema_estoque.model.Administrador;
import br.com.sistema_estoque.model.Fornecedor;
import br.com.sistema_estoque.model.Produto;
import br.com.sistema_estoque.model.Usuario;
import br.com.sistema_estoque.repository.AdministradorRepository;
import br.com.sistema_estoque.repository.FornecedorRepository;
import br.com.sistema_estoque.repository.ProdutoRepository;
import br.com.sistema_estoque.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SistemaEstoqueApplication implements CommandLineRunner{

    @Autowired
    private UserRepository usuariorep;
    
    @Autowired
    private AdministradorRepository admrep;
            
    @Autowired
    private FornecedorRepository fornerep;
    
    @Autowired
    private ProdutoRepository prodrep;
    
	public static void main(String[] args) {
		SpringApplication.run(SistemaEstoqueApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Fornecedor f1 = new Fornecedor();
        Fornecedor f2 = new Fornecedor();
        
        Administrador adm = new Administrador();
        Usuario u1 = new Usuario();
        Usuario u2 = new Usuario();
         
        //produtos
        Produto prod = new Produto();
        prod.setProduto("Maizena");
        prod.setQtd_produto(5);
        prod.setValor_produto(25);
        prod.setValor_total(125);
        prod.setUsuario(u1);

               
        Produto prod2 = new Produto();

        prod2.setProduto("guaravita");
        prod2.setQtd_produto(5);
        prod2.setValor_produto(25);
        prod2.setValor_total(125);
        prod2.setUsuario(u2);

        
        Produto prod3 = new Produto();

        prod3.setProduto("guaraviton");
        prod3.setQtd_produto(5);
        prod3.setUsuario(u2);
        prod3.setValor_produto(25);
        prod3.setValor_total(125);

    

        //administradores
        adm.setLogin("nathan");
        adm.setSenha("nat123");
        adm.setNome("nathan feiotosa");
        adm.setEmail("nathan@gmail.com");        
        adm.setUsuarios(List.of(u1,u2));
        admrep.save(adm);               
        
        //ususarios
        u1.setAdministrador(adm);
        u1.setCpf("765.620.220-02");
        u1.setNome("Lucas");
        u1.setEmail("lucas@gmail.com");
        u1.setLogin("lolcu");
        u1.setSenha("paulosshsh");
        u1.setFornecedores(List.of(f1,f2));
        u1.setProdutos(List.of(prod,prod2));
        usuariorep.save(u1);
                    
        u2.setAdministrador(adm);
        u2.setCpf("153.890.520-56");
        u2.setNome("lilito barros");
        u2.setEmail("pirulitos@gmail.com");
        u2.setLogin("lola");
        u2.setSenha("kkkkeaemen");
        u2.setFornecedores(List.of(f1,f2));
        u2.setProdutos(List.of(prod3));
        usuariorep.save(u2);
         
        //fornecedores
        f1.setNome("Mateus");
        f1.setCnpj("23.647.291/0001-00");
        f1.setEmpresa("Coca-cola");
        
        f1.setUsuario(u1);
        f1.setEmail("paulo@gmail.com");
        f1.setProdutos(List.of(prod));
        f1.setProdutos(List.of(prod,prod2));
     
        prod.setFornecedor(f1);
        prod2.setFornecedor(f1); 
        fornerep.save(f1);
        prodrep.save(prod2);                
        prodrep.save(prod);     
 
        
        //fornecedor nos produtos     
        
        f2.setNome("Joaquin");
        f2.setCnpj("90.140.349/0001-11");
        f2.setEmpresa("ambev-cola");
        
        f2.setEmail("bingulinho@gmail.com");
        f2.setUsuario(u1);
        f2.setProdutos(List.of(prod3));    
        prod3.setFornecedor(f2);
        fornerep.save(f2);
        prodrep.save(prod3);
     
        
    }

}
