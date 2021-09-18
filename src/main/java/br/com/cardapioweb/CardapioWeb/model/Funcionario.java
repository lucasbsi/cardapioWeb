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
public class Funcionario extends Usuario {
    
    @Column(length = 14, unique = true, updatable = false)
    private String cpf;

    public Funcionario() {
    }

    public Funcionario(Integer id, String cpf) {
       this.cpf = cpf;
    }

    public Funcionario(Integer id, String cpf, Integer IdUsuario, String login, String senha, String nome, String telefone) {
        super(IdUsuario, login, senha, nome, telefone);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    
    
    
    
}
