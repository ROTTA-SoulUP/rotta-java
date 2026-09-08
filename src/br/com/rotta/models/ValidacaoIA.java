package br.com.rotta.models;

import br.com.rotta.enums.FormatoMidia;
import br.com.rotta.enums.ResultadoValidacao;

public class ValidacaoIA {

    private int id;
    private double scoreIA;
    private ResultadoValidacao resultado;
    private String observacao;

    public ValidacaoIA(int id, double scoreIA, ResultadoValidacao resultado, String observacao) {
        this.id = id;
        this.scoreIA = scoreIA;
        this.resultado = resultado;
        this.observacao = observacao;
    }

    public void analisarMidia(Midia midia, Desafio desafio) {
        if (midia == null || desafio == null) {
            scoreIA = 0.0;
            resultado = ResultadoValidacao.REPROVADO;
            observacao = "Não foi possível analisar a mídia.";
            return;
        }

        if (desafio.getTipoFormato() == FormatoMidia.VIDEO && !(midia instanceof PostagemVideo)) {
            scoreIA = 0.10;
            resultado = ResultadoValidacao.REPROVADO;
            observacao = "O desafio exige um vídeo.";
            return;
        }

        if (desafio.getTipoFormato() == FormatoMidia.FOTO && !(midia instanceof PostagemFoto)) {
            scoreIA = 0.10;
            resultado = ResultadoValidacao.REPROVADO;
            observacao = "O desafio exige uma foto.";
            return;
        }

        scoreIA = 0.95;
        resultado = ResultadoValidacao.APROVADO;
        observacao = "Mídia aprovada pela IA.";
    }

    public void exibirResultado() {
        System.out.println("Pontuação da IA: " + scoreIA + " / 1.00");
        System.out.println("Resultado: " + resultado);
    }

    public boolean foiAprovado() {
        return resultado == ResultadoValidacao.APROVADO;
    }

    public int getId() {
        return id;
    }

    public double getScoreIA() {
        return scoreIA;
    }

    public ResultadoValidacao getResultado() {
        return resultado;
    }

    public String getObservacao() {
        return observacao;
    }
}