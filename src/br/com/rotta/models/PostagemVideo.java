package br.com.rotta.models;

import br.com.rotta.enums.StatusMidia;

public class PostagemVideo extends Midia {

    // ATRIBUTOS
    private int duracaoSegundos;
    private String qualidade;

    // CONSTRUTOR
    public PostagemVideo(int id, String urlArquivo, Usuario usuario, ParticipacaoDesafio participacao, int duracaoSegundos, String qualidade) {
        super(id, urlArquivo, usuario, participacao);
        this.duracaoSegundos = duracaoSegundos;
        this.qualidade = qualidade;
    }

    // MÉTODOS
    @Override
    public void enviar() {
        System.out.println("Enviando vídeo em qualidade " + qualidade + "...");
        validarDuracao();
        setStatus(StatusMidia.ENVIADO);
        System.out.println("Vídeo enviado com sucesso! Aguardando validação da IA.");
    }

    public void validarDuracao() {
        if (duracaoSegundos <= 60) {
            System.out.println("Duração de " + duracaoSegundos + "s dentro do limite permitido.");
        } else {
            System.out.println("Atenção: o video passou do tempo máximo recomendado.");
        }
    }
}