package br.com.rotta.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Streak {

    //ATRIBUTOS
    private int id;
    private Usuario usuario;
    private int diasConsecutivos;
    private LocalDate dataUltimAtividade;

    //CONSTRUTOR

    public Streak(int id, Usuario usuario) {
        this.id = id;
        this.usuario = usuario;
        this.diasConsecutivos = diasConsecutivos = 0;
    }

    //METODOS
    public void atualizarStreak() {
        LocalDate hoje = LocalDate.now();
        if (dataUltimAtividade != null && dataUltimAtividade.equals(hoje.minusDays(1)))  {
            this.diasConsecutivos++;
            System.out.println("Sequência mantida! Você está há " + diasConsecutivos + " dias seguidos utilizando a Rotta!");
        } else  if (dataUltimAtividade == null || !dataUltimAtividade.equals(hoje)) {
            this.diasConsecutivos = 1;
            System.out.println("Nova sequência iniciada! Sua Capi foi atualizada! (DIA 1) ");
        } else {
            System.out.println("Você já registrou a sua atividade hoje, volte amanhã para manter a sua Capi bonitona!");
        }
        this.dataUltimAtividade = hoje;
    }

    public boolean verificarQuebrarStreak() {
        if (dataUltimAtividade == null)
            return false;
        LocalDate hoje = LocalDate.now();
        boolean quebrou = dataUltimAtividade.isBefore(hoje.minusDays(1));
        if (quebrou) {
            System.out.println("Que pena! Sua sequência de " + diasConsecutivos + " dias foi quebrada. Vamos começar de novo?");
            this.diasConsecutivos = 0;
        }
        return quebrou;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public int getDiasConsecutivos() {
        return diasConsecutivos;
    }
}
