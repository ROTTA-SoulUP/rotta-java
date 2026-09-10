package br.com.rotta.models;

import br.com.rotta.enums.StatusMidia;

import java.time.LocalDateTime;

/**
 * Grupo de Desenvolvimento do Projeto.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

public abstract class Midia {

    // ATRIBUTOS
    private int id;
    private String nomeArquivo;
    private String descricao;
    private LocalDateTime dataEnvio;
    private StatusMidia status;
    private LocalDateTime dataSincronizacao;
    private Usuario usuario;
    private ParticipacaoDesafio participacao;

    // CONSTRUTOR
    // Cria a mídia já com status pendente, antes do envio.
    public Midia(int id, String nomeArquivo, Usuario usuario,
                 ParticipacaoDesafio participacao) {
        this.id = id;
        this.nomeArquivo = nomeArquivo;
        this.usuario = usuario;
        this.participacao = participacao;
        this.status = StatusMidia.PENDENTE;
    }

    // MÉTODOS
    // Registra o momento do envio e atualiza o status da mídia.
    public void enviar() {
        dataEnvio = LocalDateTime.now();
        status = StatusMidia.ENVIADO;
    }

    // GETTERS
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