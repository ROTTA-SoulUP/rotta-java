package br.com.rotta.models;

/**
 * Grupo de Desenvolvimento do Projeto.
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
    // Cria uma pontuação.
    public Pontuacao(int id, double pontosAtribuidos) {
        this.id = id;
        this.pontosAtribuidos = pontosAtribuidos;
    }

    // MÉTODOS
    // Retorna a quantidade de pontos atribuídos ao usuário.
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