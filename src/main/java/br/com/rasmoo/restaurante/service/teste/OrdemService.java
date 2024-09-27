package br.com.rasmoo.restaurante.service.teste;

import br.com.rasmoo.restaurante.dao.EnderecoDao;
import br.com.rasmoo.restaurante.util.CargaDeDadosUtil;
import br.com.rasmoo.restaurante.util.JPAUtil;

import javax.persistence.EntityManager;

public class OrdemService {
    public static void main(String[] args) {
        EntityManager entityManager = JPAUtil.getEntityManagerRasFood();
        entityManager.getTransaction().begin();

        CargaDeDadosUtil.cadastrarCategorias(entityManager);
        CargaDeDadosUtil.cadastrarProdutosCardapio(entityManager);
        CargaDeDadosUtil.cadastrarClientes(entityManager);
        CargaDeDadosUtil.cadastrarOrdensClientes(entityManager);

        EnderecoDao enderecoDao = new EnderecoDao(entityManager);

        System.out.println(enderecoDao.consultarClientesCriteria(
                "Minas Gerais",
                "Belo Horizonte",
                null));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
