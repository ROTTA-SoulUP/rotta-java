package br.com.rotta.models;

import br.com.rotta.enums.StatusParticipacao;
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

public class ParticipacaoDesafio {

    // ATRIBUTOS
    private int id;
    private LocalDateTime dataInicio;
    private LocalDateTime dataConclusao;
    private StatusParticipacao status;
    private Usuario usuario;
    private Desafio desafio;

    // CONSTRUTOR
    // Cria a participação já com status "em andamento".
    public ParticipacaoDesafio(int id, Usuario usuario, Desafio desafio) {
        this.id = id;
        this.usuario = usuario;
        this.desafio = desafio;
        this.status = StatusParticipacao.EM_ANDAMENTO;
    }

    // MÉTODOS
    // Marca o início da participação, registrando a data e hora.
    public void iniciar() {
        dataInicio = LocalDateTime.now();
        status = StatusParticipacao.EM_ANDAMENTO;
        System.out.println("Desafio iniciado: " + desafio.getNome());
    }

    // Marca a participação como concluída, registrando a data de conclusão.
    public void concluir() {
        dataConclusao = LocalDateTime.now();
        status = StatusParticipacao.CONCLUIDA;
        System.out.println("Participação concluída com sucesso.");
    }

    // Marca a participação como cancelada.
    public void cancelar() {
        status = StatusParticipacao.CANCELADA;
        System.out.println("Participação cancelada.");
    }

    // GETTERS
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