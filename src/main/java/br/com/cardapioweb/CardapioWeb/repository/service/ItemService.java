/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.repository.service;

import br.com.cardapioweb.CardapioWeb.model.Item;
import br.com.cardapioweb.CardapioWeb.repository.ItemRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author lucas
 */
public class ItemService {
    
    @Autowired
    private ItemRepository repo;

    public List<Item> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Item> findAll() {
        return repo.findAll();

    }

    public List<Item> findAll(int page, int size, int opc) {

        Pageable p = PageRequest.of(page, size);
        try {
            return repo.findAllItem();
        } catch (Exception e) {
            throw new RuntimeException("Erro para localizar o cardápio");
        }

    }
    
    public Item findById(Integer id) {
        Optional<Item> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Item não encontrado");
        }
        return result.get();
    }

    
    
    public Item save(Item c) {
        
        try {
            return repo.save(c);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o Item");
        }
    }
    
    public Item update(Item c, String descricao, Double valor) {
        //Verifica se o item já existe
        Item obj = findById(c.getId());
        obj.setDescricao(descricao);
        obj.setValorAdicional(valor);
        
        try {
           //rever se devo salvar o obj ou o c
            return repo.save(obj);
            
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o Item");
        }

    }

    public void delete(Integer id) {
        //localiza o funcionario
        Item obj = findById(id);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao deletar o Item");

        }
    }
    
}
