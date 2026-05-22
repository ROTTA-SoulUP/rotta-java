package br.com.rotta.models;

public class Pontuacao extends Movimentacao {

    // ATRIBUTOS
    private int scoreIA;
    private int pontosAtribuidos;
    private String resultado;
    private int midiaId;

    // Construtor recebe pontosDoDesafio
    public Pontuacao(int id, int scoreIA, int midiaId,
                     int carteiraId, int pontosDoDesafio) {
        super(id, pontosDoDesafio, carteiraId);
        this.scoreIA = scoreIA;
        this.midiaId = midiaId;
        // Pontos só são atribuídos se score >= 70
        this.pontosAtribuidos = scoreIA >= 70 ? pontosDoDesafio : 0;
        this.resultado = scoreIA >= 70 ? "APROVADO" : "REJEITADO";
    }

    @Override
    public void executar() {
        if (scoreIA >= 70) {
            setStatus("CONCLUÍDO");
            System.out.println("Pontuação creditada: " + pontosAtribuidos + " pontos");
            System.out.println("Score IA: " + scoreIA + "\nResultado: " + resultado);
        } else {
            setStatus("REJEITADO");
            System.out.println("Score " + scoreIA + " abaixo do mínimo. Sem pontos.");
        }
    }

    @Override
    public void cancelar() {
        setStatus("CANCELADO");
        System.out.println("Pontuação " + getId() + " cancelada.");
    }

    public void calcularPontos() {
        System.out.println("Calculando pontos para score " + scoreIA + "...");
        System.out.println("Pontos atribuídos: " + pontosAtribuidos);
    }

    // GETTERS E SETTERS
    public int getScoreIA() { return scoreIA; }
    public int getPontosAtribuidos() { return pontosAtribuidos; }
    public String getResultado() { return resultado; }
    public int getMidiaId() { return midiaId; }
    public void setScoreIA(int scoreIA) { this.scoreIA = scoreIA; }
    public void setPontosAtribuidos(int p) { this.pontosAtribuidos = p; }
    public void setResultado(String resultado) { this.resultado = resultado; }
    public void setMidiaId(int midiaId) { this.midiaId = midiaId; }
}