package br.com.rotta.models;

import br.com.rotta.enums.StatusMidia;

public class PostagemVideo extends Midia {

    private int duracaoSegundos;
    private String qualidade; // Hd, 4k...

    public PostagemVideo(int id, String urlArquivo, String descricao, int usuarioId, int duracaoSegundos, String qualidade) {
        super(id, urlArquivo, descricao, usuarioId);
        this.duracaoSegundos = duracaoSegundos;
        this.qualidade = qualidade;
    }

    @Override
    public void enviar() {
        System.out.println("Iniciando o envio de video...");
        System.out.println("Arquivo: " + getUrlArquivo());
        System.out.println("Duração: " + duracaoSegundos + "s | Qualidade: " + qualidade);
        validarDuracao();
        extrairFrame();
        setStatus(StatusMidia.ENVIADO);
        System.out.println("Vídeo enviado com sucesso!");
    }

    @Override
    public void cancelar() {
        setStatus(StatusMidia.CANCELADO);
        System.out.println("Enviado do vídeo " + getId() + " cancelado.");
    }

    public void validarDuracao() {
        if (duracaoSegundos <= 30) {
            System.out.println("Duração de " + duracaoSegundos + "s válida.");
        } else {
            System.out.println("Vídeo longo. Máximo: 30 segundos.");
        }
    }

    public void extrairFrame() {
        System.out.println("Extraindo frame para análise da IA...");
    }


    // GETTERS E SETTERS
    public int getDuracaoSegundos() {
        return duracaoSegundos;
    }

    public void setDuracaoSegundos(int duracaoSegundos) {
        this.duracaoSegundos = duracaoSegundos;
    }

    public String getQualidade() {
        return qualidade;
    }

    public void setQualidade(String qualidade) {
        this.qualidade = qualidade;
    }
}
