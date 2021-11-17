/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.model;

import br.com.cardapioweb.CardapioWeb.annotation.DescriptionValidation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author lucas
 */
@Entity
public class Item implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(nullable = false, length = 40, unique = true, updatable = true)
    @NotBlank(message= "A descrição do item é obrigatório")
    @Length(max= 40, message= "A descrição do item deve ter no máximo 40 caracteres.")
    @DescriptionValidation(message = "A descrição está no formato incorreto. Ex.: Feijão")
    public String descricao;
    
    @Column(nullable = false, length = 6, unique = false, updatable = true)
    private Double valorAdicional;
    
    @JsonIgnore
    //@ManyToMany
    @ManyToMany(mappedBy="itens")
    //@Valid//ao validar o objeto da classe principal, também valide os dependentes 
    private List<Cardapio> cardapios = new ArrayList<>();

    public Item() {
    }

    public Item(Integer id, String descricao, Double valorAdicional) {
        this.id = id;
        this.descricao = descricao;
        this.valorAdicional = valorAdicional;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(Double valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public List<Cardapio> getCardapios() {
        return cardapios;
    }

    public void setCardapios(List<Cardapio> cardapios) {
        this.cardapios = cardapios;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.id);
        hash = 43 * hash + Objects.hashCode(this.descricao);
        hash = 43 * hash + Objects.hashCode(this.valorAdicional);
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
        final Item other = (Item) obj;
        if (!Objects.equals(this.descricao, other.descricao)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.valorAdicional, other.valorAdicional)) {
            return false;
        }
        return true;
    }
    
    
    
    
    
}
