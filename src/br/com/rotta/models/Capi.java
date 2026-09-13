package br.com.rotta.models;

/**
 * Classe que representa a capivara mascote do Rotta, seu nível e as dicas apresentadas ao usuário.
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
    /**
     * Cria a Capi com identificação, nome, nível e descrição.
     *
     * @param id identificador da Capi
     * @param nome nome da Capi
     * @param nivel nível inicial da Capi
     * @param descricao descrição da Capi
     */
    public Capi(int id, String nome, String nivel, String descricao) {
        this.id = id;
        this.nome = nome;
        this.nivel = nivel;
        this.descricao = descricao;
    }

    // MÉTODOS
    /**
     * Exibe na tela as informações da Capi.
     */
    public void exibirNaTela() {
        System.out.println("Mascote: " + nome);
        System.out.println("Aparência: " + descricao);
    }

    /**
     * Retorna uma dica relacionada à funcionalidade utilizada pelo usuário.
     *
     * @param funcionalidade funcionalidade que está sendo utilizada
     * @return dica da Capi
     */
    public String sugerirDica(String funcionalidade) {
        return "Dica da Capi: escolha um desafio que combine com sua rotina de hoje!";
    }

    /**
     * Define e retorna o nível da Capi de acordo com os dias consecutivos do usuário.
     *
     * @param diasConsecutivos quantidade de dias consecutivos do usuário
     * @return nível atual da Capi
     */
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