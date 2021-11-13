package br.com.sistema_estoque.controller.view;

import br.com.sistema_estoque.model.Produto;
import br.com.sistema_estoque.service.ProdutoService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/produtos")
public class ProdutoviewController {
    @Autowired
    private ProdutoService service;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("produtos",service.findAl());
        return "produtos";
    }
    @GetMapping(path = "/produto")
    public String cadastro(Model model){
        model.addAttribute("produto", new Produto());
        
        return "formProduto";
    }    

    @PostMapping(path = "/produto")
    public String save(@Valid @ModelAttribute Produto produto, BindingResult result, Model model) {
        //valores de retorno padão
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formProduto";
        }
        produto.setId(null);
        try {
            service.save(produto);
            model.addAttribute("msgSucesso", "Produto cadastrado com sucesso.");
            model.addAttribute("produto", new Produto());
            return "formProduto";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("produto", e.getMessage()));
            return "formProduto";
        }
    }
    
    @GetMapping(path = "/{id}/deletar")
    public String deletar(@PathVariable("id") Long id) {
        service.delete(id);
        return "redirect:/produtos";
    }    
    
}
