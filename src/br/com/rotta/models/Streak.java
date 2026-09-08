package br.com.rotta.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class Streak {

    private int id;
    private int diasConsecutivos;
    private LocalDateTime dataUltimaAtividade;

    // Cria o controle de sequência de atividades do usuário.
    public Streak(int id, int diasConsecutivos, LocalDateTime dataUltimaAtividade) {
        this.id = id;
        this.diasConsecutivos = diasConsecutivos;
        this.dataUltimaAtividade = dataUltimaAtividade;
    }

    // Atualiza a sequência somente quando o usuário conclui um desafio.
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