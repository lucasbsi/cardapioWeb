/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.repository;

import br.com.cardapioweb.CardapioWeb.model.Funcionario;
import br.com.cardapioweb.CardapioWeb.model.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author lucas
 */
@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer>{
    @Query ("SELECT u FROM Usuario u WHERE u.nome = :nome OR u.id = :id")
    public List<Usuario> findByNomeOrId (@Param("nome") String nome, @Param("id") Integer id);
    
    @Query ("SELECT u FROM Usuario u WHERE u.cpf = :cpf")
    public List<Usuario> findByCpf (@Param("cpf") String cpf);
    
    @Query ("SELECT u FROM Usuario u WHERE u.login = :login")
    public Funcionario findByLogin(@Param("login") String login);
    
}
