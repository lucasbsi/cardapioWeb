/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.controller.apirest;

import br.com.cardapioweb.CardapioWeb.model.Administrador;
import br.com.cardapioweb.CardapioWeb.model.Funcionario;
import br.com.cardapioweb.CardapioWeb.service.FuncionarioService;
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
@RequestMapping(path = "/apirest/funcionarios")
public class FuncionarioController {
    @Autowired// ponto de injeção
    private FuncionarioService service;
    
    @GetMapping
    public ResponseEntity getAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page, 
            @RequestParam(name = "size", defaultValue = "10", required = false)int size){
        
        return ResponseEntity.ok(service.findAll(page, size));
    
}
    @GetMapping( path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Integer id){
        return ResponseEntity.ok(service.findById(id));
    }
    
    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Funcionario funcionario){
        funcionario.setId(null);
        service.save(funcionario);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    
    }
    
    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, @RequestBody Funcionario funcionario){
        funcionario.setId(id);
        service.update(funcionario, "", "", "");
        
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    //@DeleteMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity delete(@PathVariable("id") Integer id){
        service.delete(id);
        return ResponseEntity.ok().build();
    }
    
    @PutMapping(path = "/{id}/alterarSenha")
    public ResponseEntity alterarSenha(@PathVariable("id") Integer id,
            @RequestParam(name = "senhaAtual", defaultValue = "", required = true) String senhaAtual,
            @RequestParam(name = "novaSenha", defaultValue = "", required = true) String novaSenha,
            @RequestParam(name = "confirmarNovaSenha", defaultValue = "",required = true) String confirmarNovaSenha){
        
        Funcionario f = service.findById(id);
        service.update(f, senhaAtual, novaSenha, confirmarNovaSenha);
        System.out.println(f);
        return ResponseEntity.ok(id);
    }
}
