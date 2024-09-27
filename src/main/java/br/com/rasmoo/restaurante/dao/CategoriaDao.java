package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Categoria;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

public class CategoriaDao {

    private EntityManager entityManager;

    public CategoriaDao(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void cadastrar(final Categoria categoria) {

        this.entityManager.persist(categoria);
    }

    public Categoria consultar(final Integer id) {

        return this.entityManager.find(Categoria.class, id);
    }

    public List<Categoria> consultarTodos() {

        try {
            String jpql = "SELECT c FROM Categoria c";

            return this.entityManager
                    .createQuery(jpql, Categoria.class)
                    .getResultList();
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public void atualizar(final Categoria categoria) {

        this.entityManager.merge(categoria);
    }

    public void excluir(final Categoria categoria) {

        this.entityManager.remove(categoria);
    }

}
