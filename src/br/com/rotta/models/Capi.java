package br.com.rotta.models;

/**
 * Grupo de Desenvolvimento do Projeto.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

public class Capi {

    // ATRIBUTOS
    private int id;
    private String nome;
    private String nivel;
    private String descricao;

    // CONSTRUTOR
    // Cria a Capi com o seu nome, nível e descrição visual.
    public Capi(int id, String nome, String nivel, String descricao) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
        this.descricao = descricao;
    }

    // MÉTODOS
    // Exibe as informações atuais do Capi.
    public void exibirNaTela() {
        System.out.println("Mascote: " + nome);
        System.out.println("Aparência: " + descricao);
    }
    // Retorna uma dica simples relacionada à funcionalidade utilizada.
    public String sugerirDica(String funcionalidade) {
        return "Dica da Capi: escolha um desafio que combine com sua rotina de hoje!";
    }

    // Define o nível do Capi de acordo com os dias consecutivos do usuário.
    public String verificarNivel(int diasConsecutivos) {

        if (diasConsecutivos >= 30) {
            nivel = "Avançada";
        } else if (diasConsecutivos >= 15) {
            nivel = "Intermediária";
        } else if (diasConsecutivos >= 7) {
            nivel = "Iniciante";
        } else {
            nivel = "Iniciante";
        }

        return nivel;
    }

    // GETTERS
    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getNivel() {
        return nivel;
    }

    public String getDescricao() {
        return descricao;
    }
}