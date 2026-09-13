package br.com.rotta.models;

/**
 * Classe que representa a pontuação atribuída ao usuário após a validação de uma ação.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

public class Pontuacao {

    // ATRIBUTOS
    private int id;
    private double pontosAtribuidos;

    // CONSTRUTOR
    /**
     * Cria uma pontuação com a quantidade de pontos atribuídos.
     *
     * @param id identificador da pontuação
     * @param pontosAtribuidos quantidade de pontos atribuídos
     */
    public Pontuacao(int id, double pontosAtribuidos) {
        this.id = id;
        this.pontosAtribuidos = pontosAtribuidos;
    }

    // MÉTODOS
    /**
     * Retorna a quantidade de pontos atribuídos.
     *
     * @return quantidade de pontos
     */
    public double calcularPontos() {
        return pontosAtribuidos;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public double getPontosAtribuidos() {
        return pontosAtribuidos;
    }
}