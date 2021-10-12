/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

/**
 *
 * @author lucas
 */
@Entity
@Table(name = "funcionario")
public class Funcionario extends Usuario {
    
    @Column(length = 14, unique = true, updatable = false, nullable = false)
    @CPF(message = "CPF inválido")
    @NotBlank(message = "CPF obrigatório")
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
