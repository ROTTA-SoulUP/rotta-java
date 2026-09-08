package br.com.rotta.models;

public class RottaCard {
    private int id;
    private String codigoNfc;
    private boolean ativo;
    private Carteira carteira;

    public RottaCard(int id, String codigoNfc, boolean ativo) {
        this.id = id;
        this.codigoNfc = codigoNfc;
        this.ativo = ativo;
    }

    public void vincularCartao(Carteira carteira) {
        this.carteira = carteira;
        System.out.println("Cartão NFC vinculado à carteira " + carteira.getId() + ".");
    }

    public String identificar() {
        return codigoNfc;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Carteira getCarteira() {
        return carteira;
    }

    public int getId() {
        return id;
    }

    public String getCodigoNfc() {
        return codigoNfc;
    }
}