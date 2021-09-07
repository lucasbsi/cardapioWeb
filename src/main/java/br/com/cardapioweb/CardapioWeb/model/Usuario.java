/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.model;

import java.util.Objects;

/**
 *
 * @author lucas
 */
public class Usuario {
    private Integer IdUsuario;
    private String login;
    private String senha;
    private String nome;
    private String telefone;

    public Usuario() {
    }

    public Usuario(Integer IdUsuario, String login, String senha, String nome, String telefone) {
        this.IdUsuario = IdUsuario;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Integer getIdUsuario() {
        return IdUsuario;
    }

    public void setIdUsuario(Integer IdUsuario) {
        this.IdUsuario = IdUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.IdUsuario);
        hash = 71 * hash + Objects.hashCode(this.login);
        hash = 71 * hash + Objects.hashCode(this.senha);
        hash = 71 * hash + Objects.hashCode(this.nome);
        hash = 71 * hash + Objects.hashCode(this.telefone);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.senha, other.senha)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.telefone, other.telefone)) {
            return false;
        }
        if (!Objects.equals(this.IdUsuario, other.IdUsuario)) {
            return false;
        }
        return true;
    }
    
    
    
}
