/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.controller.view;

import br.com.cardapioweb.CardapioWeb.model.Administrador;
import br.com.cardapioweb.CardapioWeb.model.Funcionario;
import br.com.cardapioweb.CardapioWeb.service.AdministradorService;
import br.com.cardapioweb.CardapioWeb.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        return "formA";
    }
    
}