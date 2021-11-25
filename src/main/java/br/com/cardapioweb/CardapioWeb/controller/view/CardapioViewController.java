/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.controller.view;

import br.com.cardapioweb.CardapioWeb.model.Cardapio;
import br.com.cardapioweb.CardapioWeb.model.DiaSemanaEnum;
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
@RequestMapping(path = "/cardapios")
public class CardapioViewController {
    @Autowired
    private CardapioService service;
    
    @Autowired
    private ItemService serviceItem;
    
    @GetMapping //invoca a view cardapios
    public String getAll(Model model){
        model.addAttribute("cardapios", service.findAll());
        return "cardapios";
    }
    
    @GetMapping(path = "/cardapio")
    public String cadastro(Model model) {
        model.addAttribute("cardapio", new Cardapio());
        model.addAttribute("tiposDias", DiaSemanaEnum.values());
        model.addAttribute("itens", serviceItem.findAll());
        return "formCardapio";
    }
    
    @GetMapping(path = "/cardapio/{id}")
    public String editar(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("cardapio", service.findById(id));
        model.addAttribute("itens", serviceItem.findAll());
        //model.addAttribute("tiposDias", DiaSemanaEnum.values());
        return "formCardapio";
    }
    
    
    @GetMapping(path = "{id}/deletar")
    public String deletar(@PathVariable("id") Integer id){
      service.delete(id);
      return "redirect:/cardapios";
    }
    
}
