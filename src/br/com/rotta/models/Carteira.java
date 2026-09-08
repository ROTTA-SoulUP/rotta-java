package br.com.rotta.models;

import java.time.LocalDateTime;

public class Carteira {

    private int id;
    private double saldoPontos;
    private LocalDateTime ultimaAtualizacao;
    private String tipoUso;

    // Cria a carteira com o seu saldo inicial e define quando ela foi atualizada.
    public Carteira(int id, double saldoPontos,
                    LocalDateTime ultimaAtualizacao, String tipoUso) {

        this.id = id;
        this.saldoPontos = saldoPontos;
        this.ultimaAtualizacao = ultimaAtualizacao;
        this.tipoUso = tipoUso;

        // Caso nenhuma data seja informada, usamos a data e hora atuais.
        if (this.ultimaAtualizacao == null) {
            this.ultimaAtualizacao = LocalDateTime.now();
        }
    }

    // Adiciona pontos ao saldo da carteira.
    public void creditarPontos(double pontos) {
        saldoPontos += pontos;
        ultimaAtualizacao = LocalDateTime.now();

        System.out.println("Foram creditados " + pontos
                + " pontos! Saldo atual: " + saldoPontos);
    }

    // Retira pontos da carteira somente se houver saldo suficiente.
    public void debitarPontos(double pontos) {
        if (verificarSaldo(pontos)) {
            saldoPontos -= pontos;
            ultimaAtualizacao = LocalDateTime.now();

            System.out.println("Foram debitados " + pontos
                    + " pontos! Saldo atual: " + saldoPontos);
        } else {
            System.out.println("Saldo insuficiente para essa operação.");
        }
    }

    // Consulta o saldo atual da carteira.
    public double consultarSaldo() {
        return saldoPontos;
    }

    // Verifica se a carteira possui pontos suficientes para uma operação.
    public boolean verificarSaldo(double pontos) {
        return saldoPontos >= pontos;
    }

    public int getId() {
        return id;
    }

    public double getSaldoPontos() {
        return saldoPontos;
    }

    public LocalDateTime getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public String getTipoUso() {
        return tipoUso;
    }
}