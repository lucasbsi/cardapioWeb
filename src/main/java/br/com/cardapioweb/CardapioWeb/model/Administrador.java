/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.model;

import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author lucas
 */
@Entity
public class Administrador extends Usuario{

    public Administrador() {
    }

    public Administrador(Integer id, String login, String senha, String nome, String telefone) {
        super(id, login, senha, nome, telefone);
    }
    
    

    
    
    
    
    
    
    
}
