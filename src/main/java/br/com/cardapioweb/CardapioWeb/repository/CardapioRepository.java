/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.repository;

import br.com.cardapioweb.CardapioWeb.model.Cardapio;
import br.com.cardapioweb.CardapioWeb.model.DiaSemanaEnum;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface CardapioRepository extends JpaRepository<Cardapio, Integer>{
    @Query("SELECT c FROM Cardapio c")
    public List<Cardapio> findAllCardapio ();
    
    @Query ("SELECT c FROM Cardapio c WHERE c.dia = :dia")
    public Cardapio findByWeek (DiaSemanaEnum dia);
    
    
}
