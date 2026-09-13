package br.com.rotta.models;

/**
 * Classe que representa uma foto enviada como comprovação de uma ação sustentável.
 *
 * @author Guilherme Almeida (RM: 571713)
 * @author Leonardo Arnaldo (RM: 573188)
 * @author Thiago Santa Rosa (RM: 572616)
 * @author Geovanna Secchi Egea (RM: 573452)
 * @author Beatriz Urbano M. de Oliveira (RM: 569341)
 */

public class PostagemFoto extends Midia {

    // ATRIBUTOS
    private String localizacao;

    // CONSTRUTOR
    /**
     * Cria uma postagem de foto vinculada ao usuário e ao desafio.
     *
     * @param id identificador da mídia
     * @param nomeArquivo nome do arquivo da foto
     * @param usuario usuário que enviou a foto
     * @param participacao participação no desafio relacionada à foto
     * @param localizacao localização informada para a foto
     */
    public PostagemFoto(int id, String nomeArquivo, Usuario usuario,
                        ParticipacaoDesafio participacao, String localizacao) {
        super(id, nomeArquivo, usuario, participacao);
        this.localizacao = localizacao;
    }

    // MÉTODOS
    /**
     * Envia a foto para validação, reaproveitando o comportamento da classe Midia.
     */
    @Override
    public void enviar() {
        super.enviar();
        System.out.println("Foto enviada para validação.");
    }

    /**
     * Simula a validação do arquivo de foto.
     *
     * @return true quando a foto é considerada válida
     */
    public boolean validarFoto() {
        System.out.println("Foto validada.");
        return true;
    }

    /**
     * Simula a compressão da imagem antes do envio.
     */
    public void comprimirImagem() {
        System.out.println("Imagem comprimida para o envio.");
    }

    // GETTER
    public String getLocalizacao() {
        return localizacao;
    }
}