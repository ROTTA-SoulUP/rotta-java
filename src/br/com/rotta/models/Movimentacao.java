package br.com.rotta.models;

import br.com.rotta.enums.StatusMovimentacao;

import java.time.LocalDateTime;

/**
 * Grupo de Desenvolvimento do Projeto.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

public abstract class Movimentacao {

    // ATRIBUTOS
    private int id;
    private double valor;
    private LocalDateTime dataMovimentacao;
    private StatusMovimentacao status;

    // CONSTRUTOR
    // Cria uma movimentação com status pendente.
    public Movimentacao(int id, double valor) {
        this.id = id;
        this.valor = valor;
        this.dataMovimentacao = LocalDateTime.now();
        this.status = StatusMovimentacao.PENDENTE;
    }

    // MÉTODOS
    // Cada tipo de movimentação define sua própria execução.
    public abstract void executar();

    // GETTERS E SETTER
    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public StatusMovimentacao getStatus() {
        return status;
    }

    protected void setStatus(StatusMovimentacao status) {
        this.status = status;
    }
}