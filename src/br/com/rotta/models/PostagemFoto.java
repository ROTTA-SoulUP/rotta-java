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

public class PostagemFoto extends Midia {

    // ATRIBUTOS
    private String localizacao;

    // CONSTRUTOR
    public PostagemFoto(int id, String nomeArquivo, Usuario usuario,
                        ParticipacaoDesafio participacao, String localizacao) {
        super(id, nomeArquivo, usuario, participacao);
        this.localizacao = localizacao;
    }

    // MÉTODOS
    // Reaproveita o envio da classe mãe e complementa com uma mensagem específica de foto.
    @Override
    public void enviar() {
        super.enviar();
        System.out.println("Foto enviada para validação.");
    }

    // Simula a validação do arquivo de foto.
    public boolean validarFoto() {
        System.out.println("Foto validada.");
        return true;
    }

    // Simula a compressão da imagem antes do envio.
    public void comprimirImagem() {
        System.out.println("Imagem comprimida para o envio.");
    }

    // GETTER
    public String getLocalizacao() {
        return localizacao;
    }
}