package br.com.rotta.models;

import br.com.rotta.enums.FormatoMidia;

/**
 * Grupo de Desenvolvimento do Projeto.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

public class Desafio {

    // ATRIBUTOS
    private int id;
    private String nome;
    private FormatoMidia tipoFormato;
    private int tempoMaxVideo;
    private int pontosDesafio;

    // CONSTRUTOR
    public Desafio(int id, String nome, FormatoMidia tipoFormato, int tempoMaxVideo, int pontosDesafio) {
        this.id = id;
        this.nome = nome;
        this.tipoFormato = tipoFormato;
        this.tempoMaxVideo = tempoMaxVideo;
        this.pontosDesafio = pontosDesafio;
    }

    // MÉTODOS
    // Exibe os dados do desafio, mostrando o tempo máximo apenas quando o formato exigido é vídeo.
    public void exibirDesafio() {
        System.out.println("Desafio: " + nome);
        System.out.println("Formato: " + tipoFormato);

        if (tipoFormato == FormatoMidia.VIDEO) {
            System.out.println("Tempo máximo do vídeo: " + tempoMaxVideo + " segundos");
        }

        System.out.println("Pontos: " + pontosDesafio);
    }

    // GETTER
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