package br.com.rotta.models;

/**
 * Classe que representa o cartão Rotta com identificação NFC vinculado à carteira do usuário.
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
    /**
     * Cria um cartão Rotta com identificação NFC e status informado.
     *
     * @param id identificador do cartão
     * @param codigoNfc código de identificação NFC
     * @param ativo indica se o cartão está ativo
     */
    public RottaCard(int id, String codigoNfc, boolean ativo) {
        this.id = id;
        this.codigoNfc = codigoNfc;
        this.ativo = ativo;
    }

    // MÉTODOS
    /**
     * Vincula o cartão à carteira digital do usuário.
     *
     * @param carteira carteira que será vinculada ao cartão
     */
    public void vincularCartao(Carteira carteira) {
        this.carteira = carteira;
        System.out.println("Cartão NFC vinculado à carteira " + carteira.getId() + ".");
    }

    /**
     * Retorna o código NFC utilizado para identificar o cartão.
     *
     * @return código NFC do cartão
     */
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