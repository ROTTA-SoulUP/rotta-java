package br.com.rotta.models;

import java.time.LocalDateTime;

/**
 * Classe que representa a carteira de pontos do usuário e controla créditos, débitos e consultas de saldo.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

public class Carteira {

    // ATRIBUTOS
    private int id;
    private double saldoPontos;
    private LocalDateTime ultimaAtualizacao;
    private String tipoUso;

    // CONSTRUTOR
    /**
     * Cria uma carteira com saldo, data de atualização e tipo de uso informados.
     *
     * @param id identificador da carteira
     * @param saldoPontos saldo inicial de pontos
     * @param ultimaAtualizacao data e hora da última atualização
     * @param tipoUso finalidade de uso da carteira
     */
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

    // MÉTODOS
    /**
     * Adiciona pontos ao saldo da carteira e atualiza a data da última alteração.
     *
     * @param pontos quantidade de pontos que será adicionada
     */
    public void creditarPontos(double pontos) {
        saldoPontos += pontos;
        ultimaAtualizacao = LocalDateTime.now();

        System.out.println("Foram creditados " + pontos
                + " pontos! Saldo atual: " + saldoPontos);
    }

    /**
     * Retira pontos da carteira quando existe saldo suficiente.
     *
     * @param pontos quantidade de pontos que será retirada
     */
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

    /**
     * Consulta o saldo atual da carteira.
     *
     * @return saldo atual de pontos
     */
    public double consultarSaldo() {
        return saldoPontos;
    }

    /**
     * Verifica se a carteira possui pontos suficientes para uma operação.
     *
     * @param pontos quantidade de pontos necessária
     * @return true se houver saldo suficiente; caso contrário, false
     */
    public boolean verificarSaldo(double pontos) {
        return saldoPontos >= pontos;
    }

    // GETTERS
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