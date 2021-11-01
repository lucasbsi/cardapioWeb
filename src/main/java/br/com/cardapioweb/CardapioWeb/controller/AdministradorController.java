/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.controller;

import br.com.cardapioweb.CardapioWeb.model.Administrador;
import br.com.cardapioweb.CardapioWeb.service.AdministradorService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author lucas
 */
@RestController
@RequestMapping(path = "/apirest/administradores")
public class AdministradorController {
    
    @Autowired
    private AdministradorService service;
    
    @GetMapping //equivale a @RequestMapping(method = RequestMethod.GET, path = "/getAll")
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page, 
            @RequestParam(name = "size", defaultValue = "10", required = false)int size){
        
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    
    }
    
    
    @GetMapping( path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }
    
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Administrador administrador){
        administrador.setId(null);
        service.save(administrador);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(administrador);
    
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Administrador administrador){
        administrador.setId(id);
        service.update(administrador, "", "", "");
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    //@DeleteMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    
    
    
    
    
}
