package br.com.rasmoo.restaurante.entity;

import javax.persistence.*;

@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    public Categoria() {

    }

    public Categoria(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", name='" + nome + '\'' +
                '}';
    }
}
