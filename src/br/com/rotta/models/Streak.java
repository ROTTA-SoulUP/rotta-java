package br.com.rotta.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Grupo de Desenvolvimento do Projeto.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

public class Streak {

    // ATRIBUTOS
    private int id;
    private int diasConsecutivos;
    private LocalDateTime dataUltimaAtividade;

    // CONSTRUTOR
    // Cria o controle de sequência de atividades do usuário.
    public Streak(int id, int diasConsecutivos, LocalDateTime dataUltimaAtividade) {
        this.id = id;
        this.diasConsecutivos = diasConsecutivos;
        this.dataUltimaAtividade = dataUltimaAtividade;
    }

    // MÉTODOS
    /**
     * Atualiza a sequência somente quando o usuário conclui um desafio.
     * Se a última atividade foi ontem, soma mais um dia à sequência.
     * Se passou mais de um dia sem atividade, a sequência reinicia.
     */
    public void atualizarStreak() {
        LocalDate hoje = LocalDate.now();

        if (dataUltimaAtividade == null) {
            diasConsecutivos = 1;
        } else {
            LocalDate ultimaAtividade = dataUltimaAtividade.toLocalDate();
            long diasDesdeUltimaAtividade = ChronoUnit.DAYS.between(ultimaAtividade, hoje);

            if (diasDesdeUltimaAtividade == 1) {
                diasConsecutivos++;
            } else if (diasDesdeUltimaAtividade > 1) {
                diasConsecutivos = 1;
            }
        }

        dataUltimaAtividade = LocalDateTime.now();

        System.out.println("Streak atualizada! Dias consecutivos: " + diasConsecutivos);
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public int getDiasConsecutivos() {
        return diasConsecutivos;
    }

    public LocalDateTime getDataUltimaAtividade() {
        return dataUltimaAtividade;
    }
}