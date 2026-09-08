package br.com.rotta.models;

import br.com.rotta.enums.FormatoMidia;

public class Desafio {
    private int id;
    private String nome;
    private FormatoMidia tipoFormato;
    private int tempoMaxVideo;
    private int pontosDesafio;

    public Desafio(int id, String nome, FormatoMidia tipoFormato, int tempoMaxVideo, int pontosDesafio) {
        this.id = id;
        this.nome = nome;
        this.tipoFormato = tipoFormato;
        this.tempoMaxVideo = tempoMaxVideo;
        this.pontosDesafio = pontosDesafio;
    }

    public void exibirDesafio() {
        System.out.println("Desafio: " + nome);
        System.out.println("Formato: " + tipoFormato);

        if (tipoFormato == FormatoMidia.VIDEO) {
            System.out.println("Tempo máximo do vídeo: " + tempoMaxVideo + " segundos");
        }

        System.out.println("Pontos: " + pontosDesafio);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public FormatoMidia getTipoFormato() {
        return tipoFormato;
    }

    public int getTempoMaxVideo() {
        return tempoMaxVideo;
    }

    public int getPontosDesafio() {
        return pontosDesafio;
    }
}