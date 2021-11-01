/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.service;

import br.com.cardapioweb.CardapioWeb.model.Funcionario;
import br.com.cardapioweb.CardapioWeb.model.Usuario;
import br.com.cardapioweb.CardapioWeb.repository.FuncionarioRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repo;

    public List<Funcionario> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Funcionario> findAll() {
        return repo.findAll();

    }

    public Funcionario findById(Integer id) {
        Optional<Funcionario> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new RuntimeException("Funcionario não encontrado");
        }
        return result.get();
    }

    public Funcionario save(Funcionario f) {
        // verifica se o cpf já está cadastrado
        verificaCpfCadastrado(f.getCpf());
        try {
            return repo.save(f);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o funcionário");
        }
    }

    public Funcionario update(Funcionario f, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
        //Verifica se o funcionário já existe
        Funcionario obj = findById(f.getId());
        //Verifica alteração da senha

        alterarSenha(obj, senhaAtual, novaSenha, confirmarNovaSenha);
        try {
            f.setCpf(obj.getCpf());// garante que o cpf permaneça o mesmo já cadastrado no banco
            f.setLogin(obj.getLogin());
            f.setSenha(obj.getSenha());
            
            
            return repo.save(f);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao salvar o funcionario");
        }

    }

    public void delete(Integer id) {
        //localiza o funcionario
        Funcionario obj = findById(id);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao deletar o Funcionario");

        }
    }

    private void verificaCpfCadastrado(String cpf) {
        List<Usuario> result = repo.findByCpf(cpf);
        if (!result.isEmpty()) {
            throw new RuntimeException("CPF já cadastrado!!!");
        }
    }

    private void alterarSenha(Funcionario obj, String senhaAtual, String novaSenha, String confirmarNovaSenha) {
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
