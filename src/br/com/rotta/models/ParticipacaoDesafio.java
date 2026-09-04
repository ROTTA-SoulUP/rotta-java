package br.com.rotta.models;

import br.com.rotta.enums.StatusParticipacao;

import java.time.LocalDateTime;

public class ParticipacaoDesafio {

    //ATRIBUTOS
    private int id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataConclusao;
    private StatusParticipacao status;
    private Usuario usuario;
    private Desafio desafio;

    //CONSTRUTOR
    public ParticipacaoDesafio(int id, Usuario usuario, Desafio desafio) {
        this.id = id;
        this.usuario = usuario;
        this.desafio = desafio;
        this.dataInicio = LocalDateTime.now();
        this.status = StatusParticipacao.EM_ANDAMENTO;
    }

    //MÉTODOS
    public void iniciar() {
        this.status = StatusParticipacao.EM_ANDAMENTO;
        this.dataInicio = LocalDateTime.now();
        System.out.println(usuario.getNome() + " começou o desafio \"" + desafio.getNome() + "\"! Boa sorte!");
    }

    public void concluir() {
        this.status = StatusParticipacao.CONCLUIDA;
        this.dataConclusao = LocalDateTime.now();
        System.out.println("Desafio \"" + desafio.getNome() + "\" concluído por " + usuario.getNome() + "!");
    }

    public void cancelar() {
        this.status = StatusParticipacao.CANCELADA;
        System.out.println("Desafio \"" + desafio.getNome() + "\" foi cancelado.");
    }

    public int getId() { return id; }
    public Usuario getUsuario() { return usuario; }
    public Desafio getDesafio() { return desafio; }
    public StatusParticipacao getStatus() { return status; }
}
