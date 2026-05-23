package br.com.rotta.models;

import java.time.LocalDateTime;

public abstract class Movimentacao {

    // ATRIBUTOS
    private int id;
    private double valor;
    private LocalDateTime dataMovimentacao;
    private String status;
    private int carteiraId;

    // CONSTRUTOR
    public Movimentacao(int id, double valor, int carteiraId) {

        this.id = id;
        this.valor = valor;
        this.carteiraId = carteiraId;
        this.dataMovimentacao = LocalDateTime.now();
        this.status = "PENDENTE";
    }

    // MÉTODOS ABSTRATOS
    public abstract void executar();
    public abstract void cancelar();

    public String consultarStatus() {
        return "Status: " + status;
    }

    protected void setStatus(String status) {
        this.status = status;
    }

    // GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getDataMovimentacao() {
        return dataMovimentacao;
    }

    public String getStatus() {
        return status;
    }

    public int getCarteiraId() {
        return carteiraId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setCarteiraId(int carteiraId) {
        this.carteiraId = carteiraId;
    }
}