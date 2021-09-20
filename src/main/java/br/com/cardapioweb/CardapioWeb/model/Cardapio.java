/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author lucas
 */
@Entity
public class Cardapio implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 40, unique = true, updatable = true)
    private String nome;
    
    @JsonManagedReference
    @ManyToMany
    private List<Item> itens = new ArrayList<>();
    
    @Column(nullable = false)
    private DiaSemanaEnum dia;

    public Cardapio() {
    }

    public Cardapio(Integer id, String nome, List<Item> item) {
        this.id = id;
        this.nome = nome;
        this.itens = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Item> getItem() {
        return itens;
    }

    public void setItem(List<Item> itens) {
        this.itens = itens;
    }

    public DiaSemanaEnum getDia() {
        return dia;
    }

    public void setDia(DiaSemanaEnum dia) {
        this.dia = dia;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.id);
        hash = 17 * hash + Objects.hashCode(this.nome);
        hash = 17 * hash + Objects.hashCode(this.itens);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cardapio other = (Cardapio) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.itens, other.itens)) {
            return false;
        }
        return true;
    }
    
    
    
    
        
}
