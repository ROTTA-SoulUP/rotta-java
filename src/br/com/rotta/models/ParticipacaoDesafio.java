package br.com.rotta.models;

import br.com.rotta.enums.StatusParticipacao;
import java.time.LocalDateTime;

public class ParticipacaoDesafio {
    private int id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataConclusao;
    private StatusParticipacao status;
    private Usuario usuario;
    private Desafio desafio;

    public ParticipacaoDesafio(int id, Usuario usuario, Desafio desafio) {
        this.id = id;
        this.usuario = usuario;
        this.desafio = desafio;
        this.status = StatusParticipacao.EM_ANDAMENTO;
    }

    public void iniciar() {
        dataInicio = LocalDateTime.now();
        status = StatusParticipacao.EM_ANDAMENTO;
        System.out.println("Desafio iniciado: " + desafio.getNome());
    }

    public void concluir() {
        dataConclusao = LocalDateTime.now();
        status = StatusParticipacao.CONCLUIDA;
        System.out.println("Participação concluída com sucesso.");
    }

    public void cancelar() {
        status = StatusParticipacao.CANCELADA;
        System.out.println("Participação cancelada.");
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }

    public StatusParticipacao getStatus() {
        return status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Desafio getDesafio() {
        return desafio;
    }
}