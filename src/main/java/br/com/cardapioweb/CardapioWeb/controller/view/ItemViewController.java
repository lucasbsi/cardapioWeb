/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.controller.view;

import br.com.cardapioweb.CardapioWeb.model.Cardapio;
import br.com.cardapioweb.CardapioWeb.model.DiaSemanaEnum;
import br.com.cardapioweb.CardapioWeb.model.Item;
import br.com.cardapioweb.CardapioWeb.service.CardapioService;
import br.com.cardapioweb.CardapioWeb.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lucas
 */
@Controller
@RequestMapping(path = "/itens")
public class ItemViewController {
    @Autowired
    private ItemService service;
    
    @GetMapping //invoca a view cardapios
    public String getAll(Model model){
        model.addAttribute("itens", service.findAll());
        return "itens";
    }
    
    @GetMapping(path = "/item")
    public String cadastro(Model model) {
        model.addAttribute("item", new Item());
        //model.addAttribute("tiposDias", DiaSemanaEnum.values());
        return "formItem";
    }
    
    @GetMapping(path = "{id}/deletar")
    public String deletar(@PathVariable("id") Integer id){
      service.delete(id);
      return "redirect:/itens";
    }
    
}