package br.com.rotta.models;

import br.com.rotta.enums.StatusMovimentacao;

import java.time.LocalDateTime;

/**
 * Classe abstrata que representa uma movimentação de pontos e define a estrutura básica para sua execução.
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
    /**
     * Cria uma movimentação com valor, data atual e status pendente.
     *
     * @param id identificador da movimentação
     * @param valor valor da movimentação
     */
    public Movimentacao(int id, double valor) {
        this.id = id;
        this.valor = valor;
        this.dataMovimentacao = LocalDateTime.now();
        this.status = StatusMovimentacao.PENDENTE;
    }

    // MÉTODOS
    /**
     * Executa a movimentação de acordo com a sua implementação na classe filha.
     */
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