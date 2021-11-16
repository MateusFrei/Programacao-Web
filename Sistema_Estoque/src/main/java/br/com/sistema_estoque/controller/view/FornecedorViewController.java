
package br.com.sistema_estoque.controller.view;

import br.com.sistema_estoque.model.Fornecedor;
import br.com.sistema_estoque.service.FornecedorService;
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
@RequestMapping(path = "/fornecedores")
public class FornecedorViewController {
    @Autowired
    private FornecedorService service;
    
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("fornecedores",service.findAl());
        return "fornecedores";
    }
    
    @GetMapping(path = "/fornecedor")
    public String cadastro(Model model){
        model.addAttribute("fornecedor", new Fornecedor());
        return "formFornecedor";
    }
    
    @PostMapping(path = "/fornecedor")
    public String save(@Valid @ModelAttribute Fornecedor fornecedor, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formFornecedor";
        }

        fornecedor.setId(null);
        try {
            service.save(fornecedor);
            model.addAttribute("msgSucesso", "Funcionário cadastrado com sucesso.");
            model.addAttribute("fornecedor", new Fornecedor());
            return "formFornecedor";
        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("fornecedor", e.getMessage()));
            return "formFornecedor";
        }
    }

    @GetMapping(path = "/{id}/deletar")
    public String deletar(@PathVariable("id") Long id){
        service.delete(id);
        return "redirect:/fornecedores";
    }     
}
