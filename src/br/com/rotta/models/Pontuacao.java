package br.com.rotta.models;

public class Pontuacao extends Movimentacao {

    // ATRIBUTOS
    private int scoreIA;
    private int pontosAtribuidos;
    private String resultado;
    private int midiaId;

    public Pontuacao(int id, int scoreIA, int midiaId,
                     int carteiraId, int pontosDoDesafio) {

        super(id, pontosDoDesafio, carteiraId);

        this.scoreIA = scoreIA;
        this.midiaId = midiaId;

        this.pontosAtribuidos =
                scoreIA >= 30 ? pontosDoDesafio : 0;

        this.resultado =
                scoreIA >= 30 ? "APROVADO" : "REJEITADO";
    }

    @Override
    public void executar() {

        if (scoreIA >= 30) {

            setStatus("CONCLUÍDO");

            System.out.println("Pontuação creditada com sucesso!");

        } else {

            setStatus("REJEITADO");

            System.out.println("Pontuação rejeitada.");
        }
    }

    @Override
    public void cancelar() {
        setStatus("CANCELADO");
        System.out.println("Pontuação " + getId() + " cancelada.");
    }

    public void calcularPontos() {
        System.out.println("Pontuação processada.");
    }

    // GETTERS E SETTERS
    public int getScoreIA() { return scoreIA; }
    public int getPontosAtribuidos() { return pontosAtribuidos; }
    public String getResultado() { return resultado; }
    public int getMidiaId() { return midiaId; }

    public void setScoreIA(int scoreIA) { this.scoreIA = scoreIA; }

    public void setPontosAtribuidos(int p) {
        this.pontosAtribuidos = p;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public void setMidiaId(int midiaId) {
        this.midiaId = midiaId;
    }
}