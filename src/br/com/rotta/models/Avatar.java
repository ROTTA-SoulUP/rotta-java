package br.com.rotta.models;

public class Avatar {

    //ATRIBUTOS
    private int id;
    private String descricao;
    private Nivel nivel;

    //CONSTRUTOR
    public Avatar(int id, String descricao, Nivel nivel) {
        this.id = id;
        this.descricao = descricao;
        this.nivel = nivel;
    }

    //METODOS
    public void exibirAvatar() {
        System.out.println("---------------------------------------------------------------" +
                            "Seu avatar da Capi evoluiu! Olha que linda que ela está agora! " +
                            "Nível: " + nivel.getNome() +
                            "Aparência: " + descricao +
                            "-------------------------------------------------------------");
    }

    //GETTERS
    public int getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Nivel getNivel() {
        return nivel;
    }
}
