package br.com.rotta.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * Classe que controla a sequência de dias consecutivos em que o usuário conclui desafios.
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
    /**
     * Cria o controle de sequência de atividades do usuário.
     *
     * @param id identificador da streak
     * @param diasConsecutivos quantidade inicial de dias consecutivos
     * @param dataUltimaAtividade data e hora da última atividade
     */
    public Streak(int id, int diasConsecutivos, LocalDateTime dataUltimaAtividade) {
        this.id = id;
        this.diasConsecutivos = diasConsecutivos;
        this.dataUltimaAtividade = dataUltimaAtividade;
    }

    // MÉTODOS
    /**
     * Atualiza a sequência de dias conforme a última atividade concluída.
     */
    public void atualizarStreak() {
        LocalDate hoje = LocalDate.now();

        if (dataUltimaAtividade == null) {
            diasConsecutivos = 1;
        } else {
            LocalDate ultimaAtividade = dataUltimaAtividade.toLocalDate();
            long diasDesdeUltimaAtividade = ChronoUnit.DAYS.between(ultimaAtividade, hoje); //Esse ChronoUnit.DAYS subtrai a data de ultimaAtividade da data de "hoje" e retorna o número de dias que se passaram entre elas.

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