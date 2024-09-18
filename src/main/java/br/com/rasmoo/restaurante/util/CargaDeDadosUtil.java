package br.com.rasmoo.restaurante.util;

import br.com.rasmoo.restaurante.dao.CardapioDao;
import br.com.rasmoo.restaurante.dao.CategoriaDao;
import br.com.rasmoo.restaurante.entity.Cardapio;
import br.com.rasmoo.restaurante.entity.Categoria;

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

        entityManager.getTransaction().commit();
        entityManager.clear();
    }
}
