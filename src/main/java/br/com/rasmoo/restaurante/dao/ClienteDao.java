package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Cliente;
import br.com.rasmoo.restaurante.entity.ClienteId;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.List;

public class ClienteDao {

    private EntityManager entityManager;

    public ClienteDao(EntityManager entityManager) {

        this.entityManager = entityManager;
    }

    public void cadastrar(final Cliente cliente) {

        this.entityManager.persist(cliente);
    }

    public Cliente consultar(final ClienteId id) {

        return this.entityManager.find(Cliente.class, id);
    }

    public List<Cliente> consultarTodos() {

        try {
            String jpql = "SELECT c FROM Cliente c";

            return this.entityManager
                    .createQuery(jpql, Cliente.class)
                    .getResultList();
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public List<Cliente> consultarPorNome(final String nome) {

        try {
            String jpql = " " +
                    "SELECT c " +
                    "  FROM Cliente c " +
                    " WHERE LOWER(c.nome) " +
                    "  LIKE LOWER(:nome)";

            return this.entityManager
                    .createQuery(jpql, Cliente.class)
                    .setParameter("nome", "%" + nome + "%")
                    .getResultList();
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public void atualizar(final Cliente cliente) {

        this.entityManager.merge(cliente);
    }

    public void excluir(final Cliente cliente) {

        this.entityManager.remove(cliente);
    }

}
