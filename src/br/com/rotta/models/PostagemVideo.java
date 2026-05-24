package br.com.rotta.models;

import br.com.rotta.enums.StatusMidia;

public class PostagemVideo extends Midia {

    // ATRIBUTOS
    private int duracaoSegundos;
    private String qualidade;

    // CONSTRUTOR
    public PostagemVideo(int id, String urlArquivo,
                         String descricao, int usuarioId,
                         int duracaoSegundos, String qualidade) {

        super(id, urlArquivo, descricao, usuarioId);

        this.duracaoSegundos = duracaoSegundos;
        this.qualidade = qualidade;
    }

    // METODOS
    @Override
    public void enviar() {

        System.out.println("Iniciando o envio de video...");
        System.out.println("Arquivo: " + getUrlArquivo());

        validarDuracao();

        setStatus(StatusMidia.ENVIADO);

        System.out.println("Vídeo enviado com sucesso!");
    }

    public void validarDuracao() {

        if (duracaoSegundos <= 60) {

            System.out.println("Duração válida.");

        } else {

            System.out.println("Vídeo acima do limite.");
        }
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
