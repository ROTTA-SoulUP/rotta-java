package br.com.rotta.models;

import br.com.rotta.enums.ResultadoValidacao;
import java.time.LocalDateTime;

public class ValidacaoIA {

    //  ATRIBUTOS
    private int id;
    private int scoreIA;
    private ResultadoValidacao statusValidacao; // enum
    private String observacao;
    private LocalDateTime dataAnalise;
    private int midiaId;

    public ValidacaoIA(int id, int midiaId) {
        this.id = id;
        this.midiaId = midiaId;
        this.dataAnalise = LocalDateTime.now();
    }

    // Analisa a mídia e gera o score
    public void analisarMidia(Midia midia) {
        System.out.println("IA analisando:" + midia.getUrlArquivo());
        System.out.println("Verificando autenticidade...");

        // Simula score da IA (0 a 100)
        this.scoreIA = (int)(Math.random() * 100);

        // Define resultado baseado no score
        if (scoreIA >= 70) {
            this.statusValidacao = ResultadoValidacao.APROVADO;
            this.observacao = "Seu post foi verificado e aceito com sucesso!";
        } else {
            this.statusValidacao = ResultadoValidacao.REJEITADO;
            this.observacao = "Ops... seu post foi invalidado em nossa verificação.";
        }

        System.out.println("Score: " + scoreIA + "/100");
        System.out.println("Resultado: " + statusValidacao);
        System.out.println("Observação: " + observacao);
    }

    public void exibirResultado() {
        System.out.println("Resultado da Validação: \n\nID: " + id + "\nMídia ID: " + midiaId + "\nScore: " + scoreIA + "\nStatus: " + statusValidacao + "\nData: " + dataAnalise);
    }

    // Retorna true se a postagem foi aprovada (total ou parcial)
    public boolean foiAprovado() {
        return statusValidacao == ResultadoValidacao.APROVADO;
    }

    // GETTERS E SETTERS

    public int getId() {
        return id;
    }

    public int getScoreIA() {
        return scoreIA;
    }

    public ResultadoValidacao getStatusValidacao() {
        return statusValidacao;
    }

    public String getObservacao() {
        return observacao;
    }

    public LocalDateTime getDataAnalise() {
        return dataAnalise;
    }

    public int getMidiaId() {
        return midiaId;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScoreIA(int scoreIA) {
        this.scoreIA = scoreIA;
    }

    public void setMidiaId(int midiaId) {
        this.midiaId = midiaId;
    }
}
