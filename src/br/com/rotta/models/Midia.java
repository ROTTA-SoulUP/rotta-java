package br.com.rotta.models;

import br.com.rotta.enums.StatusMidia;
import java.time.LocalDateTime;

public abstract class Midia {

    // ATRIBUTOS
    private int id;
    private String urlArquivo;
    private String descricao;
    private final LocalDateTime dataEnvio;
    private StatusMidia status; // Aqui chamamos o enum
    private int usuarioId;

    // CONSTRUTOR
    public Midia(int id, String urlArquivo, String descricao, int usuarioId) {
        this.id = id;
        this.urlArquivo = urlArquivo;
        this.descricao = descricao;
        this.usuarioId = usuarioId;
        this.dataEnvio = LocalDateTime.now(); // O sistema pega a data/hora atual
        this.status = StatusMidia.PENDENTE; // Começa sempre pendente
    }

    public abstract void enviar();
    public abstract void cancelar();

    public String consultarStatus() {
        return "Mídia: " + this.id + "\nStatus: " + this.status;
    }

    protected void setStatus(StatusMidia status) {
        this.status = status;
    }

    // Getters
    public int getId() { return id; }
    public String getUrlArquivo() { return urlArquivo; }
    public String getDescricao() { return descricao; }
    public LocalDateTime getDataEnvio() { return dataEnvio; }
    public StatusMidia getStatus() { return status; }
    public int getUsuarioId() { return usuarioId; }

    // Setters
    public void setId(int id) { this.id = id; }
    public void setUrlArquivo(String urlArquivo) { this.urlArquivo = urlArquivo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }
}