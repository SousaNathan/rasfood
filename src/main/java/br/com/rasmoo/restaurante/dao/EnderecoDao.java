package br.com.rasmoo.restaurante.dao;

import br.com.rasmoo.restaurante.entity.Endereco;
import br.com.rasmoo.restaurante.vo.ClienteVo;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    public List<ClienteVo> consultarClientes(final String estado, String cidade, String rua) {

        try {
            String jpql = " " +
                    "SELECT new br.com.rasmoo.restaurante.vo.ClienteVo(e.cliente.cpf, e.cliente.nome) " +
                    "  FROM Endereco e " +
                    " WHERE 1=1 ";

            if (Objects.nonNull(estado)) {
                jpql = jpql.concat(" AND LOWER(e.estado) = LOWER(:estado) ");
            }

            if (Objects.nonNull(cidade)) {
                jpql = jpql.concat(" AND LOWER(e.cidade) = LOWER(:cidade) ");
            }

            if (Objects.nonNull(rua)) {
                jpql = jpql.concat(" AND LOWER(e.rua) = LOWER(:rua) ");
            }

            TypedQuery typedQuery = this.entityManager.createQuery(jpql, ClienteVo.class);

            if (Objects.nonNull(estado)) {
                typedQuery.setParameter("estado", estado);
            }

            if (Objects.nonNull(cidade)) {
                typedQuery.setParameter("cidade", cidade);
            }

            if (Objects.nonNull(rua)) {
                typedQuery.setParameter("rua", rua);
            }

            return typedQuery.getResultList();
        }
        catch (Exception e) {
            return Collections.emptyList();
        }
    }

    public List<ClienteVo> consultarClientesCriteria(final String estado, String cidade, String rua) {

        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ClienteVo> criteriaQuery = builder.createQuery(ClienteVo.class);
        Root<Endereco> root = criteriaQuery.from(Endereco.class);

        criteriaQuery.multiselect(
                root.get("cliente").get("cpf"),
                root.get("cliente").get("nome"));

        Predicate predicate = builder.and();

        if (Objects.nonNull(estado)) {
            predicate = builder.and(predicate, builder.equal(root.get("estado"), estado));
        }

        if (Objects.nonNull(cidade)) {
            predicate = builder.and(predicate, builder.equal(root.get("cidade"), cidade));
        }

        if (Objects.nonNull(rua)) {
            predicate = builder.and(predicate, builder.equal(root.get("rua"), rua));
        }

        criteriaQuery.where(predicate);

        return entityManager
                .createQuery(criteriaQuery)
                .getResultList();
    }


    public void atualizar(final Endereco endereco) {

        this.entityManager.merge(endereco);
    }

    public void excluir(final Endereco endereco) {

        this.entityManager.remove(endereco);
    }

}
