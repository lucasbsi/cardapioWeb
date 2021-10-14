/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.repository.service;

import br.com.cardapioweb.CardapioWeb.model.Administrador;
import br.com.cardapioweb.CardapioWeb.model.Cardapio;
import br.com.cardapioweb.CardapioWeb.model.DiaSemanaEnum;
import br.com.cardapioweb.CardapioWeb.model.Funcionario;
import br.com.cardapioweb.CardapioWeb.model.Item;
import br.com.cardapioweb.CardapioWeb.repository.CardapioRepository;
import br.com.cardapioweb.CardapioWeb.repository.FuncionarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author lucas
 */
public class CardapioService {

    @Autowired
    private CardapioRepository repo;

    public List<Cardapio> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Cardapio> findAll() {
        return repo.findAll();

    }

    public List<Cardapio> findAll(int page, int size, int opc) {

        Pageable p = PageRequest.of(page, size);
        try {
            return repo.findAllCardapio();
        } catch (Exception e) {
            throw new RuntimeException("Erro para localizar o cardápio");
        }

    }
    
    public Cardapio findById(Integer id) {
        Optional<Cardapio> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Cardapio não encontrado");
        }
        return result.get();
    }

    public Cardapio findByWeek(DiaSemanaEnum dia) {
        try {
            return repo.findByWeek(dia);
        } catch (Exception e) {
            throw new RuntimeException("Não localizamos o cardápio" + dia + ".");
        }
    }
    
    public Cardapio save(Cardapio c) {
        
        try {
            return repo.save(c);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o Cardapio");
        }
    }
    
    public Cardapio update(Cardapio c, List<Item> itens, String nome) {
        //Verifica se o cardapio já existe
        Cardapio obj = findByWeek(c.getDia());
        obj.setNome(nome);
        obj.setItem(itens);
        
        try {
           //rever se devo salvar o obj ou o c
            return repo.save(obj);
            
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o Cardapio");
        }

    }

    public void delete(Integer id) {
        //localiza o funcionario
        Cardapio obj = findById(id);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao deletar o Cardapio");

        }
    }
    
    

}
