package br.com.rotta.models;

import br.com.rotta.enums.StatusMovimentacao;

import java.time.LocalDateTime;

public abstract class Movimentacao {

    private int id;
    private double valor;
    private LocalDateTime dataMovimentacao;
    private StatusMovimentacao status;

    // Cria uma movimentação com status pendente.
    public Movimentacao(int id, double valor) {
        this.id = id;
        this.valor = valor;
        this.dataMovimentacao = LocalDateTime.now();
        this.status = StatusMovimentacao.PENDENTE;
    }

    // Cada tipo de movimentação define sua própria execução.
    public abstract void executar();

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