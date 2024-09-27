package br.com.rasmoo.restaurante.entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ordens")
public class Ordem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "valor_total")
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column(name = "data_de_criacao")
    private LocalDateTime dataDeCriacao = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    private Cliente cliente;

    /*
    * ALL = Realiza todas as operações em cascata
    * DETACH = Operações detach executada no pai e no dilho
    * MERGE = Salva pai e filho, podendo já haver a entidade gerenciada
    * PERSIST = Atualiza a entidade com operações do banco
    * REMOVE = Propaga remocao entre pai e filho
    * */

    @OneToMany(mappedBy = "ordem", cascade = CascadeType.ALL)
//    @JoinTable( USADO QUANDO NÃO A UMA ENTIDADE PROPRIA MANY TO MANY
//            name = "ordens_cardapio",
//            joinColumns = @JoinColumn(name = "ordens_id"),
//            inverseJoinColumns = @JoinColumn(name = "cardapio_id")
//    )
    private List<OrdensCardapio> ordensCardapios = new ArrayList<>();

    public Ordem() {

    }

    public Ordem(Cliente cliente) {
        this.cliente = cliente;
    }

    public void addOrdensCardapio(OrdensCardapio ordensCardapio) {
        ordensCardapio.setOrdem(this);
        this.ordensCardapios.add(ordensCardapio);

        this.valorTotal = valorTotal
                .add(ordensCardapio.getValorDeRegistro()
                .multiply(BigDecimal.valueOf(ordensCardapio.getQuantidade())));
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDateTime dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<OrdensCardapio> getOrdensCardapios() {
        return ordensCardapios;
    }

    public void setOrdensCardapios(List<OrdensCardapio> ordensCardapios) {
        this.ordensCardapios = ordensCardapios;
    }

    @Override
    public String toString() {
        return "Ordem{" +
                "id=" + id +
                ", valorTotal=" + valorTotal +
                ", dataDeCriacao=" + dataDeCriacao +
                ", cliente=" + cliente +
                ", ordensCardapios=" + ordensCardapios +
                '}';
    }
}
