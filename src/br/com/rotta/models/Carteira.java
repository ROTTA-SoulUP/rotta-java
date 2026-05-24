package br.com.rotta.models;

import java.time.LocalDateTime;

public class Carteira {

    // ATRIBUTOS
    private int id;
    private double saldoPontos;
    private LocalDateTime ultimaAtualizacao;
    private int usuarioId;

    // CONSTRUTOR
    public Carteira(int id, int usuarioId) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.saldoPontos = 0; // Já começa zerada a carteira
        this.ultimaAtualizacao = LocalDateTime.now();
    }

    // METODOS
    public void creditarPontos(double pontos) {
        this.saldoPontos += pontos;
        this.ultimaAtualizacao = LocalDateTime.now();
        System.out.println("Pontos creditados: " + pontos);
        System.out.println("Saldo Atual: " + saldoPontos + " pontos.");
    }

    public void debitarPontos(double pontos) {
        if (verificarLimite(pontos)) {
            this.saldoPontos -= pontos;
            this.ultimaAtualizacao = LocalDateTime.now();
            System.out.println("Pontos debitados: " + pontos);
            System.out.println("Saldo Atual: " + saldoPontos + " pontos.");
        } else {
            System.out.println("Saldo insuficiente.");
        }
    }

    public String consultarSaldo() {
        return "Saldo: " + saldoPontos + " pontos";
    }

    // Verifica se tem saldo suficiente para uma operação
    public boolean verificarLimite(double pontos) {
    return saldoPontos >= pontos;
    }

    // GETTERS E SETTERS

    public int getId() {
        return id;
    }

    public double getSaldoPontos() {
        return saldoPontos;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setSaldoPontos(double saldoPontos) {
        this.saldoPontos = saldoPontos;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
