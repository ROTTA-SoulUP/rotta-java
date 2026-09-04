package br.com.rotta.models;

import br.com.rotta.enums.ResultadoValidacao;
import java.time.LocalDateTime;

public class ValidacaoIA {

    // ATRIBUTOS
    private int id;
    private double scoreIA;
    private ResultadoValidacao statusValidacao;
    private String observacao;
    private LocalDateTime dataAnalise;
    private Midia midia;

    //CONSTRUTOR
    public ValidacaoIA(int id) {
        this.id = id;
    }

    //MÉTODOS
    public void analisarMidia(Midia midia) {
        this.midia = midia;
        System.out.println("Analisando autenticidade da midia com Inteligência Artificial...");
        this.scoreIA = Math.random() * 100;
        this.dataAnalise = LocalDateTime.now();
        if (this.scoreIA >= 30) {
            this.statusValidacao = ResultadoValidacao.APROVADO;
            this.observacao = "Mídia validada com sucesso.";
        } else {
            this.statusValidacao = ResultadoValidacao.REJEITADO;
            this.observacao = "Não foi possível confirmar a autenticidade da mídia.";
        }
        exibirResultado();
    }

    public void exibirResultado() {
        System.out.println("Score obtido: " + String.format("%.2f", scoreIA) + "/100" +
                            "Resultado: " + statusValidacao +
                            "Observação: " + observacao);
    }

    public boolean foiAprovado() {
        return this.statusValidacao == ResultadoValidacao.APROVADO;
    }

    //GETTERS
    public double getScoreIA() { return scoreIA; }
    public Midia getMidia() { return midia; }
}