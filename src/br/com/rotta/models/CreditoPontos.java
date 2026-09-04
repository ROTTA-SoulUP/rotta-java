package br.com.rotta.models;

public class CreditoPontos {
    //ATRIBUTOS
    private Pontuacao pontuacao;
    private Carteira carteira;

    //CONSTRUTOR
    public CreditoPontos(Pontuacao pontuacao, Carteira carteira) {
        this.pontuacao = pontuacao;
        this.carteira = carteira;
    }

    //METODO
    public void executar() {
        double pontos = pontuacao.getPontosAtribuidos();
        if (pontos > 0) {
            System.out.println("Creditando pontos na carteira digital...");
            carteira.creditarPontos(pontos);
        } else {
            System.out.println("Nenhum ponto a creditar nesta operação.");
        }
    }

}
