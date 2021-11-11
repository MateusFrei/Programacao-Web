package br.com.sistema_estoque.controller.view;

import br.com.sistema_estoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
}
