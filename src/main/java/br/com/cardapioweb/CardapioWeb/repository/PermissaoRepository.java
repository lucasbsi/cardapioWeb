/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.repository;

import br.com.cardapioweb.CardapioWeb.model.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */

@Repository
public interface PermissaoRepository extends JpaRepository<Permissao, Integer>{
    
}
