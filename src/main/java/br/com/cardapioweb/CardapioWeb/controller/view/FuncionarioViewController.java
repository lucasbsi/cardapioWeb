/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.controller.view;

import br.com.cardapioweb.CardapioWeb.model.Cardapio;
import br.com.cardapioweb.CardapioWeb.model.DiaSemanaEnum;
import br.com.cardapioweb.CardapioWeb.model.Funcionario;
import br.com.cardapioweb.CardapioWeb.service.CardapioService;
import br.com.cardapioweb.CardapioWeb.service.FuncionarioService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lucas
 */
@Controller
@RequestMapping(path = "/funcionarios")
public class FuncionarioViewController {

    @Autowired
    private FuncionarioService service;

    @GetMapping //invoca a view cardapios
    public String getAll(Model model) {
        model.addAttribute("funcionarios", service.findAll());
        return "funcionarios";
    }

    @GetMapping(path = "/funcionario")
    public String cadastro(Model model) {
        model.addAttribute("funcionario", new Funcionario());
        //model.addAttribute("tiposDias", DiaSemanaEnum.values());
        return "formFuncionario";
    }

    @PostMapping(path = "/funcionario")
    public String save(@Valid @ModelAttribute Funcionario funcionario, 
            BindingResult result,
            @RequestParam("confirmarSenha") String confirmarSenha,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formFuncionario";

        }
        if(!funcionario.getSenha().equals(confirmarSenha)){
            model.addAttribute("msgErros", new ObjectError("funcionario", "Campos senha e Confirmar senha devem ser iguais"));
            return "formFuncionario";
        }
        funcionario.setId(null);
        try {
            service.save(funcionario);//service.save(funcionario, file);
            model.addAttribute("msgSucesso", "Funcionario cadastrado com sucesso.");
            model.addAttribute("funcionario", new Funcionario());
            return "formFuncionario";

        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            return "formFuncionario";
        }
    }
    
    @GetMapping(path = "/funcionario/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("funcionario", service.findById(id));
        //model.addAttribute("tiposDias", DiaSemanaEnum.values());
        return "formFuncionario";
    }
    
    @PostMapping(path = "/funcionario/{id}")
    public String update(@Valid @ModelAttribute Funcionario funcionario, 
            BindingResult result,
            @PathVariable("id") Integer id, 
            //@RequestParam(name = "senhaAtual", defaultValue = "", required = true) String senhaAtual,
            //@RequestParam(name = "novaSenha", defaultValue = "", required = true) String novaSenha,
            //@RequestParam(name = "confirmarNovaSenha", defaultValue = "",required = true) String confirmarNovaSenha,
            Model model){
        List<FieldError> list = new ArrayList<>();
        for(FieldError fe : result.getFieldErrors()){
            if(!fe.getField().equals("senha")){
                list.add(fe);
            }
        }
        if (!list.isEmpty()){
            model.addAttribute("msgErros", list);
            return "formFuncionario";
        }
       // if (result.hasErrors()) {
       //     model.addAttribute("msgErros", result.getAllErrors());
       //     return "formFuncionario";
       //
       // }
        funcionario.setId(id);
        try {
            service.update(funcionario, "", "", "");
            model.addAttribute("msgSucesso", "Funcionario atualizado com sucesso.");
            model.addAttribute("funcionario", funcionario);
            return "formFuncionario";

        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            return "formFuncionario";
        }
    }
    
    
    /*@PostMapping(path = "/funcionario/{id}")
    public String atualizar(@Valid @ModelAttribute Funcionario funcionario, BindingResult result,
            @RequestParam("file") MultipartFile file, @PathVariable("id") Integer id, Model model,
            @RequestParam(name = "senhaAtual", defaultValue = "", required = true) String senhaAtual,
            @RequestParam(name = "novaSenha", defaultValue = "", required = true) String novaSenha,
            @RequestParam(name = "confirmarNovaSenha", defaultValue = "",required = true) String confirmarNovaSenha){
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formFuncionario";

        }
        funcionario.setId(id);
        try {
            service.update(funcionario, senhaAtual, novaSenha, confirmarNovaSenha);//service.save(funcionario, file);
            model.addAttribute("msgSucesso", "Funcionario cadastrador com sucesso.");
            model.addAttribute("funcionario", new Funcionario());
            return "formFuncionario";

        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("funcionario", e.getMessage()));
            return "formFuncionario";
        }
    }*/
    
    @GetMapping(path = "{id}/deletar")
    public String deletar(@PathVariable("id") Integer id){
      service.delete(id);
      return "redirect:/funcionarios";
    }

}
