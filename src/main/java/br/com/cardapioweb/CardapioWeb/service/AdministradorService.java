/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.service;

import br.com.cardapioweb.CardapioWeb.model.Administrador;
import br.com.cardapioweb.CardapioWeb.model.Usuario;
import br.com.cardapioweb.CardapioWeb.repository.AdministradorRepository;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.*;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lucas
 */
@Service
public class AdministradorService {
    
    @Autowired
    private AdministradorRepository repo;

    public List<Administrador> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Administrador> findAll() {
        return repo.findAll();

    }

    public Administrador findById(Integer id) {
        Optional<Administrador> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Administrador não encontrado");
        }
        return result.get();
    }

    public Administrador save(Administrador a) {
        
        try {
            return repo.save(a);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o adm");
        }
    }

    public Administrador update(Administrador a, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
        //Verifica se o administrador já existe
        Administrador obj = findById(a.getId());
        //Verifica alteração da senha

        alterarSenha(obj, obj.getSenha(), novaSenha, confirmarNovaSenha);
        try {
           a.setLogin(obj.getLogin());
            return repo.save(a);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o Administrador");
        }

    }

    public void delete(Integer id) {
        //localiza o funcionario
        Administrador obj = findById(id);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao deletar o Administrador");

        }
    }

    

    private void alterarSenha(Administrador obj, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
        if (!senhaAtual.isBlank() && !novaSenha.isBlank() && !confirmarNovaSenha.isBlank()) {
            if (senhaAtual.equals(obj.getSenha())) {
                throw new RuntimeException("Senha atual está incorreta");
            }
            if (novaSenha.equals(confirmarNovaSenha)) {
                throw new RuntimeException("Nova senha e Confirmar senha não conferem");
            }
            obj.setSenha(novaSenha);
        }

    }

    
}