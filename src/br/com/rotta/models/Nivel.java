package br.com.rotta.models;

public class Nivel {

    // ATRIBUTOS
    private int id;
    private String nome;
    private int diasStreakMinimo;

    //CONSTRUTOR
    public Nivel(int id, String nome, int diasStreakMinimo) {
        this.id = id;
        this.nome = nome;
        this.diasStreakMinimo = diasStreakMinimo;
    }

    //METODO
    public Nivel verificarNivelAtual(int diasConsecutivos) {
        if (diasConsecutivos >= this.diasStreakMinimo) {
            System.out.println("Parabéns! Voce atingiu o nível " + nome + " com " + diasConsecutivos + " dias de streak!");
            return this;
        }
        System.out.println("Você está quase alcançando o próximo nível da Capi! Faltam apenas " + (diasStreakMinimo - diasConsecutivos) + "dias! Uhuuul!");
        return null;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public int getDiasStreakMinimo() {
        return diasStreakMinimo;
    }
}
