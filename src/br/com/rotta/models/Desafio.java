package br.com.rotta.models;

import br.com.rotta.enums.*;

public class Desafio {
    private int id;
    private String nome;
    private FormatoMidia tipoFormato;
    private int tempoMaxVideo;
    private int pontosDesafio;
    private boolean ativo;
    private Parceiro parceiro;

    //CONSTRUTOR

    public Desafio(int id, String nome, FormatoMidia tipoFormato, int pontosDesafio, Parceiro parceiro, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.tipoFormato = tipoFormato;
        this.pontosDesafio = pontosDesafio;
        this.parceiro = parceiro;
        this.ativo = true;
    }

    //MÉTODOS
    public void exibirDesafio() {
        System.out.println("Desafio: " + nome);
        System.out.println("Formato exigido: " + tipoFormato);
        System.out.println("Recompensa: " + pontosDesafio + " pontos");
        System.out.println("Patrocinado por: " + parceiro.getNome());
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getPontosDesafio() {
        return pontosDesafio;
    }

    public FormatoMidia getTipoFormato() {
        return tipoFormato;
    }
}
