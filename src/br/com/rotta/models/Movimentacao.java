package br.com.rotta.models;

import br.com.rotta.enums.StatusMovimentacao;

import java.time.LocalDateTime;

public abstract class Movimentacao {

    // ATRIBUTOS
    private int id;
    private double valor;
    private LocalDateTime dataMovimentacao;
    private StatusMovimentacao status; //Chama do ENUM StatusMovimento
    private Carteira carteira; //Chama da classe Carteira

    // CONSTRUTOR

    public Movimentacao(int id, double valor, Carteira carteiraId) {
        this.id = id;
        this.valor = valor;
        this.carteira = carteiraId;
        this.dataMovimentacao = LocalDateTime.now();
        this.status = StatusMovimentacao.PENDENTE;
    }

    // METODOS
    public abstract void executar();

    public String consultarStatus() {
        return this.status.toString();
    }

    // GETTERS E SETTERS

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public StatusMovimentacao getStatus() {
        return status;
    }

    protected void setStatus(StatusMovimentacao status) {
        this.status = status;
    }
}