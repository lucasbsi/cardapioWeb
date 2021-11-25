/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.model;

import br.com.cardapioweb.CardapioWeb.annotation.PasswordValidation;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.*;
import org.hibernate.validator.constraints.Length;


/**
 *
 * @author lucas
 */
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)//cria uma tabela para cada classe
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    
    @Column(nullable = false, length = 8, unique = true, updatable = false)
    @NotBlank(message= "Login é obrigatório")
    @Length(max= 8, message= "Login deve ter no máximo 8 caracteres.")
    private String login;
    
    @Column(nullable = false)//, length = 10)
    @NotBlank(message= "Senha é obrigatório")
    //@Length(max= 10, message= "Senha deve ter no máximo 10 caracteres.")
    @PasswordValidation (message = "Senha inválida")
    private String senha;
    
    @Column(nullable = false, length = 40)
    @NotBlank(message= "Nome é obrigatório")
    @Length(max= 40, message= "Nome deve ter no máximo 40 caracteres.")
    private String nome;
    
    @Column(nullable = true, length = 14)
    @Length(max= 14, message= "Login deve ter no máximo 14 caracteres.")
    private String telefone;
    
    @ManyToMany(fetch = FetchType.EAGER)
    @Size(min = 1, message = "Usuario deve ter no mínimo 1 permissão")
    @Column
    private List<Permissao> permissoes = new ArrayList<>();

    public Usuario() {
    }

    public Usuario(Integer id, String login, String senha, String nome, String telefone) {
        this.id = id;
        this.login = login;
        this.senha = senha;
        this.nome = nome;
        this.telefone = telefone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.login);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    

    
    
    
    
}
