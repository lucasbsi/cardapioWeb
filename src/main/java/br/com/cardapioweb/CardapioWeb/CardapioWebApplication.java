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
        
        Administrador adm2 = new Administrador();
        adm2.setLogin("bca");
        adm2.setNome("bca");
        adm2.setSenha("senhA962@_");
        adm2.setTelefone("(22) 8888-8888");
        
        administradorRepo.save(adm2);
        
        
         //Funcionario;
        Funcionario fun2 = new Funcionario();
        fun2.setLogin("zeus");
        fun2.setNome("roberval");
        fun2.setSenha("sogrA022@&");
        fun2.setTelefone("(22) 1234-9999");
        fun2.setCpf("173.475.180-08");
        
        funcionarioRepo.save(fun2);
        
        //-----------------------------------------------------------------------
        
        //Item
        Item item1 = new Item();
        item1.setDescricao("Salsa");
        item1.setValorAdicional(2.0);
        
        
        Item item2 = new Item();
        item2.setDescricao("Cebolinha");
        item2.setValorAdicional(3.0);
        
         Item item3 = new Item();
        item3.setDescricao("Feijão");
        item3.setValorAdicional(2.0);
        
        
        Item item4 = new Item();
        item4.setDescricao("Arroz");
        item4.setValorAdicional(3.0);
        
        
        
        
         //Cardapio
        Cardapio card1 = new Cardapio();
        card1.setNome("Hojé é segunda");
        card1.setItem(List.of(item1, item2, item3, item4));
        card1.setDia(DiaSemanaEnum.TERCA);
        
        
        //----------------------------------------------------
        
        Cardapio card2 = new Cardapio();
        card2.setNome("Hojé é Sexta");
        card2.setItem(List.of(item3, item4));
        card2.setDia(DiaSemanaEnum.SEXTA);
        
       
        
        item1.setCardapios(List.of(card1));
        itemRepo.save(item1);
        
        item2.setCardapios(List.of(card1));
        itemRepo.save(item2);
        
        item3.setCardapios(List.of(card1, card2));
        itemRepo.save(item3);
        
        item4.setCardapios(List.of(card1,card2));
        itemRepo.save(item4);
        
        
        cardapioRepo.save(card1);
        cardapioRepo.save(card2);
        
        
    }

}
