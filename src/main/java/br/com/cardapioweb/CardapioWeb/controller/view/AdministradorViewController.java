/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.controller.view;


import br.com.cardapioweb.CardapioWeb.model.Administrador;

import br.com.cardapioweb.CardapioWeb.service.AdministradorService;
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

/**
 *
 * @author lucas
 */
@Controller
@RequestMapping(path = "/administradores")
public class AdministradorViewController {
    @Autowired
    private AdministradorService service;
    
    @GetMapping //invoca a view cardapios
    public String getAll(Model model){
        model.addAttribute("administradores", service.findAll());
        return "administradores";
    }
    
    @GetMapping(path = "/administrador")
    public String cadastro(Model model) {
        model.addAttribute("administrador", new Administrador());
        //model.addAttribute("tiposDias", DiaSemanaEnum.values());
        return "formAdministrador";
    }
    
    @PostMapping(path = "/administrador")
    public String save(@Valid @ModelAttribute Administrador administrador, 
            BindingResult result,
            @RequestParam("confirmarSenha") String confirmarSenha,
            Model model) {
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formAdministrador";

        }
        if(!administrador.getSenha().equals(confirmarSenha)){
            model.addAttribute("msgErros", new ObjectError("administrador", "Campos senha e Confirmar senha devem ser iguais"));
            return "formAdministrador";
        }
        administrador.setId(null);
        try {
            service.save(administrador);//service.save(administrador, file);
            model.addAttribute("msgSucesso", "Administrador cadastrado com sucesso.");
            model.addAttribute("administrador", new Administrador());
            return "formAdministrador";

        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("administrador", e.getMessage()));
            return "formAdministrador";
        }
    }
    
    @GetMapping(path = "/administrador/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("administrador", service.findById(id));
        //model.addAttribute("tiposDias", DiaSemanaEnum.values());
        return "formAdministrador";
    }
    
    @PostMapping(path = "/administrador/{id}")
    public String update(@Valid @ModelAttribute Administrador administrador, 
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
            return "formAdministrador";
        }
       // if (result.hasErrors()) {
       //     model.addAttribute("msgErros", result.getAllErrors());
       //     return "formAdministrador";
       //
       // }
        administrador.setId(id);
        try {
            service.update(administrador, "", "", "");
            model.addAttribute("msgSucesso", "Administrador atualizado com sucesso.");
            model.addAttribute("administrador", administrador);
            return "formAdministrador";

        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("administrador", e.getMessage()));
            return "formAdministrador";
        }
    }
    
    
    /*@PostMapping(path = "/administrador/{id}")
    public String atualizar(@Valid @ModelAttribute Administrador administrador, BindingResult result,
            @RequestParam("file") MultipartFile file, @PathVariable("id") Integer id, Model model,
            @RequestParam(name = "senhaAtual", defaultValue = "", required = true) String senhaAtual,
            @RequestParam(name = "novaSenha", defaultValue = "", required = true) String novaSenha,
            @RequestParam(name = "confirmarNovaSenha", defaultValue = "",required = true) String confirmarNovaSenha){
        if (result.hasErrors()) {
            model.addAttribute("msgErros", result.getAllErrors());
            return "formAdministrador";

        }
        administrador.setId(id);
        try {
            service.update(administrador, senhaAtual, novaSenha, confirmarNovaSenha);//service.save(administrador, file);
            model.addAttribute("msgSucesso", "Administrador cadastrador com sucesso.");
            model.addAttribute("administrador", new Administrador());
            return "formAdministrador";

        } catch (Exception e) {
            model.addAttribute("msgErros", new ObjectError("administrador", e.getMessage()));
            return "formAdministrador";
        }
    }*/
    
    @GetMapping(path = "{id}/deletar")
    public String deletar(@PathVariable("id") Integer id){
      service.delete(id);
      return "redirect:/administradores";
    }
    
}