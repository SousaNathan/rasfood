package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Ordem;
import br.com.rasmoo.restaurante.vo.ItensPrincipaisVo;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class OrdemDao {

    private EntityManager entityManager;

    public OrdemDao(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void cadastrar(final Ordem ordem) {

        this.entityManager.persist(ordem);
    }

    public Ordem consultar(final Integer id) {

        return this.entityManager.find(Ordem.class, id);
    }

    public List<Ordem> consultarTodos() {

        try {
            String jpql = "SELECT o FROM Ordem o";

            return this.entityManager
                    .createQuery(jpql, Ordem.class)
                    .getResultList();
        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public List<ItensPrincipaisVo> consultarItensMaisVendidos() {
        try {
            String jpql = " " +
                    "SELECT new br.com.rasmoo.restaurante.vo.ItensPrincipaisVo(c.nome, SUM(oc.quantidade)) " +
                    "  FROM Ordem o " +
                    "  JOIN OrdensCardapio oc " +
                    "    ON o.id = oc.cardapio.id " +
                    "  JOIN oc.cardapio c " +
                    " GROUP BY c.nome " +
                    " ORDER BY SUM(oc.quantidade) DESC";

            return this.entityManager
                    .createQuery(jpql, ItensPrincipaisVo.class)
                    .getResultList();

        } catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public Ordem joinFetchCliente(final Integer id) {

        String jpql = " " +
                "SELECT o " +
                "  FROM Ordem o " +
                "  JOIN FETCH o.cliente " +
                " WHERE o.id = :id";

        return this.entityManager
                .createQuery(jpql, Ordem.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void atualizar(final Ordem ordem) {

        this.entityManager.merge(ordem);
    }

    public void excluir(final Ordem ordem) {

        this.entityManager.remove(ordem);
    }

}
