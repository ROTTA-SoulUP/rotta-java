package br.com.rotta.models;

import br.com.rotta.enums.ResultadoValidacao;
import java.time.LocalDateTime;

public class ValidacaoIA {

    private int id;
    private int scoreIA;
    private ResultadoValidacao statusValidacao;
    private String observacao;
    private LocalDateTime dataAnalise;
    private int midiaId;

    public ValidacaoIA(int id, int midiaId) {

        this.id = id;
        this.midiaId = midiaId;
        this.dataAnalise = LocalDateTime.now();
    }

    public void analisarMidia(Midia midia) {

        if (midia instanceof PostagemFoto) {

            System.out.println("IA analisando imagem...");

        } else if (midia instanceof PostagemVideo) {

            System.out.println("IA analisando vídeo...");
        }

        System.out.println("Verificando autenticidade...");

        this.scoreIA = (int)(Math.random() * 100);

        if (scoreIA >= 30) {

            this.statusValidacao = ResultadoValidacao.APROVADO;

            this.observacao =
                    "Seu post foi verificado e aceito com sucesso!";

        } else {

            this.statusValidacao = ResultadoValidacao.REJEITADO;

            this.observacao =
                    "Ops... seu post foi invalidado em nossa verificação.";
        }

        System.out.println("Score: " + scoreIA + "/100");
        System.out.println("Resultado: " + statusValidacao);
    }

    public void exibirResultado() {

        System.out.println("\nResultado da Validação:");

        System.out.println("ID: " + id);

        System.out.println("Mídia ID: " + midiaId);

        System.out.println("Score: " + scoreIA);

        System.out.println("Status: " + statusValidacao);
    }

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