/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.repository;

import br.com.cardapioweb.CardapioWeb.model.Cardapio;
import br.com.cardapioweb.CardapioWeb.model.DiaSemanaEnum;
import br.com.cardapioweb.CardapioWeb.model.Item;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author lucas
 */
public interface ItemRepository extends JpaRepository<Item, Integer>{
    
    @Query("SELECT i FROM Item i")
    public List<Item> findAllItem ();
    
    @Query ("SELECT i FROM Item i INNER JOIN i.cardapios c WHERE c.dia = :dia")
    public List<Cardapio> findItemByWeek (DiaSemanaEnum dia);
    
    @Query ("SELECT i FROM Item i WHERE i.descricao = :descricao")
    public List<Item> findItemByDescricao (String descricao);
    
}
