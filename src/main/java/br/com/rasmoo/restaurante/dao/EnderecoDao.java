package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.entity.Endereco;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

public class EnderecoDao {

    private EntityManager entityManager;

    public EnderecoDao(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void cadastrar(final Endereco endereco) {

        this.entityManager.persist(endereco);
    }

    public Endereco consultar(final Integer id) {

        return this.entityManager.find(Endereco.class, id);
    }

    public List<Endereco> consultarTodos() {

        try {
            String jpql = "SELECT e FROM Endereco e";

            return this.entityManager
                    .createQuery(jpql, Endereco.class)
                    .getResultList();
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public void atualizar(final Endereco endereco) {

        this.entityManager.merge(endereco);
    }

    public void excluir(final Endereco endereco) {

        this.entityManager.remove(endereco);
    }

}
