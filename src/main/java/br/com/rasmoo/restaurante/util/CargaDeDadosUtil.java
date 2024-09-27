package br.com.rasmoo.restaurante.util;

import br.com.rasmoo.restaurante.dao.*;
import br.com.rasmoo.restaurante.entity.*;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class CargaDeDadosUtil {

    private CargaDeDadosUtil() {

    }

    public static void cadastrarCategorias(EntityManager entityManager) {

        Categoria entrada = new Categoria("Entrada");
        Categoria salada = new Categoria("Salada");
        Categoria principal = new Categoria("Pratos Principais");

        CategoriaDao categoriaDao = new CategoriaDao(entityManager);

        categoriaDao.cadastrar(entrada);
        entityManager.flush();

        categoriaDao.cadastrar(salada);
        entityManager.flush();

        categoriaDao.cadastrar(principal);
        entityManager.flush();

        entityManager.clear();
    }

    public static void cadastrarProdutosCardapio(EntityManager entityManager) {

        CategoriaDao categoriaDao = new CategoriaDao(entityManager);
        CardapioDao cardapioDao = new CardapioDao(entityManager);

        List<Categoria> categorias = categoriaDao.consultarTodos();

        Cardapio moqueca = new Cardapio(
                "Moqueca",
                "Peixe branco, banana da  terra, arroz e faroca",
                true,
                BigDecimal.valueOf(95.00),
                categorias.get(2));

        Cardapio spaguetti = new Cardapio(
                "Spaguetti",
                "Spaguetti ao molho de parmesão e cogumelos",
                true,
                BigDecimal.valueOf(60.00),
                categorias.get(2));

        Cardapio bife = new Cardapio(
                "Bife",
                "Bife acebolado com arroz branco, farofa e batata frita",
                true,
                BigDecimal.valueOf(59.00),
                categorias.get(2));

        Cardapio burrata = new Cardapio(
                "Burrata",
                "Tomates queimados, rúcula e torrada",
                true,
                BigDecimal.valueOf(15.00),
                categorias.get(0));

        Cardapio bruschetta = new Cardapio(
                "Bruschetta",
                "Tomate, mussarela e manjericão",
                true,
                BigDecimal.valueOf(20.00),
                categorias.get(0));

        Cardapio caprese = new Cardapio(
                "Caprese",
                "Mini rúcula e mussarela",
                true,
                BigDecimal.valueOf(47.00),
                categorias.get(1));

        Cardapio caesar = new Cardapio(
                "Caesar",
                "Mix de folhas, mostarda e mel",
                true,
                BigDecimal.valueOf(59.00),
                categorias.get(1));

        cardapioDao.cadastrar(moqueca);
        cardapioDao.cadastrar(spaguetti);
        cardapioDao.cadastrar(bife);
        cardapioDao.cadastrar(burrata);
        cardapioDao.cadastrar(bruschetta);
        cardapioDao.cadastrar(caprese);
        cardapioDao.cadastrar(caesar);

        entityManager.flush();
        entityManager.clear();
    }

    public static void cadastrarClientes(EntityManager entityManager) {

        ClienteDao clienteDao = new ClienteDao(entityManager);
        EnderecoDao enderecoDao = new EnderecoDao(entityManager);


        Cliente andressa = new Cliente(
                "11111111111",
                "Andressa");

        Endereco enderecoAndressa = new Endereco(
                "11111111",
                "Um",
                "A",
                "Belo Horizonte",
                "Minas Gerais"
        );

        andressa.addEndereco(enderecoAndressa);

        Cliente nathan = new Cliente(
                "22222222222",
                "Nathan"
        );

        Endereco enderecoNathan = new Endereco(
                "2222222",
                "Dois",
                "B",
                "Belo Horizonte",
                "Minas Gerais"
        );

        nathan.addEndereco(enderecoNathan);

        Cliente jorel = new Cliente(
                "33333333333",
                "jorel"
        );

        Endereco enderecoJorel =  new Endereco(
                "33333333",
                "Três",
                "C",
                "Rio de Janeiro",
                "Rio de Janeiro"
        );

        jorel.addEndereco(enderecoJorel);

        enderecoDao.cadastrar(enderecoAndressa);
        enderecoDao.cadastrar(enderecoNathan);
        enderecoDao.cadastrar(enderecoJorel);
        clienteDao.cadastrar(andressa);
        clienteDao.cadastrar(nathan);
        clienteDao.cadastrar(jorel);

        entityManager.flush();
        entityManager.clear();
    }

    public static void cadastrarOrdensClientes(EntityManager entityManager){

        OrdemDao ordemDao = new OrdemDao(entityManager);
        ClienteDao clienteDao = new ClienteDao(entityManager);
        CardapioDao cardapioDao = new CardapioDao(entityManager);

        Ordem ordem1 = new Ordem(clienteDao.consultar("11111111111"));
        Ordem ordem2 = new Ordem(clienteDao.consultar("22222222222"));
        Ordem ordem3 = new Ordem(clienteDao.consultar("33333333333"));

        ordem1.addOrdensCardapio(new OrdensCardapio(cardapioDao.consultar(1), 1));
        ordem2.addOrdensCardapio(new OrdensCardapio(cardapioDao.consultar(2), 2));
        ordem3.addOrdensCardapio(new OrdensCardapio(cardapioDao.consultar(3), 3));

        ordemDao.cadastrar(ordem1);
        ordemDao.cadastrar(ordem2);
        ordemDao.cadastrar(ordem2);

        entityManager.flush();
        entityManager.clear();
    }
}
