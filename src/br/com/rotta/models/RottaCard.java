package br.com.rotta.models;

public class RottaCard {
    //ATRIBUTOS
    private int id;
    private String codigoNfc;
    private boolean ativo;
    private Carteira carteira;

    //CONSTRUTOR
    public RottaCard(int id, String codigoNfc, Carteira carteira) {
        this.id = id;
        this.codigoNfc = codigoNfc;
        this.carteira = carteira;
        this.ativo = true;
    }

    //MÉTODOS
    public String identificar() {
        System.out.println("Lendo cartão NFC...");
        return this.codigoNfc;
    }

    //GETTERS
    public boolean isAtivo() {
        return ativo;
    }

    public Carteira getCarteira() {
        return carteira;
    }
}
