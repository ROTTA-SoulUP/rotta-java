package br.com.rotta.models;

public class Pontuacao extends Movimentacao {

    // ATRIBUTOS
    private int scoreIA;
    private int pontosAtribuidos;
    private String resultado;
    private int midiaId;

    public Pontuacao(int id, int scoreIA, int midiaId, int carteiraId) {
        super(id, calcularValor(scoreIA), carteiraId);
        this.scoreIA = scoreIA;
        this.midiaId = midiaId;
        this.pontosAtribuidos = (int) calcularValor(scoreIA);
        this.resultado = scoreIA >= 40 ? "APROVADO" : "REJEITADO";
    }

    // Metodo estático para calcular antes do super()
    private static double calcularValor(int score) {
        if (score >= 70) {
            return score;
        } else {
            return 0;
        }
    }

    @Override
    public void executar(){
        if (scoreIA >= 40) {
            setStatus("CONCLUÍDO");
            System.out.println("Pontuação creditada: " + pontosAtribuidos + " pontos");
            System.out.println("Score IA: " + scoreIA + "\nResultado: " + resultado);
        }
    }

    @Override
    public void cancelar() {
        setStatus("CANCELADO");
        System.out.println("Pontuação " + getId() + "cancelada.");
    }

    public void calcularPontos() {
        System.out.println("Calculando pontos para score " + scoreIA + "...");
        System.out.println("Pontos atribuídos: " + pontosAtribuidos);
    }

    // GETTERS E SETTERS
    public int getScoreIA() {
        return scoreIA;
    }

    public void setScoreIA(int scoreIA) {
        this.scoreIA = scoreIA;
    }

    public int getPontosAtribuidos() {
        return pontosAtribuidos;
    }

    public void setPontosAtribuidos(int pontosAtribuidos) {
        this.pontosAtribuidos = pontosAtribuidos;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public int getMidiaId() {
        return midiaId;
    }

    public void setMidiaId(int midiaId) {
        this.midiaId = midiaId;
    }
}
