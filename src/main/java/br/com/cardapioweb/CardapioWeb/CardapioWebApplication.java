package br.com.cardapioweb.CardapioWeb;

import br.com.cardapioweb.CardapioWeb.model.*;
import br.com.cardapioweb.CardapioWeb.repository.AdministradorRepository;
import br.com.cardapioweb.CardapioWeb.repository.CardapioRepository;
import br.com.cardapioweb.CardapioWeb.repository.FuncionarioRepository;
import br.com.cardapioweb.CardapioWeb.repository.ItemRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CardapioWebApplication implements CommandLineRunner{
    
    @Autowired  // já cria uma instância de adminRepo
    private AdministradorRepository administradorRepo;
    
    @Autowired  // já cria uma instância de funRepo
    private FuncionarioRepository funcionarioRepo;
    
    @Autowired  // já cria uma instância de cardapioRepo
    private CardapioRepository cardapioRepo;
    
    @Autowired  // já cria uma instância de itemRepo
    private ItemRepository itemRepo;
            
    public static void main(String[] args) {
            SpringApplication.run(CardapioWebApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        //Cliente
        Administrador adm1 = new Administrador();
        adm1.setLogin("abc");
        adm1.setNome("lucas");
        adm1.setSenha("senhA762@_");
        adm1.setTelefone("(22) 9999-9999");
        
        administradorRepo.save(adm1);
        
        //Funcionario;
        Funcionario fun1 = new Funcionario();
        fun1.setLogin("amabit");
        fun1.setNome("Amarildo");
        fun1.setSenha("senhA2@_");
        fun1.setTelefone("(22) 8888-9999");
        fun1.setCpf("215.063.620-44");
        
        funcionarioRepo.save(fun1);
        
        
        
        //Item
        Item item1 = new Item();
        item1.setDescricao("Salsa");
        item1.setValorAdicional(2.0);
        //item1.setCardapios();
        
        itemRepo.save(item1);
        
        Item item2 = new Item();
        item2.setDescricao("Cebolinha");
        item2.setValorAdicional(3.0);
        //item1.setCardapios();
        
        itemRepo.save(item2);
        
        
        
         //Cardapio
        Cardapio card1 = new Cardapio();
        card1.setNome("Hojé é segunda");
        card1.setItem(List.of(item1, item2));
        card1.setDia(DiaSemanaEnum.TERCA);
        
        cardapioRepo.save(card1);
        
    }

}
