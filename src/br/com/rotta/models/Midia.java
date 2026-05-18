package br.com.rotta.models;

import br.com.rotta.enums.StatusMidia;

import java.time.LocalDateTime;

public abstract class Midia {

    // Atributos
    private int id;
    private String urlArquivo;
    private String descricao;
    private LocalDateTime dataEnvio;
    private StatusMidia status; // Dado do Enum
    private int usuarioId;

    // Construtor
    public Midia(int id, String urlArquivo, String descricao, StatusMidia status, int usuarioId) {
        this.id = id;
        this.urlArquivo = urlArquivo;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
        this.dataEnvio = LocalDateTime.now(); // Pega a data/hora atual
        this.status = StatusMidia.PENDENTE; // Começa o app já como pendente
    }

    // Metodos Abstratos
    public abstract void enviar();
    public abstract void cancelar();

    // Metodo Concreto
    public String consultarStatus() {
        return "Mídia: " + this.id + "\nStatus: " + this.status;
    }

    // Metodo Protegido
    protected void setStatus(StatusMidia status) {
        this.status = status;
    }


    // Getters
    public int getId() {
        return id;
    }

    public String getUrlArquivo() {
        return urlArquivo;
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

    public int getUsuarioId() {
        return usuarioId;
    }

    // Setters --------------------------------------------
    public void setId(int id) {
        this.id = id;
    }

    public void setUrlArquivo(String urlArquivo) {
        this.urlArquivo = urlArquivo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }
}
