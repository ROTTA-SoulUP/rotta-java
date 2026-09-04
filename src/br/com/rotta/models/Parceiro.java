package br.com.rotta.models;

import br.com.rotta.enums.TipoParceiro;

public class Parceiro {

    //ATRIBUTOS
    private int id;
    private String nome;
    private TipoParceiro tipo;
    private String documento;
    private boolean ativo;

    //CONSTRUTOR
    public Parceiro(int id, String nome, TipoParceiro tipo, String documento, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.tipo = tipo;
        this.documento = documento;
        this.ativo = ativo;
    }

    //MÉTODOS
    public void cadastrar() {
        System.out.println("Parceiro " + nome + "(" + tipo + ") cadastrado com sucesso na Rotta!");
    }

    public void ativar() {
        this.ativo = true;
        System.out.println("Parceiro " + nome + " está ativo e patrocinando desafios.");
    }

    public void desativar() {
        this.ativo = false;
        System.out.println("Parceiro " + nome + " foi desativado.");
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }
}
