package br.com.rotta.models;

import br.com.rotta.enums.StatusMidia;

import java.time.LocalDateTime;

public abstract class Midia {
    private int id;
    private String nomeArquivo;
    private String descricao;
    private LocalDateTime dataEnvio;
    private StatusMidia status;
    private LocalDateTime dataSincronizacao;
    private Usuario usuario;
    private ParticipacaoDesafio participacao;

    public Midia(int id, String nomeArquivo, Usuario usuario,
                 ParticipacaoDesafio participacao) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.usuario = usuario;
        this.participacao = participacao;
        this.status = StatusMidia.PENDENTE;
    }

    public void enviar() {
        dataEnvio = LocalDateTime.now();
        status = StatusMidia.ENVIADO;
    }

    public int getId() {
        return id;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public String getDescricao() {
        return descricao;
    }

    public LocalDateTime getDataEnvio() {
        return dataEnvio;
    }

    public StatusMidia getStatus() {
        return status;
    }

    public LocalDateTime getDataSincronizacao() {
        return dataSincronizacao;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ParticipacaoDesafio getParticipacao() {
        return participacao;
    }
}