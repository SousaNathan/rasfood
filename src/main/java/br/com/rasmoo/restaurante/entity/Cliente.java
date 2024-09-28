package br.com.rasmoo.restaurante.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    @EmbeddedId
    private ClienteId clienteId;

    private String nome;

    @Embedded
    private Contato contato;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    public Cliente() {

    }

    public Cliente(String cpf,String email, String nome) {
        this.clienteId = new ClienteId(cpf, email);
        this.nome = nome;
    }

    public void addEndereco(Endereco endereco) {
        endereco.setCliente(this);
        this.enderecos.add(endereco);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return clienteId.getCpf();
    }

    public void setCpf(String cpf) {
        this.clienteId.setCpf(cpf);
    }

    public String getEmail() {
        return clienteId.getEmail();
    }

    public void setEmail(String email) {
        this.clienteId.setEmail(email);
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "cpf='" + clienteId.getCpf() + '\'' +
                ", nome='" + nome + '\'' +
                ", contato=" + contato +
                ", enderecos=" + enderecos +
                '}';
    }
}
