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

public class RottaCard {

    // ATRIBUTOS
    private int id;
    private String codigoNfc;
    private boolean ativo;
    private Carteira carteira;

    // CONSTRUTOR
    public RottaCard(int id, String codigoNfc, boolean ativo) {
        this.id = id;
        this.codigoNfc = codigoNfc;
        this.ativo = ativo;
    }

    // MÉTODOS
    // Associa o cartão físico (NFC) à carteira digital do usuário.
    public void vincularCartao(Carteira carteira) {
        this.carteira = carteira;
        System.out.println("Cartão NFC vinculado à carteira " + carteira.getId() + ".");
    }

    // Retorna o código NFC do cartão, usado para leitura na catraca.
    public String identificar() {
        return codigoNfc;
    }

    // GETTERS
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