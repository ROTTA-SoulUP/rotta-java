package br.com.rotta.models;

public class Pontuacao {

    //ATRIBUTOS
    private int id;
    private double pontosAtribuidos;
    private ValidacaoIA validacao;

    //CONSTRUTOR
    public Pontuacao(int id, ValidacaoIA validacao) {
        this.id = id;
        this.validacao = validacao;
    }

    //MÉTODOS
    public void calcularPontos() {
        if (validacao.foiAprovado()) {
            int pontosDoDesafio = validacao.getMidia().getParticipacao().getDesafio().getPontosDesafio();
            this.pontosAtribuidos = pontosDoDesafio;
            System.out.println("Validação aprovada! Você ganhou " + pontosAtribuidos + " pontos.");
        } else {
            this.pontosAtribuidos = 0;
            System.out.println("Como a validação foi rejeitada, nenhum ponto foi atribuído dessa vez.");
        }
    }

    public void executar() {
        calcularPontos();
    }

    //GETTER
    public double getPontosAtribuidos() { return pontosAtribuidos; }
}