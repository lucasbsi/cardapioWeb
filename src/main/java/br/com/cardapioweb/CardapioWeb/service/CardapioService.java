/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cardapioweb.CardapioWeb.service;

import br.com.cardapioweb.CardapioWeb.exception.NotFoundException;
import br.com.cardapioweb.CardapioWeb.model.Cardapio;
import br.com.cardapioweb.CardapioWeb.model.DiaSemanaEnum;
import br.com.cardapioweb.CardapioWeb.model.Item;
import br.com.cardapioweb.CardapioWeb.repository.CardapioRepository;
import java.util.List;
import java.util.Optional;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 *
 * @author lucas
 */
@Service
public class CardapioService {

    @Autowired
    private CardapioRepository repo;

    public List<Cardapio> findAll(int page, int size) {
        Pageable p = PageRequest.of(page, size);
        return repo.findAll(p).toList();
    }

    public List<Cardapio> findAll() {
        return repo.findAll();

    }

    public List<Cardapio> findAll(int page, int size, int opc) {

        Pageable p = PageRequest.of(page, size);
        try {
            return repo.findAllCardapio();
        } catch (Exception e) {
            throw new RuntimeException("Erro para localizar o cardápio");
        }

    }
    
    public Cardapio findById(Integer id) {
        Optional<Cardapio> result = repo.findById(id);
        if (result.isEmpty()) {
            throw new NotFoundException("Cardapio não encontrado");
        }
        return result.get();
    }

    public Cardapio findByWeek(DiaSemanaEnum dia) {
        try {
            return repo.findByWeek(dia);
        } catch (Exception e) {
            throw new NotFoundException("Não localizamos o cardápio" + dia + ".");
        }
    }
    
    public Cardapio save(Cardapio c) {
        
        try {
            return repo.save(c);
        } catch (Exception e) {
//            Throwable t = e;
//            while (t.getCause() != null){
//                t = t.getCause();
//                if (t instanceof ConstraintViolationException){
//                    throw ((ConstraintViolationException) t);
//                }
//            }
            throw new RuntimeException("Falha ao salvar o Cardapio");
        }
    }
    //----------------- teste do postman POST
//    http://localhost:8080/apirest/cardapios/
//    
//    {
//    "nome": "seg",
//    "dia": "SEGUNDA",
//    "item": [
//        {
//            "id": 3,
//            "descricao": "Feijão",
//            "valorAdicional": 2.0
//        },
//        {
//            "id": 4,
//            "descricao": "Arroz",
//            "valorAdicional": 3.0
//        }
//    ]
//}
    
    public Cardapio update(Cardapio c, List<Item> itens, String nome) {
        //Verifica se o cardapio já existe
        Cardapio obj = findById(c.getId());
//     
        
        try {
            //garante que o enum não seja alterado
            c.setDia(obj.getDia());
            return repo.save(c);
            
        } catch (Exception e) {
            Throwable t = e;
            while (t.getCause() != null){
                t = t.getCause();
                if (t instanceof ConstraintViolationException){
                    throw ((ConstraintViolationException) t);
                }
            }
            throw new RuntimeException("Falha ao salvar o Cardapio");
        }

    }

    public void delete(Integer id) {
        //localiza o funcionario
        Cardapio obj = findById(id);
        try {
            repo.delete(obj);
        } catch (Exception e) {
            throw new RuntimeException("Falha ao deletar o Cardapio");

        }
    }
    
    

}
