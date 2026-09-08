package br.com.rotta.models;

public class Pontuacao {

    private int id;
    private double pontosAtribuidos;

    // Cria uma pontuação.
    public Pontuacao(int id, double pontosAtribuidos) {
        this.id = id;
        this.pontosAtribuidos = pontosAtribuidos;
    }

    // Retorna a quantidade de pontos atribuídos ao usuário.
    public double calcularPontos() {
        return pontosAtribuidos;
    }

    public int getId() {
        return id;
    }

    public double getPontosAtribuidos() {
        return pontosAtribuidos;
    }
}