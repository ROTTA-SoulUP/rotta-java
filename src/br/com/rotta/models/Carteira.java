package br.com.rotta.models;

import java.time.LocalDateTime;

public class Carteira {

    // ATRIBUTOS
    private int id;
    private double saldoPontos;
    private LocalDateTime ultimaAtualizacao;
    private Usuario usuario;
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
            System.out.println("Saldo insuficiente para essa operação. \nVocê tem " + " pontos.");
        }
    }

    public double consultarSaldo() {
        System.out.println("Seu saldo atual eh de " + saldoPontos + " pontos! (Exclusivos para transporte publico)");
        return this.saldoPontos;
    }

    // Verifica se tem saldo suficiente para uma operação
    public boolean verificarLimite(double pontos) {
        return this.saldoPontos >= pontos;
    }

    public double getSaldoPontos() {
        return saldoPontos;
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public int getUsuarioId() {
        return usuarioId;
    }
}