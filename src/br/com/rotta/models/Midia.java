package br.com.rotta.models;

import br.com.rotta.enums.OrigemEnvio;
import br.com.rotta.enums.StatusMidia;
import java.time.LocalDateTime;

public abstract class Midia {

    // ATRIBUTOS
    private int id;
    private String urlArquivo;
    private String descricao;
    private LocalDateTime dataEnvio;
    private StatusMidia status;
    private OrigemEnvio origemEnvio;
    private LocalDateTime dataSincronizacao;
    private Usuario usuario;
    private ParticipacaoDesafio participacao;

    // CONSTRUTOR
    public Midia(int id, String urlArquivo, Usuario usuario, ParticipacaoDesafio participacao) {
        this.id = id;
        this.urlArquivo = urlArquivo;
        this.usuario = usuario;
        this.participacao = participacao;
        this.status = StatusMidia.PENDENTE;
        this.origemEnvio = OrigemEnvio.MOBILE;
        this.dataEnvio = LocalDateTime.now();
    }

    // MÉTODOS
    public abstract void enviar();

    public String consultarStatus() {
        return this.status.toString();
    }

    public void sincronizarMidia() {
        this.status = StatusMidia.ENVIADO;
        this.dataSincronizacao = LocalDateTime.now();
        System.out.println("Mídia sincronizada com sucesso! Agora sim ela será analisada.");
    }

    // GETTERS E SETTERS
    public int getId() {
        return id;
    }

    public String getUrlArquivo() {
        return urlArquivo;
    }

    public StatusMidia getStatus() {
        return status;
    }

    public void setStatus(StatusMidia status) {
        this.status = status;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public ParticipacaoDesafio getParticipacao() {
        return participacao;
    }
}